package model.heroes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.*;
import model.cards.spells.*;
public class Warlock extends Hero 
{

	public Warlock() throws IOException
	{
		super("Gul'dan");
		
	}

	
	public void buildDeck() throws IOException 
	{
		ArrayList<Minion> minionCards = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		//getDeck() = new ArrayList<Card>();
		getDeck().addAll(minionCards);
		
		getDeck().add(new CurseOfWeakness());
		getDeck().add(new CurseOfWeakness());
		getDeck().add(new SiphonSoul());
		getDeck().add(new SiphonSoul());
		getDeck().add(new TwistingNether());
		getDeck().add(new TwistingNether());
		
		getDeck().add(new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false));
		
		Collections.shuffle(getDeck());
		
	}
	
//	public static void main(String[] args) throws IOException
//	{
//		Warlock h = new Warlock();
//		h.buildgetDeck()();
//		for (int i = 0; i < h.getDeck().size(); i++) {
//			System.out.println(i+1 +" "+h.getDeck().get(i));
//		}
//	}
}
