package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

	
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) 
	{
		curField.clear();
		oppField.clear();
	}

}
