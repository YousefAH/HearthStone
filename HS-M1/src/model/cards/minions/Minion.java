package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,
			boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}

	public void attack(Minion target) {
		if (attacked)
			return;
		if (target.isDivine())
			if (attack != 0)
				target.divine = false;
		target.currentHP -= attack;

		if (isDivine())
			if (target.attack != 0)
				divine = false;
		currentHP -= target.attack;

		attacked = true;

	}

	public void attack(Hero target) throws InvalidTargetException {
		if (super.getName() == "Icehowl") {
			throw new InvalidTargetException("Icehowl cannot attack heroes!");
		} else {
			target.setCurrentHP(target.getCurrentHP() - this.attack);
		}
		attacked = true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Minion) super.clone();
	}

	public boolean isTaunt() {
		return taunt;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			if (listener != null) // Added to check presence of listener
				listener.onMinionDeath(this);
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Minion other = (Minion) obj;
//		if (attack != other.attack)
//			return false;
//		if (attacked != other.attacked)
//			return false;
//		if (currentHP != other.currentHP)
//			return false;
//		if (divine != other.divine)
//			return false;
//		if (maxHP != other.maxHP)
//			return false;
//		if (sleeping != other.sleeping)
//			return false;
//		if (taunt != other.taunt)
//			return false;
		return false;
	}

}
