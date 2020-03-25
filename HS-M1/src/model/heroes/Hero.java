package model.heroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import engine.*;
import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
public abstract class Hero implements MinionListener{

	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
	private HeroListener listener;
	private ActionValidator validator;

	public Hero(String name) throws IOException, CloneNotSupportedException {
		this.name = name;
		currentHP = 30;
		deck = new ArrayList<Card>();
		field = new ArrayList<Minion>();
		hand = new ArrayList<Card>();
		buildDeck();
	}

	public abstract void buildDeck() throws IOException, CloneNotSupportedException;

	public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		String current = br.readLine();
		while (current != null) {
			String[] line = current.split(",");
			Minion minion = null;
			String n = line[0];
			int m = Integer.parseInt(line[1]);
			Rarity r = null;
			switch ((line[2])) {
			case "b":
				r = Rarity.BASIC;
				break;
			case "c":
				r = Rarity.COMMON;
				break;
			case "r":
				r = Rarity.RARE;
				break;
			case "e":
				r = Rarity.EPIC;
				break;
			case "l":
				r = Rarity.LEGENDARY;
				break;
			}
			int a = Integer.parseInt(line[3]);
			int p = Integer.parseInt(line[4]);
			boolean t = line[5].equals("TRUE") ? true : false;
			boolean d = line[6].equals("TRUE") ? true : false;
			boolean c = line[7].equals("TRUE") ? true : false;
			if (!n.equals("Icehowl"))
				minion = new Minion(n, m, r, a, p, t, d, c);
			else
				minion = new Icehowl();
			minions.add(minion);
			current = br.readLine();
		}
		br.close();
		return minions;
	}

	public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) {
		ArrayList<Minion> res = new ArrayList<Minion>();
		int i = 0;
		while (i < count) {

			int index = (int) (Math.random() * minions.size());
			Minion minion = minions.get(index);
			int occ = 0;
			for (int j = 0; j < res.size(); j++) {
				if (res.get(j).getName().equals(minion.getName()))
					occ++;
			}
			if (occ == 0) {
				res.add(minion);
				i++;
			} else if (occ == 1 && minion.getRarity() != Rarity.LEGENDARY) {
				res.add(minion);
				i++;
			}
		}
		return res;
	}

	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		validator.validateUsingHeroPower(this);
		validator.validateTurn(this);
		currentManaCrystals -= 2;
	}
	
	public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Card) s);
		// If no exceptions:
		s.performAction(field);
		// Decrease mana
		currentManaCrystals -= ((Card) s).getManaCost();
		// Remove card
		hand.remove((Card) s);
	}

	public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Card) s);
		// If no exceptions:
		s.performAction(oppField, field);
		// Decrease mana
		currentManaCrystals -= ((Card) s).getManaCost();
		// Remove card
		hand.remove((Card) s);
	}

	public void castSpell(MinionTargetSpell s, Minion m)
			throws NotYourTurnException, NotEnoughManaException, InvalidTargetException {
		validator.validateTurn(this);
		validator.validateManaCost((Card) s);
		// If no exceptions:
		s.performAction(m);
		// Decrease mana
		currentManaCrystals -= ((Card) s).getManaCost();
		// Remove card
		hand.remove((Card) s);
	}

	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Card) s);
		// If no exceptions:
		s.performAction(h);
		// Decrease mana
		currentManaCrystals -= ((Card) s).getManaCost();
		// Remove card
		hand.remove((Card) s);
	}
	
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,NotEnoughManaException
	{
		//check if card is playable
		validator.validateTurn(this);
		validator.validateManaCost((Card) s);
		//lose mana
		currentManaCrystals -= ((Card)s).getManaCost();
		//remove from hand
		hand.remove((Card) s);
		//activate the spell
		s.performAction(m);
		
		
	}	
	public Card drawCard() throws FullHandException, CloneNotSupportedException 
	{
		Minion chro = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
		//Minion wil = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false,false,false);
		if(deck.isEmpty()) 
		{
			currentHP -= fatigueDamage;
			fatigueDamage++;
			return new Minion("empty", 1, Rarity.BASIC, 1, 1, false, false, false);
		}
		
		Card m = (Card)deck.get(0).clone();
		if(hand.size()>10)
			throw new FullHandException(m);

		hand.add(m);
		deck.remove(0);
			
		fatigueCheck();
		
		//chromaggus ability
		if(field.contains(chro))
		{
			if(hand.size()<10)
				hand.add((Card)m.clone());
		}
		
		return m;
	}
	
	public void fatigueCheck()
	{
		if(deck.isEmpty() && fatigueDamage == 0)
			fatigueDamage = 1;
	}
	
	public boolean warlockCheck() 
	{
		if(deck.isEmpty()) 
		{
			currentHP -= fatigueDamage;
			fatigueDamage++;
			return true;
		}
		return false;
	}
	
	public void endTurn() throws FullHandException, CloneNotSupportedException
	{
		listener.endTurn();
	}
	 public void playMinion(Minion m) throws NotYourTurnException, NotEnoughManaException, FullFieldException
	 {
		validator.validateManaCost((Card)m);
		validator.validatePlayingMinion(m);
		validator.validateTurn(this);
		hand.remove(m); 
		field.add(m);
	 }
	 public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, 
	 NotYourTurnException, TauntBypassException, InvalidTargetException, NotSummonedException{
		 validator.validateTurn(this);
		 validator.validateAttack(attacker, target);
		 attacker.attack(target);
	 }
	 public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, 
	 NotYourTurnException, TauntBypassException, InvalidTargetException, NotSummonedException{
		 validator.validateTurn(this);
		 validator.validateAttack(attacker, target);
		 attacker.attack(target);
	 }
	 
	 public void onMinionDeath(Minion m) 
	 {
		 field.remove(m);
	 }
	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
		if (this.currentHP > 30)
			this.currentHP = 30;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onHeroDeath();
		}
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
		if (this.totalManaCrystals > 10)
			this.totalManaCrystals = 10;
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
		if (this.currentManaCrystals > 10)
			this.currentManaCrystals = 10;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setHeroPowerUsed(boolean powerUsed) {
		this.heroPowerUsed = powerUsed;
	}

	public String getName() {
		return name;
	}


	public void setListener(HeroListener listener) {
		this.listener = listener;
	}

	public HeroListener getListener() {
		return listener;
	}

	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}

}
