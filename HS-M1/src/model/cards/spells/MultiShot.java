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

		boolean hasOneMinion = oppField.size() == 1;
		Minion m1; // To be damaged
		Minion m2; // To be damaged
		int idx = (int) (Math.random() * oppField.size()); // For Random index

		m1 = oppField.get(idx);
		dealDamageBy3(m1);

		if (hasOneMinion)
			return;

		while (true) {
			idx = (int) (Math.random() * oppField.size());
			m2 = oppField.get(idx);
			if (m2 == m1) // If same minion repeat again
				continue;
			// Else
			dealDamageBy3(m2);
			return;
		}
	}

	public void dealDamageBy3(Minion m)  {
		m.setCurrentHP(m.getCurrentHP() - 3);
	}

}
