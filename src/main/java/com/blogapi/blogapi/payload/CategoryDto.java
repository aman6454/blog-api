package com.blogapi.blogapi.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDto {

	private Integer categoryId;
	@NotEmpty
	@Size(min = 5,message = "Minimum size of Category Title is 4")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10,message = "Minimum size of Category Description is 10")
	private String categoryDescription;
	
	public CategoryDto() {
		super();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
}
