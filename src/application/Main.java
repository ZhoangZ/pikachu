package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Board;
import view.SubSceneDraw;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/main.fxml"));
		BorderPane root = loader.load();
		Board board= new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[2][2]=1;
		board.getMatrix()[1][2]=2;
		board.getMatrix()[2][1]=2;
		SubSceneDraw subScene = new SubSceneDraw(board);
		root.setCenter(subScene.getSubScene());
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				primaryStage.close();
				System.exit(0);
			}
		});
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
