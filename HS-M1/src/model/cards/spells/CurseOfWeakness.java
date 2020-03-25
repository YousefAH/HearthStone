package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {

	public CurseOfWeakness()  {
		super("Curse of Weakness", 2, Rarity.RARE);

	}

	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField)
	{
		for (Minion m : oppField) {
			m.setAttack(m.getAttack() - 2);
		}
	}
}
