package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false);
		getDeck().add(wilfred);
		Collections.shuffle(getDeck());

	}

	public void checkFizzlebangOnField(Minion minion) {
		for (Minion m : getField())
			if (m.getName().equals("Wilfred Fizzlebang")) {
				minion.setManaCost(0);
				return;
			}
	}

	@Override
	public Card drawCard() throws FullHandException, CloneNotSupportedException {
		// FizzleBang
		Card c = super.drawCard();
		if (c == null) // Empty deck
			return null;
		if (c instanceof Minion)
			checkFizzlebangOnField((Minion) c);

		// Chromaggus to be added

		return c;
	}

	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Card c = drawCard();
		setCurrentHP(getCurrentHP() - 2);
	}

}
