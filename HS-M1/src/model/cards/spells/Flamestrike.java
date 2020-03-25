package model.cards.spells;

import java.util.ArrayList;
import java.util.Iterator;

import model.cards.Rarity;
import model.cards.minions.Minion;


public class Flamestrike extends Spell implements AOESpell {

	
	public Flamestrike()
	{
		super("Flamestrike",7,Rarity.BASIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) 
	{
		Iterator<Minion> iter = oppField.iterator();

		while (iter.hasNext()) {
		    Minion m = iter.next();
		    if(m.isDivine())
		    {
		    	m.setDivine(false);
		    }
		    else if(m.getCurrentHP()<=4)
		    	iter.remove();
		    else
		    	m.setCurrentHP(m.getCurrentHP()-4);
		}
		
	}
	
}
