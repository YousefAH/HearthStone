package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Minion other = (Minion) obj;
		if (attack != other.attack)
			return false;
		if (attacked != other.attacked)
			return false;
		if (currentHP != other.currentHP)
			return false;
		if (divine != other.divine)
			return false;
		if (maxHP != other.maxHP)
			return false;
		if (sleeping != other.sleeping)
			return false;
		if (taunt != other.taunt)
			return false;
		return true;
	}
	
}
