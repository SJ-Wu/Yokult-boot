package tibame.tga102.yokult.member.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tibame.tga102.yokult.member.dao.MemberDao;
import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.util.YokultConstants;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;

	@Override
	public Member getOneByID(String account) {
		return dao.selectByMemberID(account);
	}

	@Override
	public List<Member> getAll() {
		return dao.selectAll();
	}

	@Override
	public Integer remove(Member member) {
		member.setMemStatus("DELETE");
		if (!checkValue(member.getMemID())) {
			System.out.println("帳號錯誤");
			return -1;
		}
		return dao.updateStatus(member);
	}

	@Override
	public Integer modify(Member member) {
		// 1. check if there is any null column in the not-null column
		if (!checkValue(member.getMemID())) {
			System.out.println("帳號錯誤");
			return null;
		}
		return dao.update(member);
	}

	@Override
	public String login(Member member) {
		String jwtToken = null;
		String account = member.getMemID();
		String password = member.getMemPassword();
//		System.out.println(account + " " + password);
		if (!checkValue(account) || !checkValue(password)) {
			System.out.println("帳號或密碼錯誤");
			return null;
		}
		member = dao.selectByMemberIdAndPassword(member);
		if (member != null) {
			Date expireDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
			jwtToken = Jwts.builder().setSubject(member.getMemID()).setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512, YokultConstants.JWTKEY).compact();
			System.out.println("jwtToken: " + jwtToken);
		}
		return jwtToken;
	}

	@Override
	public Integer register(Member member) {
		// Generate verification code in memStatus;
		GenAuthCode genAuthCode = new GenAuthCode();
		String authCode = genAuthCode.generate();
		member.setMemStatus(authCode);
		// Insert in persistence.
		Integer status = dao.insert(member);
		if (status > 0) {
			System.out.println("send email");
			String to = member.getMemEmail();
//			String subject = "Yokult會員認證信件";

			String verifysite = "http://localhost:8080/yokult/api/0.02/member/verify?memID=" + member.getMemID()
					+ "&code=" + authCode;
			String messageText = "Hello! " + member.getMemName() + " 請點擊連結以驗證信箱: " + verifysite + "\n";
			System.out.println("Receiver:" + to);
			System.out.println(messageText);
//			MailService mailService = new MailService();
//			mailService.sendMail(to, subject, messageText);
		}
		return status;
	}

	private boolean checkValue(String value) {
		if (value == null || Objects.equals(value, "")) {
			System.out.println(value);
			return false;
		}
		return true;
	}

	@Override
	public List<Member> query(String memEmail, String memID, String memName) {
		List<Member> list = null;
		if (memID != null) {
			list = dao.queryByMemberID(memID);
		} else if (memEmail != null) {
			list = dao.queryByMemberEmail(memEmail);
		} else if (memName != null) {
			list = dao.queryByMemberName(memName);
		}
		return list;
	}

	@Override
	public boolean emailVerification(String code, Member member) {
		String verifyCode = dao.selectByMemberID(member.getMemID()).getMemStatus();
		if (verifyCode.equals(code)) {
			member.setMemStatus("APPROVED");
			return (dao.updateStatus(member) > 0) ? true : false;
		}
		return false;
	}

}

class MailService {
	private final static String HOST = "smtp.gmail.com";
	private final static String AUTH = "true";
	private final static String PORT = "587";
	private final static String STARTTLE_ENABLE = "true";
	private final static String SENDER = "nickwu0301@gmail.com";
	private final static String PASSWORD = "";

//  設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String recipients, String mailSubject, String mailBody) {
//		String recipientCcs = "副本mail";
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", STARTTLE_ENABLE);
		props.put("mail.smtp.ssl.trust", HOST);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//      設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, PASSWORD);
			}
		};

		Session session = Session.getDefaultInstance(props, authenticator);
		Message message = new MimeMessage(session);

		try {
//			設定Email Message start

//			設定寄件人、收件人、副本、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientCcs));
//          https://javaee.github.io/javamail/docs/api/javax/mail/internet/MimeUtility.html#encodeText-java.lang.String-java.lang.String-java.lang.String- (第三個參數參考API文件)
			message.setSubject(MimeUtility.encodeText(mailSubject, StandardCharsets.UTF_8.toString(), "B"));

//			first part (text)
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setContent(mailBody, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);
			message.setContent(multipart);

//   		寄出email
			Transport transport = session.getTransport("smtp");
			try {
				transport.connect();
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

class GenAuthCode {
	public String generate() {
		char[] captcha = new char[8];
		// 字串長度8碼，英文大小寫加數字
		int pwCnt = 0;
		genCode: while (pwCnt < 8) {
			// step1. 決定產生數字還是英文
			char temp = genRandWord((int) (Math.random() * 3));
			// step2. 檢查有無重複
			if (pwCnt == 0) {
				captcha[pwCnt] = temp;
			} else {
				for (int i = 0; i < captcha.length; i++) {
					if (captcha[i] == temp) {
						continue genCode;
					}
				}
				captcha[pwCnt] = temp;
			}
			pwCnt++;
		}
		System.out.print("本次隨機產生的驗證碼為: ");
		for (int i = 0; i < captcha.length; i++) {
			System.out.print(captcha[i]);
		}
		System.out.println();
		return String.valueOf(captcha);
	}

	char genRandWord(int type) {
		char word = 0;
		switch (type) {
		case 0: // 0 to 9: 48~57
			word = (char) (int) ((Math.random() * 10) + 48);
			break;
		case 1: // A to Z : 65~90
			word = (char) (int) ((Math.random() * 26) + 65);
			break;
		case 2: // a to z: 97~122
			word = (char) (int) ((Math.random() * 26) + 97);
			break;
		}
		return word;
	}
}