package com.numetry.www.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.www.entity.Author;


public interface AuthorRepo extends JpaRepository<Author, Long> {

}
