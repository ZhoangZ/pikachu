package view;

public enum IconPikachu {
	One(1,"resources/ic_1.png"),
	TWO(2,"resources/ic_2.png")
	
	;
	public int VALUE;
	public String RESOURCE_PATH;
	private IconPikachu(int value, String resourcePath) {
		this.VALUE= value;
		this.RESOURCE_PATH=resourcePath;
	}
	private IconPikachu() {
		
	}
	
	
}
