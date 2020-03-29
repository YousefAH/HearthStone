package engine;

import java.util.ArrayList;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator, HeroListener  {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException
	{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		for (int i = 0; i < 3; i++) 
		{
			currentHero.drawCard();
			opponent.drawCard();
		}
		opponent.drawCard();
		
		currentHero.setListener(this);
		opponent.setListener(this);
		currentHero.setValidator(this);
		opponent.setValidator(this);
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}
	public void validateTurn(Hero user) throws NotYourTurnException {
		if(currentHero!=user) {
			throw new NotYourTurnException("This is not your turn!!");
		}
	}
	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		ArrayList<Minion> a =opponent.getField();
		if(currentHero.getField().contains(target))
			throw new InvalidTargetException("You cannot attack friendly minions!!");
		if(!opponent.getField().contains(target))
			throw new NotSummonedException("The minion you are targeting has not been summoned yet");
		if(attacker.isSleeping())
			throw new CannotAttackException("You cannot attack with a sleeping minion!!");
		if(attacker.isAttacked())
			throw new CannotAttackException("This minion attacked this turn");
		if(attacker.getAttack()==0)
			throw new CannotAttackException("You cannot attack with zero attack points!!");
		if(!target.isTaunt()) {
			for(int i=0; i<a.size();i++) {
				if(a.get(i).isTaunt()==true) {
					throw new TauntBypassException("A minion with Taunt is in the way");
				}
			}
		}
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException("The minion is not summoned!!");
	}
	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		ArrayList<Minion> a =opponent.getField();
		if(currentHero==target)
			throw new InvalidTargetException("You cannot attack your hero!!");
		if(attacker instanceof Icehowl)
			if(!attacker.getName().equals("Sheep"))
				throw new InvalidTargetException("Icehowl can not attack heroes");
		if(attacker.isSleeping())
			throw new CannotAttackException("You cannot attack with a sleeping minion!!");
		if(attacker.isAttacked())
			throw new CannotAttackException("This minion attacked this turn");
		if(attacker.getAttack()==0)
			throw new CannotAttackException("You cannot attack with zero attack points!!");
		for(int i=0; i<a.size();i++) {
			if(a.get(i).isTaunt()==true) 
				throw new TauntBypassException("A minion with Taunt is in the way");
			
		}
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException("The minion is not summoned!!");
	}
	public void validateManaCost(Card card) throws NotEnoughManaException {
		if(card.getManaCost()>currentHero.getCurrentManaCrystals()) {
			throw new NotEnoughManaException("You do not have enough manacrystals!!");
		}
	}
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		if(currentHero.getField().size()==7) {
			throw new FullFieldException("The field is full!!");
		}
		
	}

	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if(hero.getCurrentManaCrystals()<2)
			throw new NotEnoughManaException("You do not have enough manacrystals!!");
		if(hero.isHeroPowerUsed()) {
			throw new HeroPowerAlreadyUsedException("You have already used the heropower!!");
		}
	}
	public void onHeroDeath() {
		listener.onGameOver();
	}
	public void damageOpponent(int amount) {
		opponent.setCurrentHP(opponent.getCurrentHP()-amount);
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		opponent= currentHero==firstHero?firstHero:secondHero;
		currentHero = currentHero==firstHero?secondHero:firstHero;
		int x = currentHero.getTotalManaCrystals()+1;
		currentHero.setCurrentManaCrystals(x);
		currentHero.setTotalManaCrystals(x);
		currentHero.setHeroPowerUsed(false);
		ArrayList<Minion>a= currentHero.getField();
		for(int i=0; i<a.size();i++) {
			a.get(i).setAttacked(false);
			a.get(i).setSleeping(false);
		}
		currentHero.drawCard();
	}
}