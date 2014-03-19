
/**
 * A card object which for now just contains a name
 *
 * @author vanakema.
 *         Created Mar 19, 2014.
 */
public class Card {

	/**
	 * Contains the name of the card
	 */
	private String name;
	
	/**
	 * Default constructor
	 *
	 * @param name Name of the card to be construted
	 */
	public Card(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the card
	 *
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
}
