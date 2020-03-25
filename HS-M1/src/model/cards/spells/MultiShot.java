package model.cards.spells;

import java.util.ArrayList;
import engine.Game;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {

	public MultiShot() {
		super("Multi-Shot", 4, Rarity.BASIC);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {

		if (oppField.isEmpty())
			return;

		if(oppField.size()==1) 
		{
			oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP()-3);
			return;
		}
		
		int n1 = (int)(Math.random() *oppField.size()), n2;
		do 
		{
			n2 = (int)(Math.random() *oppField.size());
		}while(n1==n2);
		
		if(oppField.get(n1).isDivine())
			oppField.get(n1).setDivine(false);
		else
			oppField.get(n1).setCurrentHP(oppField.get(n1).getCurrentHP()-3);
		if(oppField.get(n2).isDivine())
			oppField.get(n2).setDivine(false);
		else
			oppField.get(n2).setCurrentHP(oppField.get(n2).getCurrentHP()-3);
	}


}
