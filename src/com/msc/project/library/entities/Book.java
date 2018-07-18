package com.msc.project.library.entities;

public class Book {
public String Book;
private String bookId;

public String getBookId() {
	return bookId;
}

public void setBookId(String bookId) {
	this.bookId = bookId;
}

public String getBooks() {
	return Book;
}

public void setBooks(String books) {
	Book = books;
}

public Book(String books,String bookId) {
	super();
	this.Book = books;
	this.bookId = bookId;
}

public Book() {
	super();
	// TODO Auto-generated constructor stub
}

}
