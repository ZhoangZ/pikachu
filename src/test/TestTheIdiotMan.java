package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import model.Board;
import model.ContentNode;
import model.Node;
import model.Step;

public class TestTheIdiotMan {
	@Test
	public void testSuccess() {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[2][2]=1;
		//System.out.println(board);
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent());
		assertTrue(initialNode.walking(contentNode));
	}
	@Test
	public void testSuccess1() {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[2][2]=1;
		board.getMatrix()[0][2]=2;
		
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent());
		assertTrue(initialNode.walking(contentNode));
		List<Step> path = contentNode.path();
//		System.out.println(board);
//		path.forEach(e->{
//			System.out.print(e+"\t");
//		});
	}
	@Test
	public void testSuccess2() {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;
		board.getMatrix()[2][2]=1;
		board.getMatrix()[0][2]=2;
		board.getMatrix()[1][3]=2;
		
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent());
		assertTrue(initialNode.walking(contentNode));
		List<Step> path = contentNode.path();
		System.out.println(board);
		path.forEach(e->{
			System.out.print(e+"\t");
		});
	}
	@Test
	public void testFailture() {
		Board board = new Board(4, 4);
		board.getMatrix()[1][1]=1;		
		//System.out.println(board);
		Node initialNode= new Node(board, Step.START, new int[] {1,1});
		ContentNode contentNode = new ContentNode(initialNode.getValueCurrent());
		assertFalse(initialNode.walking(contentNode));
	}
}
