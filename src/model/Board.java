package model;

public class Board {
	private byte[][] matrix;
	
	public Board() {
		super();
	}
	

	public Board(int cols, int rows) {
		super();
		this.matrix = new byte[rows][cols];
	}


	public byte[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(byte[][] matrix) {
		this.matrix = matrix;
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
