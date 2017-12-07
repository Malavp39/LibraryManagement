package com.demo.repository;

import java.util.ArrayList;

import java.util.List;

import com.demo.model.Book;
import com.demo.model.User;

public class LibraryManagementRepository {

	private List<User> libUsers;
	private List<Book> libBooks;

	public LibraryManagementRepository() {
		initializeDB();
	}

	private void initializeDB() {
		this.libBooks = new ArrayList<Book>();
		this.libUsers = new ArrayList<User>();
	}

	public User fetchUserById(Long userId) {
		for (User user : libUsers) {
			if (user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	public Book fetchBookById(Long bookId) {
		for (Book book : libBooks) {
			if (book.getId().equals(bookId)) {
				return book;
			}
		}
		return null;
	}

	public void createUser(User user) {
		libUsers.add(user);
	}

	public List<User> getAllUsers() {
		return this.libUsers;
	}

	public void updateUser(User user) {
		for (User existingUser : libUsers) {
			if (user.getId().equals(existingUser.getId())) {
				mapUpdateDetails(user, existingUser);
			}
		}
	}

	public List<Book> getAllBooks() {
		return this.libBooks;
	}

	public Book findBookByName(String queryStr) {
		for (Book book : libBooks) {
			if (book.getName().equalsIgnoreCase(queryStr))
				return book;
		}
		return null;
	}

	public void addBook(Book book) {
		this.libBooks.add(book);
	}

	public void deleteBook(Book book) {
		this.libBooks.remove(book);
	}

	public void checkoutBook(Long userId, Long bookId) {
		Book tobeCheckedOut = null;
		User checkOutUser = null;
		for (Book book : libBooks) {
			if (book.getId().equals(bookId)) {
				tobeCheckedOut = book;
				break;
			}
		}
		for (User user : libUsers) {
			if (user.getId().equals(userId)) {
				checkOutUser = user;
				break;
			}
		}
		tobeCheckedOut.setCheckedOutBy(checkOutUser);
	}

	private void mapUpdateDetails(User updatedUser, User existingUser) {
		existingUser.setAge(updatedUser.getAge());
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setGender(updatedUser.getGender());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setMiddleName(updatedUser.getMiddleName());
		existingUser.setPhone(updatedUser.getPhone());
		existingUser.setZip(updatedUser.getZip());
	}
}
