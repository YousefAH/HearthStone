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

	public Warlock() throws IOException, CloneNotSupportedException {
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
		
		for (int i = 0; i < getDeck().size(); i++) 
		{
			Card c = getDeck().get(i);
			if(c instanceof Minion)
				((Minion) c).setListener(this);
		}
		
		Collections.shuffle(getDeck());

	}

	
	public Card drawCard_withPower() throws FullHandException, CloneNotSupportedException
	{
		if(warlockCheck())
			return new Minion("empty", 1, Rarity.BASIC, 1, 1, false, false, false);
		
		Card c = getDeck().get(0);
		Minion wil = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false);
		if(getField().contains(wil))
			c.setManaCost(0);
		getHand().add(c);
		getDeck().remove(0);
		fatigueCheck();
		Minion chro = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
		//chromaggus ability
		if(getField().contains(chro))
			if(getHand().size()<9)
				getHand().add((Card)c.clone());
		
		
		return c;
		
	}
	
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
	    drawCard_withPower();
		setCurrentHP(getCurrentHP() - 2);
	}

}
