package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

public class Hunter extends Hero {

	public Hunter() throws IOException, CloneNotSupportedException {
		super("Rexxar");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new KillCommand());
			getDeck().add(new MultiShot());
			
		}
		Minion krush=(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true));
		
		getDeck().add(krush);
		
		for (int i = 0; i < getDeck().size(); i++) 
		{
			Card c = getDeck().get(i);
			if(c instanceof Minion)
				((Minion) c).setListener(this);
		}
		
		Collections.shuffle(getDeck());
	}
	
	 public void useHeroPower(Hero opp) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
	 {
		 super.useHeroPower();
		 opp.setCurrentHP(opp.getCurrentHP()-2);
	 }

	
	
	
	
}
