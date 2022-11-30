package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.datas.iris.IrisDataset;

class NewPointModelTest {

	MainModel model = new MainModel();
	NewPointModel npm;
	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	
	@BeforeEach
	void setup() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		model.loadFromFile(path, ',', false);
		npm = new NewPointModel(model);
	}
	
	@Test
	void test_setData() {
		assertEquals(null, npm.datas[1]);
		npm.setData(1, "2.4");
		assertEquals("2.4", npm.datas[1]);
	}
	
	@Test
	void test_loadPoint() throws IOException, TypeNotRegisteredException, NotSameTypeException {
		npm.setData(0, "3.2");
		npm.setData(1, "2.4");
		npm.setData(2, "5.1");
		npm.setData(3, ".4");
		npm.setData(4, "SETOSA");
		npm.loadPoint();
		MainModel otherModel = new MainModel();
		otherModel.loadFromFile(path, ',', false);
		otherModel.loadFromString("3.2, 2.4, 5.1, .4,Setosa", ',');
		assertFalse(npm.mainModel.equals(otherModel));
	}
	
	@Test
	void test_getType() {
		assertEquals(new IrisDataset().name(), npm.getType().name());
	}

}
