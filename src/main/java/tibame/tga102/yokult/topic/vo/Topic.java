package tibame.tga102.yokult.topic.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Topic")
public class Topic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topid")
	private Integer topid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "foreword")
	@Type(type="text")
	private String foreword;
	
	@Column(name = "content")
	@Type(type="text")
	private String content;
	
	@Column(name = "sortid")
	private String sortid;
	
	@Column(name = "views")
	private Integer views;
	
	@Column(name = "posttime")
	private Timestamp posttime;
	
	@Lob
	@Column(name = "pic")
	private byte[] pic;
	
	@Transient
	private String pic64;
	
	@Override
	public String toString() {
		return "Topic [topid=" + topid + ", title=" + title + ", sortid=" + sortid + ", views=" + views + ", posttime="
				+ posttime + "]";
	}


	public void renewBean() {
	 
		  this.pic64 = Base64.getEncoder().encodeToString(this.pic);

		 }
	
	
	public Integer getTopid() {
		return topid;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForeword() {
		return foreword;
	}

	public void setForeword(String foreword) {
		this.foreword = foreword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSortid() {
		return sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Timestamp getPosttime() {
		return posttime;
	}

	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}