package model.heroes;

import exceptions.*;

public interface HeroListener {
	public void onHeroDeath(); // This method is triggered once any of the two heroes die
	// The method should also trigger onGameOver method of the GameListener

	public void damageOpponent(int amount);

	public void endTurn() throws FullHandException, CloneNotSupportedException;
	// triggered once a hero ends his turn
	/*
	 * The turn of the hero should be switched. The current and total mana crystals
	 * of the current hero should be updated according to the game rules. The hero
	 * power usage should be reset. All minions of the current hero should have
	 * their attack usage reset and wake up (if asleep). Finally, the current hero
	 * should draw a card from his deck
	 */
}
