package model.heroes;

import java.io.*;
import java.util.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.*;

public abstract class Hero {
	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;

//	public Hero()  
//	{}

	
	public Hero(String name) throws IOException 
	{
		this.name = name;
		this.currentHP = 30;
		this.heroPowerUsed = false;
		this.totalManaCrystals = 0;
		this.currentManaCrystals = 0;
		this.deck = new ArrayList<Card>();
		this.field = new ArrayList<Minion>();
		this.hand = new ArrayList<Card>();
		buildDeck();
	}
//setter & getters
	public int getCurrentHP() 
	{
		return currentHP;
	}
	public void setCurrentHP(int currentHP)
	{
		if(currentHP > 30)
			this.currentHP = 30;
		else
		this.currentHP = currentHP;
	}
	public boolean isHeroPowerUsed() 
	{
		return heroPowerUsed;
	}
	public void setHeroPowerUsed(boolean heroPowerUSed) 
	{
		this.heroPowerUsed = heroPowerUSed;
	}
	public int getTotalManaCrystals() 
	{
		return totalManaCrystals;
	}
	public void setTotalManaCrystals(int totalManaCrystals) 
	{
		if(totalManaCrystals > 10)
			this.totalManaCrystals = 10;
		else
			this.totalManaCrystals = totalManaCrystals;
	}
	public int getCurrentManaCrystals() 
	{
		return currentManaCrystals;
	}
	public void setCurrentManaCrystals(int currentManaCrystals) 
	{
		if(currentManaCrystals > 10)
			this.currentManaCrystals = 10;
		else
			this.currentManaCrystals = currentManaCrystals;
	}
	public String getName() 
	{
		return name;
	}
	public ArrayList<Card> getDeck() 
	{
		return deck;
	}
	public ArrayList<Minion> getField() 
	{
		return field;
	}
	public ArrayList<Card> getHand()
	{
		return hand;
	}
//end 
	
	final public static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException
	{
		ArrayList<Minion> minionList = new ArrayList<Minion>();
		   try 
		   {
					Scanner sc = new Scanner(new File(filePath));  
					String[] x;
					while (sc.hasNext())   
					{  
						String s = sc.nextLine();
						x = s.split(",");  
					   Rarity rarity = Rarity.RARE;
					   if(x[0].equals("Icehowl"))
						   minionList.add(new Icehowl());
					   else {
						   switch(x[2])
						   {
						   	case "b": rarity = Rarity.BASIC;
						   	break;
							case "c": rarity = Rarity.COMMON;
						   	break;
							case "r": rarity = Rarity.RARE;
						   	break;
							case "e": rarity = Rarity.EPIC;
						   	break;
							case "l": rarity = Rarity.LEGENDARY;
						   	break;
						   }
					       minionList.add( new Minion(x[0],Integer.parseInt(x[1]),rarity,Integer.parseInt(x[3]),Integer.parseInt(x[4]),Boolean.parseBoolean(x[5]),Boolean.parseBoolean(x[6]),Boolean.parseBoolean(x[7])));
					   	}
					}
					sc.close();
	       }catch(IOException e) {
			System.out.println("file path was lost");
		}
		   
		return minionList;
	}
	
	final public static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count)
	{
		ArrayList<Minion> playable = new ArrayList<Minion>();
		ArrayList<Minion> duplicates = new ArrayList<Minion>();
		int size = minions.size();
		int rand;
		while(playable.size()<count)
		{
			//Random random = new Random();
			rand = (int)(Math.random() * size);
			Minion m = minions.get(rand);
			if(playable.contains(m)) 
			{
				if(!duplicates.contains(m)) 
				{
     				if(m.getRarity() != Rarity.LEGENDARY) 
     					playable.add(m);
					duplicates.add(m);
     			
				}
			}
			else 
			{
				playable.add(m);
			}
		}
		return playable;
	}
	
	public abstract void buildDeck() throws IOException;
	public static void main(String[] args) throws IOException 
	{
		try {
			ArrayList<Minion> mL = getAllNeutralMinions("neutral_minions.csv");
			ArrayList<Minion> m = getNeutralMinions(mL,15);
			for (int i = 0; i < m.size(); i++) 
				System.out.println(i+" "+m.get(i));
		}catch(IOException e) {
			System.out.println("file path wass lost");
		}
	}
	
}
