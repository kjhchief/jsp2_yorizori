package ezen.yorizori.domain.cookbook.dto;

public class Cookbook {
	private int bookId;
	private String name;
	private String describe;
	private String authorId;
	
	public Cookbook() {}
	
	public Cookbook(int bookId, String name, String describe, String authorId) {
		this.bookId = bookId;
		this.name = name;
		this.describe = describe;
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "bookId: "+bookId +" "+"name: " + name +" "+"describe: "+ describe+ " "+"authorId: " + authorId;
	}
	
	
}
