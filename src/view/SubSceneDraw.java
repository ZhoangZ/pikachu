package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Platform;
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
import model.Ultis;

public class SubSceneDraw {
	private Group root;
	private Group groupContainIcon;
	private Group groupContainLine;
	private SubScene subScene;
	private Board board;
	public SubSceneDraw(Board board) {
		super();
		this.groupContainIcon=new Group();
		this.groupContainLine=new Group();
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
				//System.out.println(String.format("%d:%d", row_index, col_index));
				if(this.initial==null) {
					this.initial= new model.Node(SubSceneDraw.this.board,Step.START, new int[] {row_index, col_index}) ; 
					//System.out.println("null");
				}else {
					//System.out.println("not null");
					model.ContentNode contentNode = new ContentNode(this.initial.getValueCurrent(), new int[] {row_index, col_index});
					//boolean rs=this.initial.walking(contentNode);
					//System.out.println(rs);
					if(this.initial.walking(contentNode)) {
						//contentNode.path().forEach(System.out::println);
						List<Node> track = contentNode.track();
						//System.out.println(String.format("Corect: %d", contentNode.goal));
						SubSceneDraw.this.board.setValue(contentNode.locationGoal, 0);
						SubSceneDraw.this.board.setValue(this.initial.getCurrentLocation(), 0);
						
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Group group = new Group();
								track.forEach(e->{
									Ultis.drawLine(group, e);
								});
								
								Platform.runLater(new Runnable() {
									
									@Override
									public void run() {
										SubSceneDraw.this.root.getChildren().remove(SubSceneDraw.this.groupContainLine); 
										SubSceneDraw.this.groupContainLine=group; 
										SubSceneDraw.this.root.getChildren().add(SubSceneDraw.this.groupContainLine); 
										
									}
								});
								try {
									Thread.sleep(1000);
									Platform.runLater(new Runnable() {
										
										@Override
										public void run() {
											SubSceneDraw.this.drawBoard();
											SubSceneDraw.this.root.getChildren().remove(SubSceneDraw.this.groupContainLine); 
											SubSceneDraw.this.groupContainLine=new Group(); 
											SubSceneDraw.this.root.getChildren().add(SubSceneDraw.this.groupContainLine); 
											
										}
									});
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								
							}
						}).start(); 
					}
					this.initial=null;
				}
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
	
	public void draw(int[] location, IconPikachu icon) {
		try {
			FileInputStream fis;
			fis = new FileInputStream(new File(icon.RESOURCE_PATH));
			ImagePattern img = new ImagePattern(new Image(fis));
			Rectangle e= new Rectangle(Config.DRAW, Config.DRAW, img);
			int rowIndex= location[0];
			int colIndex= location[1];
			e.setTranslateX(colIndex*Config.DRAW);
			e.setTranslateY(rowIndex*Config.DRAW);
			this.groupContainIcon.getChildren().add(e);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void drawBoard() {
		this.root.getChildren().remove(this.groupContainIcon);
		this.groupContainIcon=new Group();
		int[][] m = this.board.getMatrix();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				int key=m[i][j];
				switch (key) {
				case 1: {
					draw(new int[] {i,j}, IconPikachu.One);
					break;
				}
				case 2: {
					draw(new int[] {i,j}, IconPikachu.TWO);
					break;
				}
				case 3: {
					draw(new int[] {i,j}, IconPikachu.THREE);
					break;
				}
				case 4: {
					draw(new int[] {i,j}, IconPikachu.FOR);
					break;
				}
				case 5: {
					draw(new int[] {i,j}, IconPikachu.FIVE);
					break;
				}
				case 6: {
					draw(new int[] {i,j}, IconPikachu.SIX);
					break;
				}
				default:
					break;
				}
				
			}
		}
		this.root.getChildren().add(groupContainIcon);
	}
	
	
	

}
