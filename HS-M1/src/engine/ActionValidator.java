package engine;
import exceptions.*;
import model.heroes.*;
import model.cards.*;
import model.cards.minions.*;

public interface ActionValidator 
{
	//validates different actions through out the game
	public void validateTurn(Hero user) throws NotYourTurnException;
	//allows the current_player to play only in his turn
	public void validateAttack(Minion attacker,Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException,InvalidTargetException;
	//checks if the attack on the minion is allowed
	public void validateAttack(Minion attacker,Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException,InvalidTargetException;
	//checks if the attack on the hero is allowed
	public void validateManaCost(Card card) throws NotEnoughManaException;
	//checks if player has enough mana for action
	public void validatePlayingMinion(Minion minion) throws FullFieldException;
	//checks if the player can summon or not
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException,HeroPowerAlreadyUsedException;
	//checks if player can use hero power 
}
