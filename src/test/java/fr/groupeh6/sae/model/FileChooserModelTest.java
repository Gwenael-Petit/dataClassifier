package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;

class FileChooserModelTest {
	
	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "DataForTest.csv";
	protected File file = new File(path);
	
	MainModel mainModel = new MainModel();
	MainController mainController = new MainController(mainModel);
	FileChooserModel fcm = new FileChooserModel(mainController, true);
	
	@BeforeEach
	void setup() {
		
		fcm.setFile(file);
		fcm.setDelimiter(',');
	}

	@Test
	void test_set_file() {
		assertEquals(fcm.getFile(), file);
	}
	
	@Test
	void test_set_delimiter() {
		assertEquals(fcm.getDelimiter(), ',');
	}
	
	@Test
	void test() {
		try {
			fcm.loadCSV();
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
