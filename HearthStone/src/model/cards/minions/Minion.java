package model.cards.minions;

import model.cards.*;

public class Minion extends Card {
	private int attack;
	private int maxHP;

	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private boolean charge;

	public Minion() {}
	 public Minion(String name,int manaCost,Rarity rarity, int attack,int maxHP,boolean taunt,boolean divine,boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		this.sleeping = !charge;
		this.attacked = false;
		this.charge = charge;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		
		this.attack = (attack>10)? 10:((attack<0)? 0: attack);
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public boolean equals(Object o) 
	{
		return super.equals(o) && this.attack == ((Minion) o).attack && this.maxHP == ((Minion) o).maxHP && this.taunt == ((Minion) o).taunt && this.divine == ((Minion) o).divine && this.charge == ((Minion) o).charge;
	}

	public String toString() {

		return super.toString() + " " + attack + " " + maxHP + " " + taunt + " " + divine + " " + charge + " ";
	}

	public static void main(String[] args) {

	}

}
