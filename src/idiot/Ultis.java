package idiot;

public class Ultis {
	public static Step nextStep(Step step) {
		switch (step) {
		case TOP:
			return Step.RIGHT;
		case RIGHT:
			return Step.DOWN;
		case DOWN:
			return Step.LEFT;
		case LEFT:
			return Step.END;
		case START:
			return Step.TOP;
		default:
			return Step.END;
		}
	}

	public static Step backStep(Step step) {
		switch (step) {
		case TOP:
			return Step.DOWN;
		case RIGHT:
			return Step.LEFT;
		case DOWN:
			return Step.TOP;
		case LEFT:
			return Step.RIGHT;
		default:
			return Step.NULL;
		}
	}

}
