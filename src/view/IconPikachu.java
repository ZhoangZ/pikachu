package view;

public enum IconPikachu {
	One(1,"resources/ic_1.png"),
	TWO(2,"resources/ic_2.png"),
	THREE(3,"resources/ic_3.png"),
	FOR(4,"resources/ic_4.png"),
	FIVE(5,"resources/ic_5.png"),
	SIX(6,"resources/ic_6.png"),
	
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
