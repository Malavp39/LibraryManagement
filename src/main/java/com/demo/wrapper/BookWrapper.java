package com.demo.wrapper;

import java.util.List;
import com.demo.model.Book;

public class BookWrapper {
	private Long id;
	private String name;
	private List<String> authors;
	private UserWrapper userWrapper = new UserWrapper();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public UserWrapper getUserWrapper() {
		return userWrapper;
	}

	public void setUserWrapper(UserWrapper userWrapper) {
		this.userWrapper = userWrapper;
	}

	@Override
	public String toString() {
		return "BookWrapper [id=" + id + ", name=" + name + ", authors=" + authors + ", userWrapper=" + userWrapper + "]";
	}

	public Book unwrap(BookWrapper bookWrapper) {
		Book book = new Book();
		book.setAuthors(bookWrapper.getAuthors());
		book.setCheckedOutBy(userWrapper.unwrap());
		book.setId(bookWrapper.getId());
		book.setName(bookWrapper.getName());
		return book;
	}
/*
	public Book unwrapForUpdate(Book book) {
		book.setAuthors(this.authors);
		book.setCheckedOutBy(this.userWrapper.unwrap());
		book.setId(this.id);
		book.setName(this.name);
		return book;
	}*/

	public void wrap(Book book) {
		this.id = book.getId();
		this.authors = book.getAuthors();
		this.name = book.getName();
		if (book.getCheckedOutBy() != null) {
			this.userWrapper.wrap(book.getCheckedOutBy());
		}
	}
}
