package model.cards.spells;

import java.util.ArrayList;
import java.util.Iterator;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for (int i = 0; i < oppField.size(); i++) 
		{
			oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-2);
		}
		Iterator itr = oppField.iterator();
		 while (itr.hasNext()) 
	     { 
			Minion check = (Minion)itr.next();
			if(check.getCurrentHP()==0) 
			{
				//minion listener should be notified somehow
				itr.remove();
			}
				
	     } 
		
		
		for (int i = 0; i < curField.size(); i++) 
		{
			curField.get(i).setCurrentHP(curField.get(i).getCurrentHP()+2);
		}
	}

}
