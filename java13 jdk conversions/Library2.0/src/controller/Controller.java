package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;

/**
 * This class controls all functions with the UI down to the finest detail.
 * This is essentially what the user sees when the program is launched.
 * This class calls methods from the Book class as well to complete kitchen work.
 * @author Tanner's Laptop
 */
public class Controller {
//FIELDS
//SOURCE LABELS
	/**
	 * This label is an invisible label in the 'Update' tab that will get the source and fxid of the button clicked.
	 */
	@FXML
	private Label lblSourceUpdate;
	/**
	 * This label is an invisible label in the 'Search Authors' tab that will get the source 
	 * and fxid of the button clicked.
	 */
	@FXML
	private Label lblSourceAuthor;
	/**
	 * This label is an invisible label in the 'Search Titles' tab that will get the source 
	 * and fxid of the button clicked.
	 */
	@FXML
	private Label lblSourceTitle;
	/**
	 * This label is an invisible label in the 'Search Genres' tab that will get the source 
	 * and fxid of the button clicked.
	 */
	@FXML
	private Label lblSourceGenre;
	/**
	 * This label is an invisible label in the 'Search Location' tab that will get the source 
	 * and fxid of the button clicked.
	 */
	@FXML
	private Label lblSourceLocation;
//TABS
	/**
	 * This is the pane consisting of all the tabs in my view.
	 */
	@FXML
	private TabPane tabPane;
	/**
	 * This is the home page tab in my view.
	 */
	@FXML
	private Tab homeTab;
	/**
	 * This is my update tab in my view to update book information.
	 */
	@FXML
	private Tab updateTab;
// AUTHOR TABLEVIEW
	/**
	 * Author table in Search Author tab.
	 */
	@FXML
	private TableView<Book> authorTable;
	/**
	 * Author table Author column.
	 */
	@FXML
	private TableColumn<Book, String> authorAuthorC1;
	/**
	 * Author table Title column.
	 */
	@FXML
	private TableColumn<Book, String> authorTitleC2;
	/**
	 * Author table Genre column.
	 */
	@FXML
	private TableColumn<Book, String> authorGenreC3;
	/**
	 * Author table Location column.
	 */
	@FXML
	private TableColumn<Book, String> authorLocationC4;

// TITLE TABLEVIEW
	/**
	 * Title table in Search Title tab.
	 */
	@FXML
	private TableView<Book> titleTable;
	/**
	 * Title table Author column.
	 */
	@FXML
	private TableColumn<Book, String> titleAuthorC1;
	/**
	 * Title table Title column.
	 */
	@FXML
	private TableColumn<Book, String> titleTitleC2;
	/**
	 * Title table Genre column.
	 */
	@FXML
	private TableColumn<Book, String> titleGenreC3;
	/**
	 * Title table Location column.
	 */
	@FXML
	private TableColumn<Book, String> titleLocationC4;

// GENRE TABLEVIEW
	/**
	 * Genre Table in Search Genre tab.
	 */
	@FXML
	private TableView<Book> genreTable;
	/**
	 * Genre table Author column.
	 */
	@FXML
	private TableColumn<Book, String> genreAuthorC1;
	/**
	 * Genre table Title column.
	 */
	@FXML
	private TableColumn<Book, String> genreTitleC2;
	/**
	 * Genre table Genre column.
	 */
	@FXML
	private TableColumn<Book, String> genreGenreC3;
	/**
	 * Genre table Location column.
	 */
	@FXML
	private TableColumn<Book, String> genreLocationC4;

// LOCATION TABLEVIEW
	/**
	 * Location table in Search Location tab.
	 */
	@FXML
	private TableView<Book> locationTable;
	/**
	 * Location table...Author column.
	 */
	@FXML
	private TableColumn<Book, String> locationAuthorC1;
	/**
	 * Location table...Title column.
	 */
	@FXML
	private TableColumn<Book, String> locationTitleC2;
	/**
	 * Location table...Genre column.
	 */
	@FXML
	private TableColumn<Book, String> locationGenreC3;
	/**
	 * Location table...Location column.
	 */
	@FXML
	private TableColumn<Book, String> locationLocationC4;

// AUTHOR TAB
	/**
	 * Text box to search for authors.
	 */
	@FXML
	private TextField txtSearchAuthor;
	/**
	 * Button that activates the search function for authors Search Author tab.
	 */
	@FXML
	private Button btnSearchAuthor;
	/**
	 * Button that activates the updates process for a book Search Author tab.
	 */
	@FXML
	private Button btnUpdateAuthor;
	/**
	 * Button that activates the delete process for a book in Search Author tab.
	 */
	@FXML
	private Button btnDeleteAuthor;

// TITLE TAB
	/**
	 * Text box to search for titles.
	 */
	@FXML
	private TextField txtSearchTitle;
	/**
	 * Button that activates the search function for authors Search Titles tab.
	 */
	@FXML
	private Button btnSearchTitle;
	/**
	 * Button that activates the updates process for a book Search Titles tab.
	 */
	@FXML
	private Button btnUpdateTitle;
	/**
	 * Button that activates the delete process for a book in Search Titles tab.
	 */
	@FXML
	private Button btnDeleteTitle;

// GENRE TAB
	/**
	 * Text box to search for genre.
	 */
	@FXML
	private TextField txtSearchGenre;
	/**
	 * Button that activates the search function for authors Search Genres tab.
	 */
	@FXML
	private Button btnSearchGenre;
	/**
	 * Button that activates the updates process for a book Search Genres tab.
	 */
	@FXML
	private Button btnUpdateGenre;
	/**
	 * Button that activates the delete process for a book in Search Genres tab.
	 */
	@FXML
	private Button btnDeleteGenre;

// LOCATION TAB
	/**
	 * Text box to search for location.
	 */
	@FXML
	private TextField txtSearchLocation;
	/**
	 * Button that activates the search function for authors Search Location tab.
	 */
	@FXML
	private Button btnSearchLocation;
	/**
	 * Button that activates the updates process for a book Search Location tab.
	 */
	@FXML
	private Button btnUpdateLocation;
	/**
	 * Button that activates the delete process for a book in Search Location tab.
	 */
	@FXML
	private Button btnDeleteLocation;

// CREATE TAB
	/**
	 * Text field for the creation of the new book's author's name in Create tab.
	 */
	@FXML
	private TextField txtCreateAuthor;
	/**
	 * Text field for the creation of the new book's title in Create tab.
	 */
	@FXML
	private TextField txtCreateTitle;
	/**
	 * Text field for the creation of the new book's genre in Create tab.
	 */
	@FXML
	private TextField txtCreateGenre;
	/**
	 * Text field for the creation of the new book's location in Create tab.
	 */
	@FXML
	private TextField txtCreateLocation;
	/**
	 * Button that allows the creation of a book entry into the database.
	 */
	@FXML
	private Button btnCreateBook;
//UPDATE TAB
	/**
	 * Text box where the user can edit a current book's author in Update tab.
	 */
	@FXML
	private TextField txtUpdateAuthor;
	/**
	 * Text box where the user can edit a current book's title in Update tab.
	 */
	@FXML
	private TextField txtUpdateTitle;
	/**
	 * Text box where the user can edit a current book's genre in Update tab.
	 */
	@FXML
	private TextField txtUpdateGenre;
	/**
	 * Text box where the user can edit a current book's location in Update tab.
	 */
	@FXML
	private TextField txtUpdateLocation;
	/**
	 * Button that connects with the database and updates book information.
	 */
	@FXML
	private Button btnUpdateBook;
	/**
	 * Created an instance of the Book model to reference to as a field.
	 */
	private Book myBook = new Book();

// METHODS
	/**
	 * Initializes the program with information when it is launched.
	 * @throws SQLException exceptions that can be handled.
	 */
	@FXML
	public void initialize() throws SQLException {
//AUTHOR
		// cannot edit the contents of the table view
		authorTable.setEditable(false);
		// imports all books into Search Author
		authorAuthorC1.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		authorTitleC2.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		authorGenreC3.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		authorLocationC4.setCellValueFactory(new PropertyValueFactory<Book, String>("location"));
//TITLE
		titleTable.setEditable(false);
		titleAuthorC1.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		titleTitleC2.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		titleGenreC3.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		titleLocationC4.setCellValueFactory(new PropertyValueFactory<Book, String>("location"));
//GENRE
		genreTable.setEditable(false);
		genreAuthorC1.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		genreTitleC2.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		genreGenreC3.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		genreLocationC4.setCellValueFactory(new PropertyValueFactory<Book, String>("location"));
//LOCATION
		locationTable.setEditable(false);
		locationAuthorC1.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		locationTitleC2.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		locationGenreC3.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		locationLocationC4.setCellValueFactory(new PropertyValueFactory<Book, String>("location"));
	}
	/**
	 * When the Search button is pressed in any of the four search tabs, the code below
	 * is executed. It decides which tab the search came from, queries the results 
	 * of the search, and displays it in the table view.
	 * 
	 * @throws SQLException exceptions that can be handled.
	 * @param e is an ActionEvent that refers to a search button clicked in a certain search tab.
	 */
	@FXML
	private void handleSearch(final ActionEvent e) throws SQLException {
		List<Book> returnedBooks = new ArrayList<>();
		//temporary placeholders
		TableView<Book> temp1 = new TableView<>();
		TextField temp2 = new TextField();
		
// if-else-if statement to choose which tab the search request came from
		if (((Button) e.getSource()).getId().equals("btnSearchAuthor")) {
		//sets temps to author table and it's search field
			temp1 = authorTable; temp2 = txtSearchAuthor;
			myBook.setAuthor(temp2.getText());
			temp1.getItems().clear();
		//searches for books who meet search criteria
			myBook.getBookByAuthor();
		//adds them to a new list of returned books from the search
			returnedBooks.addAll(myBook.getBookByAuthor());
		//returns the filtered books into the tableView
			temp1.getItems().addAll(returnedBooks);
			
		} else if (((Button) e.getSource()).getId().equals("btnSearchTitle")) {
			temp1 = titleTable; temp2 = txtSearchTitle;
			myBook.setTitle(temp2.getText());
			temp1.getItems().clear();
			myBook.getBookByTitle();
			returnedBooks.addAll(myBook.getBookByTitle());
			temp1.getItems().addAll(returnedBooks);
			
		} else if (((Button) e.getSource()).getId().equals("btnSearchGenre")) {
			temp1 = genreTable; temp2 = txtSearchGenre;
			myBook.setGenre(temp2.getText());
			temp1.getItems().clear();
			myBook.getBookByGenre();
			returnedBooks.addAll(myBook.getBookByGenre());
			temp1.getItems().addAll(returnedBooks);
		
		} else {
			temp1 = locationTable; temp2 = txtSearchLocation;
			myBook.setLocation(temp2.getText());
			temp1.getItems().clear();
			myBook.getBookByLocation();
			returnedBooks.addAll(myBook.getBookByLocation());
			temp1.getItems().addAll(returnedBooks);
		}
		//adds the whole database of books if no search criteria is entered
		if (temp2.getText().isEmpty()) {
			temp1.getItems().addAll(Book.getAllBooks()); 
		}
	}
	/**
	 * This method will disable the buttons to update and delete books within each
	 * of the four searching tabs if no book is selected in the table view.
	 * When a book is selected, the buttons will be enabled in that specific tab.
	 */
	@FXML
	private void disableButtons() {
		//author
    	btnDeleteAuthor.setDisable(true);
    	btnUpdateAuthor.setDisable(true);
	    //title
    	btnDeleteTitle.setDisable(true);
    	btnUpdateTitle.setDisable(true);
	    //genre
    	btnDeleteGenre.setDisable(true);
    	btnUpdateGenre.setDisable(true);
	    //location
    	btnDeleteLocation.setDisable(true);
    	btnUpdateLocation.setDisable(true);
	}
	/**
	 * This button allows the update and delete buttons in each of the four
	 * search tabs to enable when a book selection is made inside their specific
	 * tab.
	 */
	@FXML
	private void enableButtons() {
		
	if (authorTable.getSelectionModel().getSelectedItem() != null) {
		btnDeleteAuthor.setDisable(false);
    	btnUpdateAuthor.setDisable(false);
	}
	if (titleTable.getSelectionModel().getSelectedItem() != null) {
		btnDeleteTitle.setDisable(false);
    	btnUpdateTitle.setDisable(false);
	}
	if (genreTable.getSelectionModel().getSelectedItem() != null) {
    	btnDeleteGenre.setDisable(false);
    	btnUpdateGenre.setDisable(false);
	}
	if (locationTable.getSelectionModel().getSelectedItem() != null) {
    	btnDeleteLocation.setDisable(false);
    	btnUpdateLocation.setDisable(false);
	}
}
	/**
	 * Disables the 'Create' button until all information has been given by the user.
	 */
	@FXML
	private void disableCreateButton() {
		if (!(txtCreateAuthor.getText().isEmpty() || txtCreateTitle.getText().isEmpty()
				|| txtCreateGenre.getText().isEmpty() || txtCreateLocation.getText().isEmpty())) {
			btnCreateBook.setDisable(false);
		} else {
			btnCreateBook.setDisable(true);
		}
	}
	
	/**
	 * Creates a book in the view and injects it into the database.
	 */
	@FXML
	private void handleCreateBook() {
		myBook.setAuthor(txtCreateAuthor.getText());
		myBook.setTitle(txtCreateTitle.getText());
		myBook.setGenre(txtCreateGenre.getText());
		myBook.setLocation(txtCreateLocation.getText());
		myBook.createBook();
		//ALERT BOX
		Alert creationConfirmed = new Alert(AlertType.CONFIRMATION);
		creationConfirmed.setTitle("Book Created");
		creationConfirmed.setContentText("Congratulations! Your book has been created."
				+ " Press OK to either create more books or search for more books within the library.");
		creationConfirmed.showAndWait();

		txtCreateAuthor.clear();
		txtCreateTitle.clear();
		txtCreateGenre.clear();
		txtCreateLocation.clear();
		txtCreateAuthor.requestFocus();
		tabPane.getSelectionModel().select(homeTab);
		
	}
	/**
	 * If the user wants to update a book, this method
	 * will occur once the "Update" button is pressed in that tab.
	 * @param e is an ActionEvent that refers to a search button clicked in a certain search tab.
	 */
	@FXML
	private void handleUpdate(final ActionEvent e) {
		Book temp;
		if (((Button) e.getSource()).getId().equals("btnUpdateAuthor")) {
			temp = authorTable.getSelectionModel().getSelectedItem();
		} else if (((Button) e.getSource()).getId().equals("btnUpdateTitle")) {
			temp = titleTable.getSelectionModel().getSelectedItem();
		} else if (((Button) e.getSource()).getId().equals("btnUpdateGenre")) {
			temp = genreTable.getSelectionModel().getSelectedItem();
		} else {
			temp = locationTable.getSelectionModel().getSelectedItem();
		}
		updateTab.setDisable(false);
		tabPane.getSelectionModel().select(updateTab);
		txtUpdateAuthor.setText(temp.getAuthor());
		txtUpdateTitle.setText(temp.getTitle());
		txtUpdateGenre.setText(temp.getGenre());
		txtUpdateLocation.setText(temp.getLocation());
		btnUpdateBook.setDisable(false);
	}
	/**
	 * This is where the book is actually updated. After the program decides which
	 * tab the request to update book information came from, this method is called
	 * to fulfill the task in the 'Update' tab.
	 * 
	 * @throws SQLException exceptions that can be handled.
	 * @param table is the TableView from the tab the update request came from.
	 * This way, the rowID can be received and the book can be easily updated.
	 */
	@FXML
	private void handleUpdateBook(final TableView<Book> table) throws SQLException {

		myBook.setRowID((table.getSelectionModel().getSelectedItem()).getRowID());
		myBook.setAuthor(txtUpdateAuthor.getText());
		myBook.setTitle(txtUpdateTitle.getText());
		myBook.setGenre(txtUpdateGenre.getText());
		myBook.setLocation(txtUpdateLocation.getText());
		myBook.updateBook();
		// ALERT BOX
		Alert update = new Alert(AlertType.CONFIRMATION);
		update.setTitle("Update Complete");
		update.setContentText("Your book has been successfully updated.");
		update.showAndWait();
		if (!(update.getResult().getButtonData().isCancelButton())) {
		txtUpdateAuthor.clear();
		txtUpdateTitle.clear();
		txtUpdateGenre.clear();
		txtUpdateLocation.clear();
		tabPane.getSelectionModel().select(homeTab);
		btnUpdateBook.setDisable(true);
		updateTab.setDisable(true);
		// clears our table and repopulates with updated entries
		authorTable.getItems().clear();
		titleTable.getItems().clear();
		genreTable.getItems().clear();
		locationTable.getItems().clear();
		table.getItems().addAll(Book.getAllBooks());
		}
	}
	/**
	 * This method is used to determine which tab the request to update book
	 * information has come from by seeing which tab has a selected item inside its
	 * TableView.
	 * @throws SQLException exceptions that can be handled.
	 */
	@FXML
	private void tabToBeUpdated() throws SQLException {		
		if (!(authorTable.getSelectionModel().getSelectedItem() == null)) {
			handleUpdateBook(authorTable);
		} else if (!(titleTable.getSelectionModel().getSelectedItem() == null)) {
			handleUpdateBook(titleTable);
		} else if (!(genreTable.getSelectionModel().getSelectedItem() == null)) {
			handleUpdateBook(genreTable);
		} else if (!(locationTable.getSelectionModel().getSelectedItem() == null)) {
			handleUpdateBook(locationTable);
		}
	}
	/**
	 * Disables the ability to update a book until information is in all four categories
	 * needed to makeup a book.
	 */
	@FXML
	private void disableUpdateBook() {
		if (!(txtUpdateAuthor.getText().isEmpty() || txtUpdateTitle.getText().isEmpty()
				|| txtUpdateGenre.getText().isEmpty() || txtUpdateLocation.getText().isEmpty())) {
			btnUpdateBook.setDisable(false);
		} else {
			btnUpdateBook.setDisable(true);
		}
	}
	/**
	 * Deletes a book from the database in the 'Search Author' tab.
	 * @param e is an ActionEvent that refers to a search button clicked in a certain search tab.
	 */
	@FXML
	private void handleDeleteBook(final ActionEvent e) {
		//temporary placeholders
		Book temp;
		TableView<Book> temp1;
		//if statement to choose which tab the delete request came from
		if (((Button) e.getSource()).getId().equals("btnDeleteAuthor")) {
			temp = authorTable.getSelectionModel().getSelectedItem();
			temp1 = authorTable;
		} else if (((Button) e.getSource()).getId().equals("btnDeleteTitle")) {
			temp = titleTable.getSelectionModel().getSelectedItem();
			temp1 = titleTable;
		} else if (((Button) e.getSource()).getId().equals("btnDeleteGenre")) {
			temp = genreTable.getSelectionModel().getSelectedItem();
			temp1 = genreTable;
		} else {
			temp = locationTable.getSelectionModel().getSelectedItem();
			temp1 = locationTable;
		}
		// ALERT BOX
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		Alert editable = new Alert(AlertType.NONE, "Are you sure?", yes, no);
		editable.setTitle("Delete This Book");
		editable.setContentText("Are you sure you want to delete this book? Click YES to delete"
				+ " a book or NO to continue viewing books.");
		editable.showAndWait().ifPresent(response -> {
			if (response == yes) {
				myBook.setRowID(temp.getRowID());
				myBook.deleteBook();
				temp1.getItems().remove(temp);
			}
		});
	}
}
