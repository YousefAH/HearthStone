package model.heroes;

import java.io.IOException;
import java.util.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;
import model.cards.spells.Spell;

public class Hunter extends Hero
{

	public Hunter() throws IOException
	{
		super("Rexxar");
//		this.setCurrentHP(currentHP);
//		this.setHeroPowerUsed(heroPowerUsed);
//		this.setTotalManaCrystals(totalManaCrystals);
//		this.setCurrentManaCrystals(currentManaCrystals);
		
	}
	
	public void buildDeck() throws IOException 
	{
		ArrayList<Minion> minionCards = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 15);
		//getDeck() = new ArrayList<Card>();
		getDeck().addAll(minionCards);
		
		//add 2 KILLCOMMAND & 2 MULTISHOT
		getDeck().add(new KillCommand()) ;
		getDeck().add(new KillCommand()) ;
		getDeck().add(new MultiShot()) ;
		getDeck().add(new MultiShot()) ;
		
		getDeck().add(new Minion("King Krush",9,Rarity.LEGENDARY,8,8,false,false,true));
		Collections.shuffle(getDeck());
		

	}
	
//	public static void main(String[] args) throws IOException 
//	{
//		Hunter h = new Hunter();
//		h.buildDeck();
//		for (int i = 0; i < h.deck.size(); i++) {
//			System.out.println(i+1 +" "+h.deck.get(i));
//		}
	//}
}
