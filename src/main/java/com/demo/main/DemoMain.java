package com.demo.main;

import static spark.Spark.*;


import com.demo.service.LibraryManagementService;
import com.demo.wrapper.BookWrapper;
import com.demo.wrapper.UserWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoMain {
	public static void main(String[] args){

		LibraryManagementService libraryManagementService = new LibraryManagementService();

		post("/createUser", (request, response) -> {		
			ObjectMapper mapper = new ObjectMapper();
			UserWrapper wrappercreation = mapper.readValue(request.body(), UserWrapper.class);
			return libraryManagementService.createUser(wrappercreation);
		});

		get("/getAllUsers", (request, response) -> {
			response.type("application/json");
			return LibraryManagementService.dataToJson(libraryManagementService.getAllUsers());

		});

		put("/updateUser", (request, response) -> {
			ObjectMapper mapper = new ObjectMapper();
			UserWrapper wrappercreation = mapper.readValue(request.body(), UserWrapper.class);
			return libraryManagementService.updateUser(wrappercreation); 
		}); 

		post("/addBook", (request, response) -> {
			ObjectMapper mapper = new ObjectMapper();
			BookWrapper wrapperc = mapper.readValue(request.body(), BookWrapper.class);
			return libraryManagementService.addBook(wrapperc);
		});

		get("/getAllBooks", (req, res) -> {
			res.type("application/json");
			return LibraryManagementService.dataToJson(libraryManagementService.getAllBooks());
		});


		get("/findBookByName/:name", (request, response) ->{ 
			String name = request.params("name");
			response.type("application/json");
			return LibraryManagementService.dataToJson(libraryManagementService.searchBookByName(name));
		});

		get("/checkOutBook/:userid/:bookid", (request, response) ->{ 
			Long userid = Long.valueOf(request.params("userid"));
			Long bookid = Long.valueOf(request.params("bookid"));
			return libraryManagementService.checkoutBook(userid, bookid);

		});

		delete("/deleteBook/:bookid", (request, response) -> {
			Long bookid = Long.valueOf(request.params("bookid"));
			return libraryManagementService.deleteBook(bookid);
		});

	}
}
