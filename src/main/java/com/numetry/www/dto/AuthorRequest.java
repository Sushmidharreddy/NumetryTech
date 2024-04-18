package com.numetry.www.dto;

import lombok.Data;

@Data
public class AuthorRequest {
	private String authorName;
    private BookRequest book;

}
