package tibame.tga102.yokult.fundraising.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tibame.tga102.yokult.fundraising.service.PostNumberService;

@Entity
@Table(name="fund_post")
public class PostBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	private String postFisrtName;
	private String postLastName;
	private String postCellphone;
	private Integer post_SID;
	private String postAddress;
	private String memID;
	private String postNickName;
	@Transient
	private String postCity;
	@Transient
	private String postArea;
	@Transient
	private String postNumber;
	
	
	public void renew(PostNumberService postnumberService) {
		PostNumberBean postnumberBean = postnumberService.selectBean(this.post_SID);
		this.postCity = postnumberBean.getPostCity();
		this.postArea = postnumberBean.getPostArea();
		this.postNumber = postnumberBean.getPostNumber();
		
	}
	
	public PostBean() {}

	public PostBean(String postFisrtName, String postLastName, String postCellphone, Integer post_SID,
			String postAddress, String memID, String postNickName, String postCity, String postArea,
			String postNumber) {
		super();
		this.postFisrtName = postFisrtName;
		this.postLastName = postLastName;
		this.postCellphone = postCellphone;
		this.post_SID = post_SID;
		this.postAddress = postAddress;
		this.memID = memID;
		this.postNickName = postNickName;
		this.postCity = postCity;
		this.postArea = postArea;
		this.postNumber = postNumber;
	}

	@Override
	public String toString() {
		return "PostBean [postID=" + postID + ", postFisrtName=" + postFisrtName + ", postLastName=" + postLastName
				+ ", postCellphone=" + postCellphone + ", post_SID=" + post_SID + ", postAddress=" + postAddress
				+ ", memID=" + memID + ", postNickName=" + postNickName + ", postCity=" + postCity + ", postArea="
				+ postArea + ", postNumber=" + postNumber + "]";
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getPostFisrtName() {
		return postFisrtName;
	}

	public void setPostFisrtName(String postFisrtName) {
		this.postFisrtName = postFisrtName;
	}

	public String getPostLastName() {
		return postLastName;
	}

	public void setPostLastName(String postLastName) {
		this.postLastName = postLastName;
	}

	public String getPostCellphone() {
		return postCellphone;
	}

	public void setPostCellphone(String postCellphone) {
		this.postCellphone = postCellphone;
	}

	public Integer getPost_SID() {
		return post_SID;
	}

	public void setPost_SID(Integer post_SID) {
		this.post_SID = post_SID;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getPostNickName() {
		return postNickName;
	}

	public void setPostNickName(String postNickName) {
		this.postNickName = postNickName;
	}

	public String getPostCity() {
		return postCity;
	}

	public void setPostCity(String postCity) {
		this.postCity = postCity;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	
	
	
}
