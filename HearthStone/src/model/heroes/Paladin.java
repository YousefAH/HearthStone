package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Paladin extends Hero {
	
	public Paladin() throws IOException 
	{
		super("Uther Lightbringer");
	}

	
	public void buildDeck() throws IOException 
	{
		ArrayList<Minion> minionCards = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 15);
		//getDeck() = new ArrayList<Card>();
		getDeck().addAll(minionCards);
		
		getDeck().add(new SealOfChampions());
		getDeck().add(new SealOfChampions());
		getDeck().add(new LevelUp());
		getDeck().add(new LevelUp());
		
		
		getDeck().add(new Minion("Tirion Fordring",4,Rarity.LEGENDARY,6,6,true,true,false));

		Collections.shuffle(getDeck());
		
	}
	

}
