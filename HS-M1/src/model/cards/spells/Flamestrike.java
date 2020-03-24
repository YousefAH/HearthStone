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
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for (int i = 0; i < oppField.size(); i++) 
		{
			oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-4);
		}
		
	}
}
