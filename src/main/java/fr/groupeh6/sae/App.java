package fr.groupeh6.sae;

import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.model.Model;
import fr.groupeh6.sae.views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model model = new Model();
		MainController controller = new MainController(model);
		
		new MainView(model, controller);
		//new MainView(model, controller);
		String sep = System.getProperty("file.separator");
		String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
		//model.loadFromFile(path, ',');
		
	}

}
