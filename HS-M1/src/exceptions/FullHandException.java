package exceptions;

import model.cards.Card;

@SuppressWarnings("serial")
public class FullHandException extends HearthstoneException {
	@SuppressWarnings("unused")
	private Card burned;
	public FullHandException(Card b) {
		super();
		burned=b;
		
	}

	public FullHandException(String message,Card b) {
		super(message);
		burned=b;
		
	}
}
