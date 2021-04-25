package model;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import view.Config;

public class Ultis {
	public static Step nextStep(Step step) {
		switch (step) {
		case TOP:
			return Step.RIGHT;
		case RIGHT:
			return Step.DOWN;
		case DOWN:
			return Step.LEFT;
		case LEFT:
			return Step.END;
		case START:
			return Step.TOP;
		default:
			return Step.END;
		}
	}

	public static Step backStep(Step step) {
		switch (step) {
		case TOP:
			return Step.DOWN;
		case RIGHT:
			return Step.LEFT;
		case DOWN:
			return Step.TOP;
		case LEFT:
			return Step.RIGHT;
		default:
			return Step.NULL;
		}
	}
	//get Icon
	private static int maxValue=6;
	private static int minValue=1;
	private static int indexIcon=0;
	private static int tempIcon=0;
	public static int getIconPikachu() {
		indexIcon++;
		if(indexIcon%2==1) {
			return nextValue();
		}
		return tempIcon;
	}
	private static int nextValue() {
		if(tempIcon==maxValue) {
			return tempIcon=minValue;
		}
		return tempIcon+=1;
	}
	
	//Random Icon
	public static void randomMatrix(int[][] matrix) {
		for(int i=0;i<matrix.length;i++) {
			int rowIndex= randomMinMax(1,matrix.length-2);
			int colIndex= randomMinMax(1,matrix[rowIndex].length-2);
			int rowIndexAfter= randomMinMax(1,matrix.length-2);
			int colIndexAfter= randomMinMax(1,matrix[rowIndexAfter].length-2);
			int temp = matrix[rowIndex][colIndex];
			matrix[rowIndex][colIndex]=matrix[rowIndexAfter][colIndexAfter];
			matrix[rowIndexAfter][colIndexAfter]=temp;
			//System.out.println(String.format("swap %d:%d -> %d:%d", rowIndex, colIndex, rowIndexAfter, colIndexAfter));
		}
	}
	private static int randomMinMax(int min, int max) {
		return (int) (Math.random()*max) +min;
	}
	public static void drawLine(Group group, Node  node) {
		int rowIndex= node.getCurrentLocation()[0];
		int colIndex= node.getCurrentLocation()[1];
		
		Step key = node.getJustStep();
		Line line = new Line(0, 0, 0, 0);
		//System.out.println(key);
		switch (key) {
		case START: {
			break;
		}
		case RIGHT: {
			
			line = new Line(0, 0, Config.DRAW, 0);
			line.setTranslateX(colIndex*Config.DRAW-Config.DRAW/2);
			line.setTranslateY(rowIndex*Config.DRAW+Config.DRAW/2);
			break;
		}
		case LEFT: {
			
			line = new Line(0, 0, Config.DRAW, 0);
			line.setTranslateX(colIndex*Config.DRAW+Config.DRAW/2);
			line.setTranslateY(rowIndex*Config.DRAW+Config.DRAW/2);
			break;
		}
		case DOWN: {
			
			line = new Line(0, 0, 0, Config.DRAW);
			line.setTranslateX(colIndex*Config.DRAW+Config.DRAW/2);
			line.setTranslateY(rowIndex*Config.DRAW-Config.DRAW/2);
			break;
		}
		case TOP: {
			
			line = new Line(0, 0, 0, Config.DRAW);
			line.setTranslateX(colIndex*Config.DRAW+Config.DRAW/2);
			line.setTranslateY(rowIndex*Config.DRAW+Config.DRAW/2);
			break;
		}
		default:
			break;
		}
		
		group.getChildren().add(line);
		
	}

}
