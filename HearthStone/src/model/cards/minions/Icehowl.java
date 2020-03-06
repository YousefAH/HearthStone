package model.cards.minions;

import model.cards.Rarity;

public class Icehowl extends Minion
{
	public Icehowl() 
	{
		super("Icehowl",9,Rarity.LEGENDARY,10,10,false,false,true);
		setSleeping(false);
	}
	
	public static void main(String[] args) {
		Icehowl i = new Icehowl();
		System.out.println(i.getManaCost());
	}
}
