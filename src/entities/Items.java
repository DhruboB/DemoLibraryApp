package entities;

public class Items {
	public String Author;
	public String BookName;
	public String Subject;
	public String Rating;
	private String bookId;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}
	public Items(String author, String bookName, String subject, String rating, String bookId) {
		super();
		Author = author;
		BookName = bookName;
		Subject = subject;
		Rating = rating;
		this.bookId = bookId;		
	}
	public Items() {
		super();
	}
	
	

}
