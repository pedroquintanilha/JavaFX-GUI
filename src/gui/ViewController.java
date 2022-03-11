package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Person> comboBoxPerson;

	private ObservableList<Person> obsList;

	@FXML
	private Button btAll;
	
	@FXML
	private TableView<Person> table;
	
	@FXML
	private TableColumn<Person, Integer> id;
	
	@FXML
	private TableColumn<Person, String> name;
	
	@FXML
	private TableColumn<Person, String> email;
	
	@FXML
	public void onComboBoxPersonAction() {
		
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
		Alerts.showAlert("You selected an item!", "This is just a confirmation.","Selected name: " + person.getName(), AlertType.CONFIRMATION);
		
	}

	@FXML
	public void onBtAllAction() {
		
		id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		table.setItems(obsList);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Pedro", "pedro@gmail.com"));
		list.add(new Person(2, "Lucas", "lucas@gmail.com"));
		list.add(new Person(3, "Carlos", "carlos@gmail.com"));
		list.add(new Person(4, "Elizete", "elizete@gmail.com"));

		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList);

		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));

	}
}
