package fr.groupeh6.sae.views;

import java.io.File;
import java.io.IOException;

import fr.groupeh6.sae.controllers.FileChooserController;
import fr.groupeh6.sae.model.FileChooserModel;
import fr.groupeh6.sae.model.NotSameTypeException;
import fr.groupeh6.sae.model.TypeNotRegisteredException;
import fr.groupeh6.sae.model.utils.Observer;
import fr.groupeh6.sae.model.utils.AbstractSubject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class FileChooserView extends AbstractModalView implements Observer {
	
	private Window owner;
	
	private FileChooserModel model;
	private FileChooserController controller;
	
	private FileChooser chooser = new FileChooser();
	
	@FXML
	private VBox vBox;
	@FXML
	private Label fileLabel;
	@FXML
	private Button bFileChooser, bCharger;
	@FXML
	private TextField tfDelimiter;
	
	public FileChooserView(Window owner, FileChooserModel model, FileChooserController controller) {
		super(owner);
		this.owner = owner;
		this.model = model;
		this.controller = controller;
		model.attach(this);
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("filechooserview.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root, 300, 300);
			this.setScene(scene);
			this.setTitle("CSV Loader");
			this.show();
			
			chooser.setTitle("Select CSV");
			chooser.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
			tfDelimiter.setDisable(true);
			bCharger.setDisable(true);
			initEvents();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initEvents() {
		tfDelimiter.textProperty().addListener((obs, oldV, newV) -> {
			if(newV.length() > 1) tfDelimiter.setText(oldV);
			else if(newV.length() == 0) bCharger.setDisable(true);
			else controller.setDelimiter(newV);
		});
		bFileChooser.setOnAction(e -> {
			File file = chooser.showOpenDialog(this);
			if(file != null) this.controller.setFile(file);
		});
		vBox.setOnDragOver(e -> {
			if(e.getGestureSource() != vBox && e.getDragboard().hasFiles()) e.acceptTransferModes(TransferMode.COPY);
			e.consume();
		});
		vBox.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
			if(db.hasFiles()) {
				this.controller.setFile(db.getFiles().get(0));
				success = true;
			}
			e.setDropCompleted(success);
			e.consume();
		});
		bCharger.setOnAction(e -> {
			try {
				this.controller.load();
			} catch (NotSameTypeException ex) {
				new ErrorView(owner, "Le type n'est pas compatible avec le mod??le actuel.");
			} catch (IOException ex) {
				new ErrorView(owner, "Une erreur est survenu durant la lecture des donn??es.");
			} catch (TypeNotRegisteredException ex) {
				new ErrorView(owner, "Le type n'est pas reconnu par le programme.");
			}
			this.close();
		});
	}

	@Override
	public void update(AbstractSubject subj) {
		if(model.getFile() != null) {
			fileLabel.setText("Fichier s??lectionner : " + model.getFile().getName());
			tfDelimiter.setDisable(false);
		}
		if(model.getDelimiter() != null) {
			bCharger.setDisable(false);
		}
	}


	@Override
	public void update(AbstractSubject subj, Object data) {
		update(subj);
	}

}
