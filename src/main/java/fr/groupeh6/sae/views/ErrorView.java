package fr.groupeh6.sae.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Window;

public class ErrorView extends AbstractModalView {

	public ErrorView(Window owner, String message) {
		super(owner);
		Label label = new Label(message);
		VBox.setVgrow(label, Priority.ALWAYS);
		VBox.setMargin(label, new Insets(10, 5, 0, 5));
		label.setWrapText(true);
		label.setTextAlignment(TextAlignment.CENTER);
		Button accept = new Button("Ok !");
		VBox.setMargin(accept, new Insets(10, 0, 10, 0));
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(label, accept);
		Scene scene = new Scene(vBox, 200, 200);
		this.setScene(scene);
		this.setTitle("Erreur");
		this.setResizable(false);
		this.show();
		
		accept.setOnAction(e -> this.close());
	}

}
