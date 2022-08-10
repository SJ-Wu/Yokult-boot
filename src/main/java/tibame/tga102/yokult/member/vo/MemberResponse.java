package tibame.tga102.yokult.member.vo;

import org.springframework.stereotype.Component;

@Component
public class MemberResponse {
	private String msg;
	private Member member;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "MemberResponse [msg=" + msg + ", member=" + member + "]";
	}	
}
