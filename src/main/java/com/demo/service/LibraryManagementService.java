package com.demo.service;

import java.io.IOException;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.demo.exception.BookException;
import com.demo.exception.UserException;
import com.demo.model.Book;
import com.demo.model.User;
import com.demo.repository.LibraryManagementRepository;
import com.demo.validator.UserValidator;
import com.demo.wrapper.BookWrapper;
import com.demo.wrapper.UserWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class LibraryManagementService {

	private LibraryManagementRepository managementRepository;

	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} 	
		catch (IOException e){
			throw new RuntimeException("IOException from a StringWriter?");
		}

	}

	public LibraryManagementService() {
		this.managementRepository = new LibraryManagementRepository();
	}

	public String createUser(UserWrapper userWrapper) {
		UserValidator.validateUserDetails(userWrapper);
		User user = userWrapper.unwrap();
		managementRepository.createUser(user);
		return "created";
	}

	public List<UserWrapper> getAllUsers() {
		List<User> users = managementRepository.getAllUsers();
		List<UserWrapper> userWrappers = new ArrayList<UserWrapper>();

		for (User user : users) {
			UserWrapper userWrapper = new UserWrapper();
			userWrapper.wrap(user);
			userWrappers.add(userWrapper);
		}
		return userWrappers;
	}

	public String updateUser(UserWrapper userWrapper){
		User user = managementRepository.fetchUserById(userWrapper.getId());
		if (user == null) {
			throw new UserException("Requested user not found.");
		}
		user = userWrapper.unwrap();
		managementRepository.updateUser(user);
		return "updated";
	}

	public List<BookWrapper> getAllBooks() {
		List<Book> books = managementRepository.getAllBooks();
		List<BookWrapper> bookWrappers = new ArrayList<BookWrapper>();
		for (Book book : books) {
			BookWrapper bookWrapper = new BookWrapper();
			bookWrapper.wrap(book);
			bookWrappers.add(bookWrapper);
		}

		return bookWrappers;
	}

	public BookWrapper searchBookByName(String name) {
		Book book = managementRepository.findBookByName(name);
		if (book == null) {
			throw new BookException("Requested book not found.");
		}
		BookWrapper bookWrapper = new BookWrapper();
		bookWrapper.wrap(book);
		return bookWrapper;
	}

	public String addBook(BookWrapper bookWrapper) {
		Book book = bookWrapper.unwrap(bookWrapper);
		// no checked out when adding
		book.setCheckedOutBy(null);
		managementRepository.addBook(book);
		return "book added";
	}

	public String checkoutBook(Long userId, Long bookId) {
		Book book = managementRepository.fetchBookById(bookId);
		User user = managementRepository.fetchUserById(userId);

		if (user == null) {
			throw new UserException("Requested user was not found.");
		}
		else if (book == null) {
			throw new BookException("Requested book was not found.");
		}
		else if (book.getCheckedOutBy() != null) {
			throw new BookException("The book is already checked out.");
		}
		managementRepository.checkoutBook(userId, bookId);
		return "Book checked out succesfully";
	}

	public String deleteBook(Long id) {
		Book book = managementRepository.fetchBookById(id);
		if(book==null)
		{
			throw new BookException("Book not found");
		}
		else if (book.getCheckedOutBy() != null) {
			throw new BookException("The book is already checked out.");
		}
		managementRepository.deleteBook(book);
		return "book deleted";
	}


}
