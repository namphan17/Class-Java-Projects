package uno;

import java.util.List;

public class dumb_UnoPlayer implements UnoPlayer {

	/**
	 * play - This method is called when it's your turn and you need to choose
	 * what card to play.
	 *
	 * The hand parameter tells you what's in your hand. It is a List (which we
	 * will talk more about later this term) of Cards. What you need to know now
	 * is that it is similar to an array, and you can call methods such as
	 * hand.size() for the number of cards in your hand and hand.get(i) to
	 * access the ith card in your hand.
	 * 
	 * You can call getColor(), getRank(), and getNumber() on each of the cards
	 * that your hand contains to see what it is. The color will be the color of
	 * the card, or "Color.NONE" if the card is a wild card. The rank will be
	 * "Rank.NUMBER" for all numbered cards, and another value (e.g.,
	 * "Rank.SKIP," "Rank.REVERSE," etc.) for special cards. The value of a
	 * card's "number" only has meaning if it is a number card. (Otherwise, it
	 * will be -1.)
	 *
	 * The upCard parameter works the same way, and tells you what the up card
	 * (in the middle of the table) is.x The calledColor parameter only has
	 * meaning if the up card is a wild, and tells you what color the player who
	 * played that wild card called.
	 *
	 * Finally, the state parameter is a GameState object on which you can
	 * invoke methods if you choose to access certain detailed information about
	 * the game (like who is currently ahead, what colors each player has
	 * recently called, etc.)
	 *
	 * You must return a value from this method indicating which card you wish
	 * to play. If you return a number 0 or greater, that means you want to play
	 * the card at that index. If you return -1, that means that you cannot play
	 * any of your cards (none of them are legal plays) in which case you will
	 * be forced to draw a card (this will happen automatically for you.)
	 */
	private static int max(int a, int b, int c, int d) {
		
		int m0 = Math.max(a, b);
		int m1 = Math.max(c, d);
		
		return Math.max(m0, m1);
	}
	public int play(List<Card> hand, Card upCard, Color calledColor,
			GameState state) {

		// THIS IS WHERE YOUR AMAZING CODE GOES
		Rank r = upCard.getRank(); // Get the upcard's rank.
		int numCard = hand.size();
		int num;

		if (r == Rank.WILD || r == Rank.WILD_D4) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getColor() == calledColor
						|| hand.get(i).getRank() == Rank.WILD
						|| hand.get(i).getRank() == Rank.WILD_D4) {
					return i;
				}
			}
		}

		if (r == Rank.NUMBER) {
			num = upCard.getNumber(); // Get the upcard's number (if its rank is
										// number).
			for (int i = 0; i < hand.size(); i++) {
				// check if any card in our hand has either the same color or
				// the same number as the upcard.
				if (hand.get(i).getNumber() == num
						|| hand.get(i).getColor() == upCard.getColor()
						|| hand.get(i).getRank() == Rank.WILD
						|| hand.get(i).getRank() == Rank.WILD_D4) {
					return i;
				}
			}
			return -1;
		}
		for (int i = 0; i < hand.size(); i++) {
			// check if any card in our hand has the same coloras the upcard.
			if (hand.get(i).getRank() == r
					|| hand.get(i).getColor() == upCard.getColor()
					|| hand.get(i).getRank() == Rank.WILD
					|| hand.get(i).getRank() == Rank.WILD_D4) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * callColor - This method will be called when you have just played a wild
	 * card, and is your way of specifying which color you want to change it to.
	 *
	 * You must return a valid Color value from this method. You must not return
	 * the value Color.NONE under any circumstances.
	 */
	public Color callColor(List<Card> hand) {

		// THIS IS WHERE YOUR AMAZING CODE GOES
		
		return Color.YELLOW;

	}

}
