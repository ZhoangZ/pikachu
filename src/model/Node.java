package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Board board;
	private Step justStep;
	private Step pointStep;
	private Node parent;
	// private int turnNum;
	private List<Node> lstChild;
	private int[] currentLocation;

	public Node() {
		super();
		this.pointStep = Step.START;
		this.currentLocation = new int[2];
		lstChild = new ArrayList<>();
	}

	public Node(Board board, Step justStep, int[] currentLocation) {
		super();
		this.board = board;
		this.justStep = justStep;
		this.pointStep = Step.START;
		this.currentLocation = currentLocation;
		lstChild = new ArrayList<>();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Step getJustStep() {
		return justStep;
	}

	public void setJustStep(Step justStep) {
		this.justStep = justStep;
	}

	public boolean walking(ContentNode contentNode) {
		Step nextStep = Ultis.nextStep(this.pointStep);
		if (nextStep == Ultis.backStep(this.justStep)) {
			this.pointStep = nextStep;
			
			return this.walking(contentNode);
		}
		if (nextStep == Step.END) {
			if (this.parent != null) {
//				System.out.println("Back");
//				System.out.println(
//						String.format("%d-%d", this.parent.currentLocation[0], this.parent.currentLocation[1]));
				if (this.justStep != this.parent.justStep) {
					contentNode.turnNum--;
				}
				return this.parent.walking(contentNode);
			}

			return false;
		}

		Node child = this.clone();
		child.currentLocation[0] += nextStep.VECTOR[0];
		child.currentLocation[1] += nextStep.VECTOR[1];
		this.pointStep = nextStep;
		if (!child.checkCurrentLocationCanWalking()) {
			// if(this.parent!=null)
			
			return this.walking(contentNode);
		}else {
			//Kiểm tra có chướng ngại vật hay không
			//Check for obstacles or not
			if((child.getValueCurrent()!=contentNode.goal
					||!(child.getCurrentLocation()[1]==contentNode.locationGoal[1]
					&&child.getCurrentLocation()[0]==contentNode.locationGoal[0])
					)
					&&child.getValueCurrent()!=0
							) {
				return this.walking(contentNode);
			}
		}
		if (this.justStep != nextStep) {
			contentNode.turnNum++;
		}
		if (contentNode.turnNum > 3) {
			// if(this.parent!=null)
			contentNode.turnNum--;
			
			return this.walking(contentNode);
		}

		child.parent = this;
		this.lstChild.add(child);
		child.justStep = nextStep;
//		Board tmp = this.board.clone();
//		tmp.getMatrix()[child.currentLocation[0]][child.currentLocation[1]]=1;
//		System.out.println(tmp);
//		System.out.println(nextStep);
//		System.out.println(String.format("%d-%d", child.currentLocation[0], child.currentLocation[1]));
//		
		if(child.getValueCurrent()!=contentNode.goal
				||child.getCurrentLocation()[0]!=contentNode.locationGoal[0]
						||child.getCurrentLocation()[1]!=contentNode.locationGoal[1]
				) {
			
			return child.walking(contentNode);
			
		}
		//System.out.println("GOAL");
		contentNode.setNodeGoal(child);
		return true;

	}

	public Node clone() {
		Node node = new Node(this.board.clone(), justStep, currentLocation.clone());
		return node;
	}

	public boolean checkCurrentLocationCanWalking() {
		if (this.board.getMatrix().length <= this.currentLocation[0]
				|| this.board.getMatrix()[0].length <= this.currentLocation[1] || 0 > this.currentLocation[1]
				|| 0 > this.currentLocation[0]
						//||this.getValueCurrent()!=0
		) {
			return false;
		}
		return true;
	}
	public int getValueCurrent() {
		return this.board.getMatrix()[this.currentLocation[0]][this.currentLocation[1]];
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getLstChild() {
		return lstChild;
	}

	public void setLstChild(List<Node> lstChild) {
		this.lstChild = lstChild;
	}

	public int[] getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int[] currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	

}
