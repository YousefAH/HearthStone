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
		
		
		if(n1>n2)
			damage2Minions(n1, n2, oppField);
		else
			damage2Minions(n2, n1, oppField);
			
	}
	
	public void damage2Minions(int i, int j, ArrayList<Minion> oppField) {
		if(oppField.get(i).isDivine())
			oppField.get(i).setDivine(false);
		else
			oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-3);
		if(oppField.get(j).isDivine())
			oppField.get(j).setDivine(false);
		else
			oppField.get(j).setCurrentHP(oppField.get(j).getCurrentHP()-3);
	}

}
