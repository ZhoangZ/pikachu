package main;

import java.util.List;

import model.Board;
import model.ContentNode;
import model.Node;
import model.Step;

public class Main {
	public static void main(String[] args) {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[2][2]=1;
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		System.out.println(board);
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent());
		boolean rs = initialNode.walking(contentNode);
		System.out.println(rs);
		List<Step> path = contentNode.path();
		path.forEach(e->{
			System.out.print(e+"\t");
		});
	}

}
