package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		
	}
	
	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		if(m.getAttack()<5) // Needs to be proven
			throw new InvalidTargetException("The attack of this minion is less than 5.");
		m.setCurrentHP(0);
	}
}
