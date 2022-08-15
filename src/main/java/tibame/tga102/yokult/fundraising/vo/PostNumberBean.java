package tibame.tga102.yokult.fundraising.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fund_postnumber")
public class PostNumberBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_SID;
	private String postCity;
	private String postArea;
	private String postNumber;
	
	
	public PostNumberBean() {}


	@Override
	public String toString() {
		return "PostNumberBean [post_SID=" + post_SID + ", postCity=" + postCity + ", postArea=" + postArea
				+ ", postNumber=" + postNumber + "]";
	}


	public Integer getPost_SID() {
		return post_SID;
	}

	public String getPostCity() {
		return postCity;
	}

	public String getPostArea() {
		return postArea;
	}

	public String getPostNumber() {
		return postNumber;
	}



	
	
}
