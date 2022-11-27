package fr.groupeh6.sae;

import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.model.MainModel;
import fr.groupeh6.sae.views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainModel model = new MainModel();
		MainController controller = new MainController(model);
		
		new MainView(model, controller);
		//new MainView(model, controller);
	}

}
