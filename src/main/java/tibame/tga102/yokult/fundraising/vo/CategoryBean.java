package tibame.tga102.yokult.fundraising.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "fund_category")
@Component
public class CategoryBean {

	@Id
	private String categoryID;
	
	private String categoryName;

	public CategoryBean() {
	}

	public CategoryBean(String categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryBean [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	
}
