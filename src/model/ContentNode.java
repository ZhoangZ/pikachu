package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentNode {
	public int turnNum;
	public int goal;
	public int[] locationGoal;
	private Node nodeGoal;
	public ContentNode(int goal, int[] location) {
		super();
		this.goal=goal;
		this.locationGoal=location;
	}

	public int getTurnNum() {
		return turnNum;
	}

	public void setTurnNum(int turnNum) {
		this.turnNum = turnNum;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public Node getNodeGoal() {
		return nodeGoal;
	}

	public void setNodeGoal(Node nodeGoal) {
		this.nodeGoal = nodeGoal;
	}
	public List<Step> path(){
		ArrayList rs = new ArrayList<>();
		if(nodeGoal!=null) {
			Node tmp = nodeGoal;
			while(tmp!=null) {
				//System.out.println(tmp.getJustStep());
				rs.add(tmp.getJustStep());
				tmp=tmp.getParent();
			}
		}
		Collections.reverse(rs);
		return rs;
	}
	public List<Node> track(){
		ArrayList rs = new ArrayList<>();
		if(nodeGoal!=null) {
			Node tmp = nodeGoal;
			while(tmp!=null) {
				rs.add(tmp);
				tmp=tmp.getParent();
			}
		}
		Collections.reverse(rs);
		return rs;
	}

}
