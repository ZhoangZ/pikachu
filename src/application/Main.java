package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Board;
import model.Ultis;
import view.SubSceneDraw;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/main.fxml"));
		BorderPane root = loader.load();
		Board board= new Board(6, 6);
		int[][] array = board.getMatrix();
		for (int i = 1; i < array.length-1; i++) {
			for (int j = 1; j < array[i].length-1; j++) {
				int value=Ultis.getIconPikachu();
				//System.out.println(value);
				board.getMatrix()[i][j]=value;
				
			}
		}
		Ultis.randomMatrix(array);
	
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
