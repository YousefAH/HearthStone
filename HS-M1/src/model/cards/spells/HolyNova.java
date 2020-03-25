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
		Iterator<Minion> iter = oppField.iterator();

		while (iter.hasNext()) {
		    Minion m = iter.next();
		    if(m.getCurrentHP()<=2)
		    	iter.remove();
		    else
		    	m.setCurrentHP(m.getCurrentHP()-2);
		}
		
		for (int i = 0; i < curField.size(); i++) 
		{
			curField.get(i).setCurrentHP(curField.get(i).getCurrentHP()+2);
		}
	}

}
