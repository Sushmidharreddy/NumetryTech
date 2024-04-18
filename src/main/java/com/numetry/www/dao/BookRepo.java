package com.numetry.www.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.www.entity.Book;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;



public interface BookRepo extends JpaRepository<Book, Long> {

}
