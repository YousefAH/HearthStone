package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.*;
import model.cards.spells.*;
public class Mage extends Hero
{
	
	
	public Mage() throws IOException
	{
		super("Jaina Proudmoore");
	}
	
	public void buildDeck() throws IOException 
	{
		ArrayList<Minion> minionCards = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		//getDeck() = new ArrayList<Card>();
		getDeck().addAll(minionCards);
		
		getDeck().add(new Polymorph());
		getDeck().add(new Polymorph());
		getDeck().add(new Flamestrike());
		getDeck().add(new Flamestrike());
		getDeck().add(new Pyroblast());
		getDeck().add(new Pyroblast());
		
		getDeck().add(new Minion("Kalycgos",10,Rarity.LEGENDARY,4,12,false,false,false));
		Collections.shuffle(getDeck());
		
	}
	
}
