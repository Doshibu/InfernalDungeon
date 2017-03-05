package Elements;

/**
 * Énumération des directions possibles.
 * @author Quentin VIGNAUD
 *
 */
public enum Direction {
	NORD,
	EST,
	SUD,
	OUEST;
	
	/**
	 * @return La cardinalité suivante (sens horaire).
	 */
	public Direction next() {
		if (ordinal() < 3) return values()[ordinal() + 1];
		else return NORD;
	}
	
	/**
	 * @return La cardinalité suivante (sens anti-horaire).
	 */
	public Direction before() {
		if (ordinal() > 0) return values()[ordinal() - 1];
		else return OUEST;
	}
}
