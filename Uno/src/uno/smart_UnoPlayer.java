package uno;

import java.util.List;

public class smart_UnoPlayer implements UnoPlayer {

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

	/*************************************************************************
	 * Helper functions
	 *************************************************************************/

	private static int max(int a, int b, int c, int d) {

		int m0 = Math.max(a, b);
		int m1 = Math.max(c, d);

		return Math.max(m0, m1);
	}

	private static Color mostValue(List<Card> hand) {
		/* This method tells us which color we have most */
		int b = 0;
		int g = 0;
		int r = 0;
		int y = 0;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getColor() == Color.BLUE) {
				if (hand.get(i).getRank() == Rank.NUMBER)
					b++;
				else
					b = +2;
			}
			if (hand.get(i).getColor() == Color.GREEN) {
				if (hand.get(i).getRank() == Rank.NUMBER)
					b++;
				else
					g = +2;
			}
			if (hand.get(i).getColor() == Color.RED) {
				if (hand.get(i).getRank() == Rank.NUMBER)
					b++;
				else
					r = +2;
			}
			if (hand.get(i).getColor() == Color.YELLOW) {
				if (hand.get(i).getRank() == Rank.NUMBER)
					b++;
				else
					y = +2;
			}
		}
		int max = max(b, g, r, y);
		if (b == max)
			return Color.BLUE;
		if (g == max)
			return Color.GREEN;
		if (r == max)
			return Color.RED;
		return Color.YELLOW;
	}

	private static boolean hasThisCard(List<Card> hand, Rank r, Color c) {

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == r && hand.get(i).getColor() == c)
				return true;
		}
		return false;
	}

	private static boolean hasThisNumCard(List<Card> hand, int n, Color c) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == Rank.NUMBER
					&& hand.get(i).getColor() == c)
				if (hand.get(i).getNumber() == n)
					return true;
		}
		return false;
	}

	private static int playCnum(List<Card> hand, int n, Color c) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == Rank.NUMBER
					&& hand.get(i).getColor() == c)
				if (hand.get(i).getNumber() == n)
					return i;
		}
		return -1;
	}

	private static boolean hasThisSpecialRank(List<Card> hand, Rank r) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == r) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasThisNum(List<Card> hand, int n) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == Rank.NUMBER) {
				if (hand.get(i).getNumber() == n)
					return true;
			}
		}
		return false;
	}

	private static int playC(List<Card> hand, Rank r, Color c) {

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == r && hand.get(i).getColor() == c)
				return i;
		}
		return -1;
	}

	private static int playThisRank(List<Card> hand, Rank r) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == r)
				return i;
		}
		return -1;
	}

	private static boolean hasThisColor(List<Card> hand, Color c) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getColor() == c)
				return true;
		}
		return false;
	}

	private static int playThisNum(List<Card> hand, int n) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == Rank.NUMBER)
				if (hand.get(i).getNumber() == n)
					return i;

		}
		return -1;
	}

	private static int maxInColor(List<Card> hand, Color c) {
		int max = 0;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getColor() == c) {
				if (hand.get(i).getRank() == Rank.NUMBER) {
					if (hand.get(i).getNumber() > max) {
						max = hand.get(i).getNumber();
					}
				}
			}
		}
		return max;// highest number in a Color.
	}

	/*************************************************************************
	 * The Play() functions
	 *************************************************************************/

	public int play(List<Card> hand, Card upCard, Color calledColor,
			GameState state) {

		// THIS IS WHERE YOUR AMAZING CODE GOES
		Rank r = upCard.getRank(); // Get the upcard's rank.

		
		Color mostC = mostValue(hand);
		

		if (hasThisColor(hand, upCard.getColor())) {
			if (r == Rank.WILD || r == Rank.WILD_D4) {
				if (hasThisColor(hand, calledColor)) {
					if (hasThisCard(hand, Rank.DRAW_TWO, calledColor)) {
						return playC(hand, Rank.DRAW_TWO, calledColor);
					}
					if (hasThisCard(hand, Rank.SKIP, calledColor)) {
						return playC(hand, Rank.SKIP, calledColor);
					}
					if (hasThisCard(hand, Rank.REVERSE, calledColor)) {
						return playC(hand, Rank.REVERSE, calledColor);
					}
					int m = maxInColor(hand, calledColor);
					return playCnum(hand, m, calledColor);
				}
				if (hasThisCard(hand, Rank.WILD_D4, Color.NONE)) {
					return playC(hand, Rank.WILD_D4, Color.NONE);
				}
				if (hasThisCard(hand, Rank.WILD, Color.NONE)) {
					return playC(hand, Rank.WILD, Color.NONE);
				}
				return -1;
			}
			if (r == Rank.NUMBER) {
				if (hasThisNum(hand, upCard.getNumber())) {
					if (hand.size() < 4) {
						if (hasThisNumCard(hand, 0, upCard.getColor())) {
							return playCnum(hand, 0, upCard.getColor());
						}
						if (hasThisCard(hand, Rank.DRAW_TWO, upCard.getColor())) {
							return playC(hand, Rank.DRAW_TWO, upCard.getColor());
						}
						if (hasThisNumCard(hand, upCard.getNumber(), mostC)) {
							return playCnum(hand, upCard.getNumber(), mostC);
						}
						if (hasThisCard(hand, Rank.SKIP, upCard.getColor())) {
							return playC(hand, Rank.SKIP, upCard.getColor());
						}
						if (hasThisCard(hand, Rank.REVERSE, upCard.getColor())) {
							return playC(hand, Rank.REVERSE, upCard.getColor());
						}
						int m = maxInColor(hand, upCard.getColor());
						return playCnum(hand, m, upCard.getColor());
					}
					if (hasThisNumCard(hand, upCard.getNumber(), mostC)) {
						return playCnum(hand, upCard.getNumber(), mostC);
					}
					return playThisNum(hand, upCard.getNumber());
				}
				if (hand.size() < 4) {
					if (hasThisNumCard(hand, 0, upCard.getColor())) {
						return playCnum(hand, 0, upCard.getColor());
					}
					if (hasThisCard(hand, Rank.DRAW_TWO, upCard.getColor())) {
						return playC(hand, Rank.DRAW_TWO, upCard.getColor());
					}
					if (hasThisNumCard(hand, upCard.getNumber(), mostC)) {
						return playCnum(hand, upCard.getNumber(), mostC);
					}
					if (hasThisCard(hand, Rank.SKIP, upCard.getColor())) {
						return playC(hand, Rank.SKIP, upCard.getColor());
					}
					if (hasThisCard(hand, Rank.REVERSE, upCard.getColor())) {
						return playC(hand, Rank.REVERSE, upCard.getColor());
					}
					int m = maxInColor(hand, upCard.getColor());
					return playCnum(hand, m, upCard.getColor());
				}
				if (hasThisCard(hand, Rank.DRAW_TWO, upCard.getColor())) {
					return playC(hand, Rank.DRAW_TWO, upCard.getColor());
				}
				if (hasThisCard(hand, Rank.SKIP, upCard.getColor())) {
					return playC(hand, Rank.SKIP, upCard.getColor());
				}
				if (hasThisCard(hand, Rank.REVERSE, upCard.getColor())) {
					return playC(hand, Rank.REVERSE, upCard.getColor());
				}
				int m = maxInColor(hand, upCard.getColor());
				return playCnum(hand, m, upCard.getColor());
			}

			if (hasThisSpecialRank(hand, r)) {
				if (hand.size()<4) {
					if (hasThisNumCard(hand, 0, upCard.getColor())) {
						return playCnum(hand, 0, upCard.getColor());
					}
					if (hasThisCard(hand, Rank.DRAW_TWO, upCard.getColor())) {
						return playC(hand, Rank.DRAW_TWO, upCard.getColor());
					}
					return playThisRank(hand, r);
				}
				if (hasThisCard(hand, r, mostC)) {
					return playC(hand, r, mostC);
				}
				return playThisRank(hand, r);

			}
			if (hasThisCard(hand, Rank.DRAW_TWO, upCard.getColor())) {
				return playC(hand, Rank.DRAW_TWO, upCard.getColor());
			}
			if (hasThisCard(hand, Rank.SKIP, upCard.getColor())) {
				return playC(hand, Rank.SKIP, upCard.getColor());
			}
			if (hasThisCard(hand, Rank.REVERSE, upCard.getColor())) {
				return playC(hand, Rank.REVERSE, upCard.getColor());
			}
			int m = maxInColor(hand, upCard.getColor());
			return playCnum(hand, m, upCard.getColor());

		} else {
			if (r == Rank.WILD || r == Rank.WILD_D4) {
				if (hasThisColor(hand, calledColor)) {
					if (hand.size()<4) {
						if (hasThisNumCard(hand, 0, calledColor)) {
							return playCnum(hand, 0, calledColor);
						}
						if (hasThisCard(hand, Rank.DRAW_TWO, calledColor)) {
							return playC(hand, Rank.DRAW_TWO, calledColor);
						}
						if (hasThisCard(hand, Rank.SKIP, calledColor)) {
							return playC(hand, Rank.SKIP, calledColor);
						}
						if (hasThisCard(hand, Rank.REVERSE, calledColor)) {
							return playC(hand, Rank.REVERSE, calledColor);
						}
						int m = maxInColor(hand, calledColor);
						return playCnum(hand, m, calledColor);
					}
					if (hasThisCard(hand, Rank.DRAW_TWO, calledColor)) {
						return playC(hand, Rank.DRAW_TWO, calledColor);
					}
					if (hasThisCard(hand, Rank.SKIP, calledColor)) {
						return playC(hand, Rank.SKIP, calledColor);
					}
					if (hasThisCard(hand, Rank.REVERSE, calledColor)) {
						return playC(hand, Rank.REVERSE, calledColor);
					}
					int m = maxInColor(hand, calledColor);
					return playCnum(hand, m, calledColor);
				}
				if (hasThisCard(hand, Rank.WILD_D4, Color.NONE)) {
					return playC(hand, Rank.WILD_D4, Color.NONE);
				}
				if (hasThisCard(hand, Rank.WILD, Color.NONE)) {
					return playC(hand, Rank.WILD, Color.NONE);
				}
				return -1;
			}

			if (r == Rank.NUMBER) {
				if (hasThisNum(hand, upCard.getNumber())) {
					if (hasThisNumCard(hand, upCard.getNumber(), mostC)) {
						return playCnum(hand, upCard.getNumber(), mostC);
					}
					return playThisNum(hand, upCard.getNumber());
				}
				if (hasThisCard(hand, Rank.WILD_D4, Color.NONE)) {
					return playC(hand, Rank.WILD_D4, Color.NONE);
				}
				if (hasThisCard(hand, Rank.WILD, Color.NONE)) {
					return playC(hand, Rank.WILD, Color.NONE);
				}
				return -1;
			}
			if (hasThisSpecialRank(hand, r)) {
				if (hasThisCard(hand, r, mostC)) {
					return playC(hand, r, mostC);
				}
				return playThisRank(hand, r);
			}
			if (hasThisCard(hand, Rank.WILD_D4, Color.NONE)) {
				return playC(hand, Rank.WILD_D4, Color.NONE);
			}
			if (hasThisCard(hand, Rank.WILD, Color.NONE)) {
				return playC(hand, Rank.WILD, Color.NONE);
			}
			return -1;
		}

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
		/*
		 * Basically, my idea is to call the color that I have most cards in my
		 * hand. If it turns out to be 2 or more colors, I want to call the
		 * color that I have more big numbers than others. In doing so I will
		 * make the opponents win as less points as possible(keep the small
		 * number, play the higher number
		 */
		int blue = 0;
		int green = 0;
		int red = 0;
		int yellow = 0;
		int b = 0;
		int g = 0;
		int r = 0;
		int y = 0;
		for (int i = 0; i < hand.size(); i++) {
			Color a = hand.get(i).getColor();
			if (a == Color.BLUE) {
				blue++;
				if (hand.get(i).getRank() == Rank.NUMBER) {
					b += hand.get(i).getNumber();
				}
			}
			if (a == Color.GREEN) {
				green++;
				if (hand.get(i).getRank() == Rank.NUMBER) {
					g += hand.get(i).getNumber();
				}
			}
			if (a == Color.RED) {
				red++;
				if (hand.get(i).getRank() == Rank.NUMBER) {
					r += hand.get(i).getNumber();
				}
			}

			if (a == Color.YELLOW) {
				yellow++;
				if (hand.get(i).getRank() == Rank.NUMBER) {
					y += hand.get(i).getNumber();
				}
			}
		}

		int max = max(blue, green, red, yellow);// From each color, get the
												// highest number of
		// cards overall.
		int high = max(b, g, r, y);// Highest total values for each color.
		// Call the color that we have the most.
		if (blue == green && green == red && red == yellow) {

			if (b == high)
				return Color.BLUE;
			if (g == high)
				return Color.GREEN;
			if (r == high)
				return Color.RED;
			return Color.YELLOW;
		} else {
			// 2 colors have most cards.
			if (blue == green && blue == max) {
				if (b == high)
					return Color.BLUE;
				else
					return Color.GREEN;
			}
			if (blue == red && blue == max) {
				if (b == high)
					return Color.BLUE;
				else
					return Color.RED;
			}
			if (blue == yellow && blue == max) {
				if (b == high)
					return Color.BLUE;
				else
					return Color.YELLOW;
			}
			if (green == red && green == max) {
				if (g == high)
					return Color.GREEN;
				else
					return Color.RED;
			}
			if (green == yellow && green == max) {
				if (g == high)
					return Color.GREEN;
				else
					return Color.YELLOW;
			}
			if (red == yellow && red == max) {
				if (r == high)
					return Color.RED;
				else
					return Color.YELLOW;
			}

			// 3 colors have most cards.
			if (blue == green && blue == red && blue == max) {
				if (b == high)
					return Color.BLUE;
				if (g == high)
					return Color.GREEN;
				return Color.RED;

			}
			if (blue == green && blue == yellow && blue == max) {
				if (b == high)
					return Color.BLUE;
				if (g == high)
					return Color.GREEN;
				return Color.YELLOW;
			}
			if (blue == red && blue == yellow && blue == max) {
				if (b == high)
					return Color.BLUE;
				if (r == high)
					return Color.RED;
				return Color.YELLOW;
			}
			if (green == red && green == yellow && green == max) {
				if (g == high)
					return Color.GREEN;
				if (r == high)
					return Color.RED;
				return Color.YELLOW;
			}
		}
		// Finally when the numbers of cards of each color are different from
		// each
		// other.
		if (blue == max)
			return Color.BLUE;
		if (red == max)
			return Color.RED;
		if (green == max)
			return Color.GREEN;
		return Color.YELLOW;

	}

}
