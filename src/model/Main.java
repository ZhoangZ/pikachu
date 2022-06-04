package model;

import java.util.List;



public class Main {
	public static void main(String[] args) {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[1][2]=1;
		board.getMatrix()[2][2]=1;
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		System.out.println(board);
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent(), new int[] {2,2} );
		boolean rs = initialNode.walking(contentNode);
		System.out.println(rs);
		List<Step> path = contentNode.path();
		path.forEach(e->{
			System.out.print(e+"\t");
		});
	}

}
