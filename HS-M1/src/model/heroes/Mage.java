package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.*;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero {

	public Mage() throws IOException, CloneNotSupportedException {
		super("Jaina Proudmoore");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new Polymorph());
			getDeck().add(new Flamestrike());
			getDeck().add(new Pyroblast());
		}
		Minion kalycgos = (new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false));
		;
		getDeck().add(kalycgos);
		Collections.shuffle(getDeck());

	}

	public void checkKalycgosOnField(Spell s) { // Legendary Minion Special effects
		for (Minion m : getField())
			if (m.getName().equals("Kalycgos")) {
				s.setManaCost(s.getManaCost() - 4);
				return;
			}
	}

	public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException {
		checkKalycgosOnField((Spell) s);
		super.castSpell(s);
	}

	public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws NotYourTurnException, NotEnoughManaException {
		checkKalycgosOnField((Spell) s);
		super.castSpell(s, oppField);
	}

	public void castSpell(MinionTargetSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException, InvalidTargetException {
		checkKalycgosOnField((Spell) s);
		super.castSpell(s, m);
	}

	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException {
		checkKalycgosOnField((Spell) s);
		super.castSpell(s, h);
	}

	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException {
		checkKalycgosOnField((Spell) s);
		super.castSpell(s, m);
	}

	public void useHeroPower(Object target) throws NotEnoughManaException, HeroPowerAlreadyUsedException,
			NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		if (target instanceof Minion)
			((Minion) target).setCurrentHP(((Minion) target).getCurrentHP() - 1);
		else if (target instanceof Hero)
			((Hero) target).setCurrentHP(((Hero) target).getCurrentHP() - 1);
	}

}
