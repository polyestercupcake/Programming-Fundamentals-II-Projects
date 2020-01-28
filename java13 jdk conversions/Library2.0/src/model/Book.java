package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class contains all the business process needed for the library management system to work, including
 * database connectivity and functionality.
 * @author 217056
 */
public class Book {
	/**
	 * The rowID of the book. Needed to distinguish between books in the database.
	 */
	private int rowID;
	/**
	 * Holds the author of a book.
	 */
	private String author;
	/**
	 * Holds the title of a book.
	 */
	private String title;
	/**
	 * Holds the genre of a book.
	 */
	private String genre;
	/**
	 * Holds the location of a book.
	 */
	private String location;
	
	/**
	 * @return the rowID
	 */
	public final int getRowID() {
		return rowID;
	}
	
	/**
	 * @param pRowID the rowID to set.
	 */
	public final void setRowID(final int pRowID) {
		this.rowID = pRowID;
	}
	
	/**
	 * @return the author
	 */
	public final String getAuthor() {
		return author;
	}
	/**
	 * @param pAuthor the author to set
	 */
	public final void setAuthor(final String pAuthor) {
		this.author = pAuthor;
	}
	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}
	/**
	 * @param pTitle the title to set
	 */
	public final void setTitle(final String pTitle) {
		this.title = pTitle;
	}
	/**
	 * @return the genre
	 */
	public final String getGenre() {
		return genre;
	}
	/**
	 * @param pGenre the genre to set
	 */
	public final void setGenre(final String pGenre) {
		this.genre = pGenre;
	}
	/**
	 * @return the location
	 */
	public final String getLocation() {
		return location;
	}
	/**
	 * @param pLocation the location to set
	 */
	public final void setLocation(final String pLocation) {
		this.location = pLocation;
	}
	/**
	 * This method will be called when no search criteria has been entered in the UI and the user presses Search. 
	 * It will get all books currently in the database and output them to the user since no filter criteria 
	 * was specified.
	 * @return All books currently in the database.
	 * @throws SQLException in case something goes wrong in the database while
	 * getting all the books.
	 */
	public static final List<Book> getAllBooks() throws SQLException {
		Database db = new Database();
		List<Book> returnBooks = new ArrayList<>();
		
	try {
		ResultSet rsBook = db.getResultSet("library.usp_GetAllBooks");
		
		while (rsBook.next()) {
			Book b = new Book();
			b.setRowID(rsBook.getInt("RowID"));
			b.setAuthor(rsBook.getString("Author"));
			b.setTitle(rsBook.getString("Title"));
			b.setGenre(rsBook.getString("Genre"));
			b.setLocation(rsBook.getString("Location"));
			returnBooks.add(b);
		} return returnBooks;
		
	} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in getAllBooks().");
		}	
	}
	/**
	 * When the user wants to create a new book, this method is called and will create a new book in the database.
	 * The new book's rowID will automatically be given by the database in order to avoid duplicate rowID's.
	 */
	@SuppressWarnings("rawtypes")
	public final void createBook() {
	Database db = new Database();
	List<Parameter> params = new ArrayList<>();
	try {
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<String>(author));
			params.add(new Parameter<String>(title));
			params.add(new Parameter<String>(genre));
			params.add(new Parameter<String>(location));
					
			ResultSet rsNewBook = db.getResultSet("library.usp_CreateBook", params);
			if (rsNewBook.next()) {
				rowID = rsNewBook.getInt("RowID");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * When a book's information needs to be updated, this method will be called and will set all the updated 
	 * information to the each corresponding field in that book's rowID.
	 */
	public final void updateBook() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
	try {
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(rowID));
			params.add(new Parameter<String>(author));
			params.add(new Parameter<String>(title));
			params.add(new Parameter<String>(genre));
			params.add(new Parameter<String>(location));
			
			db.executeSql("library.usp_UpdateBook", params);
			
	 } catch (SQLException e) {
		e.printStackTrace();
	}	
}
	/**
	 * When a book needs to be deleted, this method will be called and will delete all the book's 
	 * information from the database.
	 */
	public final void deleteBook() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
	try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(rowID));
			db.executeSql("library.usp_DeleteBook", params);
		} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in deleting the Book.");
	}
}
	/**
	 * When a book needs to be searched for, this method will be called and filters the search criteria 
	 * within the database to output the books that meet that specific criteria.
	 * @param option One of the four categories that can be searched for within the library.
	 * @return The list of filtered books for whatever was searched for.
	 * @throws SQLException in case something goes wrong in the database while
	 * searching for a book.
	 */
	public List<Book> search(final SearchOption option) throws SQLException {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		List<Book> returnBooks = new ArrayList<>();
		ResultSet rsBook;
	try {
			if (option == SearchOption.AUTHOR) {
				params.add(new Parameter<String>(getAuthor()));
				rsBook = db.getResultSet("library.usp_FindBooksByAuthor", params);
			} else if (option == SearchOption.TITLE) {
				params.add(new Parameter<String>(getTitle()));
				rsBook = db.getResultSet("library.usp_FindBooksByTitle", params);
			} else if (option == SearchOption.GENRE) {
				params.add(new Parameter<String>(getGenre()));
				rsBook = db.getResultSet("library.usp_FindBooksByGenre", params);
			} else {
				params.add(new Parameter<String>(getLocation()));
				rsBook = db.getResultSet("library.usp_FindBooksByLocation", params);
			}
			while (rsBook.next()) {
				Book b = new Book();
				b.setRowID(rsBook.getInt("RowID"));
				b.setAuthor(rsBook.getString("Author"));
				b.setTitle(rsBook.getString("Title"));
				b.setGenre(rsBook.getString("Genre"));
				b.setLocation(rsBook.getString("Location"));
				returnBooks.add(b);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 		return returnBooks;
	}

	/**
	 * This method combines with the search method above to filter authors of books in the database.
	 * @return The search method for searching authors.
	 * @throws SQLException in case something goes wrong in the database while
	 * searching for a author.
	 */
	public final List<Book> getBookByAuthor() throws SQLException {
		return search(SearchOption.AUTHOR);
	}
	/**
	 * This method combines with the search method above to filter titles of books in the database.
	 * @return The search method for searching titles.
	 * @throws SQLException in case something goes wrong in the database while
	 * searching for a title.
	 */
	public final List<Book> getBookByTitle() throws SQLException {
		return search(SearchOption.TITLE);
	}
	/**
	 * This method combines with the search method above to filter genres of books in the database.
	 * @return The search method for searching genres.
	 * @throws SQLException in case something goes wrong in the database while
	 * searching for a genre.
	 */
	public final List<Book> getBookByGenre() throws SQLException {
		return search(SearchOption.GENRE);
	}
	/**
	 * This method combines with the search method above to filter locations of books in the database.
	 * @return The search method for searching locations.
	 * @throws SQLException in case something goes wrong in the database while
	 * searching for a location.
	 */
	public final List<Book> getBookByLocation() throws SQLException {
		return search(SearchOption.LOCATION);
	}
}
