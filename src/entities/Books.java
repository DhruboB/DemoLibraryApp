package entities;

public class Books {
public String Books;
private String bookId;

public String getBookId() {
	return bookId;
}

public void setBookId(String bookId) {
	this.bookId = bookId;
}

public String getBooks() {
	return Books;
}

public void setBooks(String books) {
	Books = books;
}

public Books(String books,String bookId) {
	super();
	this.Books = books;
	this.bookId = bookId;
}

public Books() {
	super();
	// TODO Auto-generated constructor stub
}

}
