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
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero {
	public Paladin() throws IOException, CloneNotSupportedException
	{
		super("Uther Lightbringer");
	}
	
	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
//		getDeck().addAll(neutrals);
		for(Minion minion : neutrals)
			getDeck().add((Minion) minion.clone());
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new SealOfChampions());
			getDeck().add(new LevelUp());
		}
		Minion tirion=new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
	
		getDeck().add(tirion);
		
		for (int i = 0; i < getDeck().size(); i++) 
		{
			Card c = getDeck().get(i);
			if(c instanceof Minion)
				((Minion) c).setListener(this);
		}
		
		
		Collections.shuffle(getDeck());
	}
	
	public Card drawCard() throws FullHandException, CloneNotSupportedException 
	{
		Card m = super.drawCard();
		getHand().add(m);
//		Minion chro = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
		//chromaggus ability
//		if(getField().contains(chro))
//			if(getHand().size()<10)
//				getHand().add((Card)m.clone());
		for(Card c : getField())
			if(c.getName().equals("Chromaggus") && getHand().size()<10)
				getHand().add((Card) m.clone());
		return m;
	}
	
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
	 {
		 super.useHeroPower();
		 if(getField().size()>=7)
			 throw new FullFieldException();
		 getField().add(new Minion("Silver Hand Recruit", 1, Rarity.BASIC, 1, 1, false, false, false));
	 }
	
}
