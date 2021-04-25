package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Board;
import model.ContentNode;
import model.Node;
import model.Step;

public class SubSceneDraw {
	private Group root;
	private SubScene subScene;
	private Board board;
	public SubSceneDraw(Board board) {
		super();
		this.groupContainIcon=new Group();
		this.root = new Group();
		this.root.getChildren().add(groupContainIcon);
		
		this.board=board;
		subScene= new SubScene(root, Config.DRAW*this.board.getMatrix().length, Config.DRAW*this.board.getMatrix().length);
		drawBoard();
	
		
		EventHandler<MouseEvent> listenerMouseClickForOnePeople = new EventHandler<MouseEvent>() {
			
			Node initial;
			@Override
			public void handle(MouseEvent e) {
				double x = e
						.getSceneX() 
						- SubSceneDraw.this.subScene.getLocalToSceneTransform().getTx();
				double y = e
						.getSceneY() 
						- SubSceneDraw.this.subScene.getLocalToSceneTransform().getTy();
				int row_index = (int) (y / Config.DRAW);
				int col_index = (int) (x / Config.DRAW);
				System.out.println(String.format("%d:%d", row_index, col_index));
				if(initial==null) {
					this.initial= new model.Node(SubSceneDraw.this.board,Step.START, new int[] {row_index, col_index}) ; 
				}else {
					
					model.ContentNode contentNode = new ContentNode(this.initial.getValueCurrent(), new int[] {row_index, col_index});
					System.out.println(initial.walking(contentNode));
					this.initial=null;
				}
			}

			private Object ContentNode() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		this.root.addEventHandler(MouseEvent.MOUSE_CLICKED, listenerMouseClickForOnePeople);
	}
	public Group getRoot() {
		return root;
	}
	public void setRoot(Group root) {
		this.root = root;
	}
	public SubScene getSubScene() {
		return subScene;
	}
	public void setSubScene(SubScene subScene) {
		this.subScene = subScene;
	}
	private Group groupContainIcon;
	public void draw(int[] location, IconPikachu icon) {
		try {
			FileInputStream fis;
			int rowIndex= location[0];
			int colIndex= location[1];
			fis = new FileInputStream(new File(icon.RESOURCE_PATH));
			ImagePattern img = new ImagePattern(new Image(fis));
			Rectangle e= new Rectangle(Config.DRAW, Config.DRAW, img);
			e.setTranslateX(rowIndex*Config.DRAW);
			e.setTranslateY(colIndex*Config.DRAW);
			this.groupContainIcon.getChildren().add(e);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void drawBoard() {
		this.root.getChildren().remove(this.groupContainIcon);
		this.groupContainIcon=new Group();
		byte[][] m = this.board.getMatrix();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if(m[i][j]==1) {
					draw(new int[] {i,j}, IconPikachu.One);
				}
				if(m[i][j]==2) {
					draw(new int[] {i,j}, IconPikachu.TWO);
				}
			}
		}
		this.root.getChildren().add(groupContainIcon);
	}
	
	
	

}
