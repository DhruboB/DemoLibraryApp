package entities;

public class NewItems {
	public String comments;
	public String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public NewItems(String name,String comments) {
		super();
		this.name=name;
		this.comments = comments;
	}
	public NewItems() {
		super();
	}
	
}
