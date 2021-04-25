package model;

public class Board {
	private int[][] matrix;
	
	public Board() {
		super();
	}
	

	public Board(int cols, int rows) {
		super();
		this.matrix = new int[rows][cols];
	}


	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void setValue(int [] location, int value) {
		
		this.matrix[location[0]][location[1]]=value;
		//System.out.println(this.toString());
	}
	@Override
	public String toString() {
		StringBuffer stringBuffer= new StringBuffer();
		stringBuffer.append("Board: \n");
		stringBuffer.append("=========================\n");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				stringBuffer.append(matrix[i][j]+"\t");
			}
			stringBuffer.append("\n");
		}
		stringBuffer.append("=========================");
		return stringBuffer.toString();
	}
	public Board clone() {
		Board board = new Board(this.matrix[0].length, this.matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				board.matrix[i][j]= this.matrix[i][j];
			}
			
		}	
		return board;
	}
	

}
