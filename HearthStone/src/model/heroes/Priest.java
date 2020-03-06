package model.heroes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;
public class Priest extends Hero 
{
	
	public Priest() throws IOException
	{
		super("Anduin Wrynn");
	}

	
	public void buildDeck() throws IOException 
	{
		ArrayList<Minion> minionCards = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		//getDeck() = new ArrayList<Card>();
		getDeck().addAll(minionCards);
		
		getDeck().add(new DivineSpirit());
		getDeck().add(new DivineSpirit());
		getDeck().add(new HolyNova());
		getDeck().add(new HolyNova());
		getDeck().add(new ShadowWordDeath());
		getDeck().add(new ShadowWordDeath());
		
		getDeck().add(new Minion("Prophet Velen",7,Rarity.LEGENDARY,7,7,false,false,false));

		Collections.shuffle(getDeck());
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Priest h = new Priest();
		
		for (int i = 0; i < h.getDeck().size(); i++) {
			System.out.println(i+1 +" "+h.getDeck().get(i));
		}
	}
}
