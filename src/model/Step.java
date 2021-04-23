package model;

public enum Step {
	LEFT(new byte[] {0,-1}),
	RIGHT(new byte[] {0,1}),
	TOP(new byte[] {-1,0}), 
	DOWN(new byte[] {1,0}),
	START,
	END,
	NULL
	;
	
	public byte[] VECTOR;
	private Step(byte[] vector) {
		this.VECTOR=vector;
	}
	private Step() {
		
	}
}
