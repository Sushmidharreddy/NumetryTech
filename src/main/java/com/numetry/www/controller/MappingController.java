package com.numetry.www.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.www.dao.AuthorRepo;
import com.numetry.www.dao.BookRepo;
import com.numetry.www.dto.AuthorRequest;
import com.numetry.www.dto.BookRequest;
import com.numetry.www.entity.Author;
import com.numetry.www.entity.Book;

@RestController
@RequestMapping("/api/v1")
public class MappingController
{
//	@Autowired
//    private AuthorRepo authorRepository;
//
//    // Create operation
//    @PostMapping("/post")
//    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
//        Author savedAuthor = authorRepository.save(author);
//        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
//    }
//
//    // Read operations
//    @GetMapping("/get")
//    public ResponseEntity<List<Author>> getAllAuthors() {
//        List<Author> authors = authorRepository.findAll();
//        return new ResponseEntity<>(authors, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
//        Optional<Author> authorOptional = authorRepository.findById(id);
//        if (authorOptional.isPresent()) {
//            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Update operation
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
//        Optional<Author> existingAuthorOptional = authorRepository.findById(id);
//        if (existingAuthorOptional.isPresent()) {
//            Author existingAuthor = existingAuthorOptional.get();
//            existingAuthor.setAuthorName(author.getAuthorName());
//            // You can add more fields to update as needed
//            Author updatedAuthor = authorRepository.save(existingAuthor);
//            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete operation
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
//        Optional<Author> authorOptional = authorRepository.findById(id);
//        if (authorOptional.isPresent()) {
//            authorRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
	
	
//	@Autowired
//    private AuthorRepo authorRepository;
//
//    @Autowired
//    private BookRepo bookRepository;
//
//    @PostMapping("/post")
//    public String createAuthorWithBook(@RequestBody AuthorRequest authorRequest) {
//        Author author = new Author();
//        author.setAuthorName(authorRequest.getAuthorName());
//
//        Book book = new Book();
//        book.setBookName(( authorRequest.getBook()).getBookName());
//        book.setAuthor(author);
//
//        author.getBook().add(book);
//
//        authorRepository.save(author);
//        return"done";
//    }
	 @Autowired
	    private AuthorRepo authorRepository;

	    // Create operation
	    @PostMapping("/post")
	    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) {
	        Author author = new Author();
	        author.setAuthorName(authorRequest.getAuthorName());

	        if (authorRequest.getBook() != null) {
	            Book book = new Book();
	            book.setBookName(authorRequest.getBook().getBookName());
	            book.setAuthor(author);
	            author.getBook().add(book);
	        }

	        Author savedAuthor = authorRepository.save(author);
	        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
	    }

	    // Read operations
	    @GetMapping("/get")
	    public ResponseEntity<List<Author>> getAllAuthors() {
	        List<Author> authors = authorRepository.findAll();
	        return new ResponseEntity<>(authors, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
	        Optional<Author> authorOptional = authorRepository.findById(id);
	        return authorOptional.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
	                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	    @Autowired
	    private BookRepo bookRepository;
	

	    // Update operation
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Author> updateAuthorWithNewBook(@PathVariable Long id, @RequestBody AuthorRequest bookRequest) {
	        Optional<Author> authorOptional = authorRepository.findById(id);
	        if (authorOptional.isPresent()) {
	            Author author = authorOptional.get();
	            
	            // Update author's name if provided in the request
	            if (bookRequest.getAuthorName() != null) {
	                author.setAuthorName(bookRequest.getAuthorName());
	            }

	            // Create a new book and associate it with the author
	            Book book = new Book();
	            book.setBookName(bookRequest.getBook().getBookName());
	            book.setAuthor(author);

	            // Save the new book
	            bookRepository.save(book);

	            // Add the new book to the author's list of books
	            author.getBook().add(book);

	            // Update the author
	            Author updatedAuthor = authorRepository.save(author);

	            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	

	    // Delete operation
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
	        if (authorRepository.existsById(id)) {
	            authorRepository.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
