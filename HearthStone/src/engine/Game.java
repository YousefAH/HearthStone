package engine;

import model.heroes.Hero;

public class Game {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero; // Read Only
	private Hero opponent; // Read Only

	public Game(Hero p1, Hero p2) {
		firstHero = p1;
		secondHero = p2;
		currentHero = ((int) (Math.random() * 2) == 1) ? p1 : p2; // If 1 then p1, else if 0 then p2;
		opponent = (currentHero.equals(firstHero)) ? secondHero : firstHero;
		currentHero.setCurrentManaCrystals(1);
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}
}
