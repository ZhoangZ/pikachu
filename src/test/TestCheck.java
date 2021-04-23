package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Board;
import model.Node;
import model.Step;

public class TestCheck {

	@Test
	public void test() {
		Board board = new Board(4, 4);
		Node initialNode= new Node(board, Step.START, new int[] {0,0});
		assertTrue(initialNode.checkCurrentLocationCanWalking());
		System.out.println(board);
	}

}
