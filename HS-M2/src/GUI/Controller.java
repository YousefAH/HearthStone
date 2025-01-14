package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.*;
import engine.*;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;

public class Controller implements ActionListener, GameListener {
	private Game g;
	private GameView model;
	private ArrayList<JButton> cHand = new ArrayList<JButton>();
	private ArrayList<JButton> cField = new ArrayList<JButton>();
	private ArrayList<JButton> oField = new ArrayList<JButton>();
	private ArrayList<JButton> oHand = new ArrayList<JButton>();
	private Spell usedSpell;
	private JButton s;
	private Minion attacker;
	private boolean powerTrigger;
	private String[] posibleValuesMinion = { "Summon", "View Details", "Cancel" };
	private String[] posibleValuesMinionOnField = { "Attack", "View Details", "Cancel" };
	private String[] possibleAttacker = {"Opponent","A Minion", "Cancel"};
	private String[] posibleValuesSpell = { "Activate", "View Details", "Cancel" };
	private String[] posibleValuesHeroSpell = { "Myself", "Opponent","A Minion", "Cancel" };
	private String name1;
	private String name2;

	public Controller(Hero p1, Hero p2, String n1, String n2) throws IOException, FullHandException, CloneNotSupportedException 
	{

		model = new GameView();
		model.setTitle("HearthStone");
		model.getEndTurn().addActionListener(this);
		model.getHeroPower().addActionListener(this);
		
		name1 = n1;
		name2 = n2;
		
		g = new Game(p1, p2);
		g.setListener(this);
		model.getcText().setText(name1 + "\nMana: "
				+ g.getCurrentHero().getCurrentManaCrystals() + "\nHp: " + g.getCurrentHero().getCurrentHP() + "\nCards Left in Deck: "+ g.getCurrentHero().getDeck().size());
		model.getoText().setText(name2 + "\nMana: " + g.getOpponent().getCurrentManaCrystals()
				+ "\nHp: " + g.getOpponent().getCurrentHP()+"\nCards Left in Deck: "+ g.getOpponent().getDeck().size());
		genButtonHand(g.getCurrentHero().getHand(), cHand, model.getCurrentHandPanel());
		genButtonHandOpp(g.getOpponent().getHand(), oHand, model.getOpponentHandPanel());
	}

	public void genButtonHand(ArrayList<Card> hand, ArrayList<JButton> l, JPanel p)
	{
		for (int i = 0; i < hand.size(); i++) {
			JButton b = new JButton(hand.get(i).getName());
			l.add(b);
			p.add(b);
			b.addActionListener(this);
			ImageIcon icon;
			if (hand.get(i).getName().equals("Shadow Word: Death"))
				icon = new ImageIcon("images/Shadow Word.png");
			else
				icon = new ImageIcon("images/" + hand.get(i).getName() + ".png");
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance((int)(model.getHeight()*0.14), (int)(model.getHeight()*0.22), Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			b.setIcon(icon);
			b.setOpaque(false);
		}
	}
		public void genButtonHandOpp(ArrayList<Card> hand, ArrayList<JButton> l, JPanel p)
		{
			for (int i = 0; i < hand.size(); i++) {
				JButton b = new JButton();
				l.add(b);
				p.add(b);
				b.addActionListener(this);
				ImageIcon icon;
				icon = new ImageIcon("images/card back.png");
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance((int)(model.getHeight()*0.14), (int)(model.getHeight()*0.22), Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);
				b.setIcon(icon);
				b.setOpaque(false);
			}
	}
	
	public void genButtonFieled(ArrayList<Minion> hand, ArrayList<JButton> l, JPanel p) {
		for (int i = 0; i < hand.size(); i++) {
			JButton b = new JButton(hand.get(i).getName());
			l.add(b);
			p.add(b);
			b.addActionListener(this);
			ImageIcon icon;
			if (hand.get(i).getName().equals("Shadow Word: Death"))
				icon = new ImageIcon("images/Shadow Word.png");
			else
				icon = new ImageIcon("images/" + hand.get(i).getName() + ".png");
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance((int)(model.getHeight()*0.14), (int)(model.getHeight()*0.22), Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			b.setIcon(icon);
			if(hand.get(i).isDivine())
				b.setBackground(Color.YELLOW);
			//b.setOpaque(false);
		}
	}

	public void onGameOver() 
	{
		updateScreen();
		Hero winner = (g.getCurrentHero().getCurrentHP()==0)? g.getOpponent():g.getCurrentHero();
		String[] o = {"Close Window"};
		int val = JOptionPane.showOptionDialog(null, winner.getName()+" won", "Winner!!", JOptionPane.DEFAULT_OPTION, 0, null, o,o[0]);
		if(val == -1 || val == 0)
			model.dispatchEvent(new WindowEvent(model, WindowEvent.WINDOW_CLOSING));
	}
	public void updateScreen()
	{
		String temp = name1;
		name1 = name2;
		name2 = temp;
		model.getcText().setText(name1 + "\nMana: "
				+ g.getCurrentHero().getCurrentManaCrystals() + "\nHp: " + g.getCurrentHero().getCurrentHP() + "\nCards Left in Deck: "+ g.getCurrentHero().getDeck().size());
		model.getoText().setText(name2 + "\nMana: " + g.getOpponent().getCurrentManaCrystals()
				+ "\nHp: " + g.getOpponent().getCurrentHP()+"\nCards Left in Deck: "+ g.getOpponent().getDeck().size());
		cHand = new ArrayList<JButton>();
		oHand = new ArrayList<JButton>();
		cField = new ArrayList<JButton>();
		oField = new ArrayList<JButton>();
		model.getCurrentHandPanel().removeAll();
		model.getOpponentHandPanel().removeAll();
		model.getCurrentFieldPanel().removeAll();
		model.getOpponentFieldPanel().removeAll();;
		genButtonHand(g.getCurrentHero().getHand(), cHand, model.getCurrentHandPanel());
		genButtonHandOpp(g.getOpponent().getHand(), oHand, model.getOpponentHandPanel());
		genButtonFieled(g.getCurrentHero().getField(), cField, model.getCurrentFieldPanel());
		genButtonFieled(g.getOpponent().getField(), oField, model.getOpponentFieldPanel());
		
		model.repaint();
		model.revalidate();
	}
	public void viewDetails(ActionEvent e)
	{
		Minion c = null;
		model.getCardDisplay().setIcon(new ImageIcon("images/" + e.getActionCommand() + ".png"));
		if (cField.indexOf(e.getSource()) != -1) {
			c = g.getCurrentHero().getField().get(cField.indexOf(e.getSource()));
			JOptionPane.showMessageDialog(null, "Attack: " + c.getAttack() +" Current HP: "+ c.getCurrentHP());
		}
		else if (oField.indexOf(e.getSource()) != -1) {
			c = g.getOpponent().getField().get(oField.indexOf(e.getSource()));
			JOptionPane.showMessageDialog(null, "Attack: " + c.getAttack() +" Current HP: "+ c.getCurrentHP());
		}
		
		
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException 
	{
			g.endTurn();
			updateScreen();
		    model.getCardDisplay().setIcon(new ImageIcon("images/card.png"));

	}
	
	public void summonMinion(ActionEvent e,Card c) throws NotYourTurnException , NotEnoughManaException, FullFieldException
	{
		
		int val = -1;
		val = JOptionPane.showOptionDialog(null, "Actions", "", JOptionPane.DEFAULT_OPTION, 0, null,
				posibleValuesMinion, posibleValuesMinion[2]);
		if (val == 0) {
			g.getCurrentHero().playMinion((Minion) c);
			updateScreen();
		}
		if (val == 1)
			viewDetails(e);
	}
	
	public void castSpell(ActionEvent e, Card c) throws NotYourTurnException, NotEnoughManaException, InvalidTargetException
	{
		int val = -1;
		ImageIcon icon;
		if(c instanceof HeroTargetSpell)
			{

				val = JOptionPane.showOptionDialog(null, "Choose a Target", "", JOptionPane.DEFAULT_OPTION, 0, null, posibleValuesHeroSpell, posibleValuesHeroSpell[3]);
				if(val == 0) {
					g.getCurrentHero().castSpell((HeroTargetSpell)c, g.getCurrentHero());
					model.getCurrentHandPanel().remove(cHand.indexOf(s));
					cHand.remove(cHand.indexOf(s));
				}
				else if(val == 1) {
					g.getCurrentHero().castSpell((HeroTargetSpell)c, g.getOpponent());
					model.getCurrentHandPanel().remove(cHand.indexOf(s));
					cHand.remove(cHand.indexOf(s));
				}
				else if(val == 2) 
					usedSpell = (Spell) c;
	
			}
		else if(usedSpell != null)
		{
			
			if(usedSpell instanceof LeechingSpell)
				g.getCurrentHero().castSpell((LeechingSpell)usedSpell, (Minion)c);
			if(usedSpell instanceof MinionTargetSpell)
				g.getCurrentHero().castSpell((MinionTargetSpell)usedSpell, (Minion)c);
			if(c.getName().equals("Sheep"))
			{
				icon = new ImageIcon("images/Sheep.png");
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance(157, 234, Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);
				((JButton)e.getSource()).setIcon(icon);
			}
			usedSpell = null;
			updateScreen();
		}
		else 
		{
			if (c instanceof AOESpell) {
				g.getCurrentHero().castSpell((AOESpell) c, g.getOpponent().getField());
				model.getCurrentHandPanel().remove(cHand.indexOf(s));
				cHand.remove(cHand.indexOf(s));
			} else if (c instanceof FieldSpell) {
				g.getCurrentHero().castSpell((FieldSpell) c);
				model.getCurrentHandPanel().remove(cHand.indexOf(s));
				cHand.remove(cHand.indexOf(s));
			} else
				usedSpell = (Spell) c;

		}
		updateScreen();
		model.repaint();
		model.revalidate();
	}

	public void attack(ActionEvent e, Card c) throws CannotAttackException, NotYourTurnException, TauntBypassException, NotSummonedException, InvalidTargetException
	{
		
		if(attacker != null)
		{
			g.getCurrentHero().attackWithMinion(attacker, (Minion)c);
			attacker = null;
		}
		else 
		{
			int val = JOptionPane.showOptionDialog(null, "Choose a Target", "", JOptionPane.DEFAULT_OPTION, 0, null, possibleAttacker, possibleAttacker[2]);
			if(val == 0)
				g.getCurrentHero().attackWithMinion((Minion)c, g.getOpponent());
			else if(val == 1)
				if(attacker == null)
					attacker = (Minion)c;
			
		}
		updateScreen();
	}
	
	public void heroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
	{
		int val = -1;
		if(g.getCurrentHero() instanceof Priest ) 
		{
			val = JOptionPane.showOptionDialog(null, "Choose a Target", "", JOptionPane.DEFAULT_OPTION, 0, null, posibleValuesHeroSpell, posibleValuesHeroSpell[3]);
			if(val == 0)
				((Priest)g.getCurrentHero()).useHeroPower(g.getCurrentHero());
			else if(val == 1)
				((Priest)g.getCurrentHero()).useHeroPower(g.getOpponent());
			else if(val == 2)
				powerTrigger = true;
		}
		else if(g.getCurrentHero() instanceof Mage ) 
		{
			val = JOptionPane.showOptionDialog(null, "Choose a Target", "", JOptionPane.DEFAULT_OPTION, 0, null, posibleValuesHeroSpell, posibleValuesHeroSpell[3]);
			if(val == 0)
				((Mage)g.getCurrentHero()).useHeroPower(g.getCurrentHero());
			else if(val == 1)
				((Mage)g.getCurrentHero()).useHeroPower(g.getOpponent());
			else if(val == 2)
				powerTrigger = true;
		}
		else
			g.getCurrentHero().useHeroPower();
		updateScreen();
	}
	
	public void heroPower(ActionEvent e) throws InvalidTargetException, NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
	{
		Minion c;
		powerTrigger = !powerTrigger;
		if (cField.indexOf(e.getSource()) != -1)
			c = g.getCurrentHero().getField().get(cField.indexOf(e.getSource()));
		else if (oField.indexOf(e.getSource()) != -1)
			c = g.getOpponent().getField().get(oField.indexOf(e.getSource()));
		else
			throw new InvalidTargetException("You can only target minions on field");
		
		if(g.getCurrentHero() instanceof Priest ) 
			((Priest)g.getCurrentHero()).useHeroPower(c);
		else if(g.getCurrentHero() instanceof Mage ) 
			((Mage)g.getCurrentHero()).useHeroPower(c);

	}
	public void actionPerformed(ActionEvent e) 
	{
		int val = -1;
		try {
			Card c;
			if (e.getActionCommand().equals("End Turn"))
				endTurn();
			else if(e.getActionCommand().equals("Call A Friend"))
			{
				
			}
			else if (e.getActionCommand().equals("Hero Power"))
				heroPower();
			else if(powerTrigger)
			{
				heroPower(e);
			}
			else if (attacker != null) {
				if (oField.indexOf(e.getSource()) != -1)
					c = g.getOpponent().getField().get(oField.indexOf(e.getSource()));
				else
					throw new InvalidTargetException("You can only attack Minions in your opponent's field");
				attack(e, c);
			} else if (cHand.indexOf(e.getSource()) != -1) {
				c = g.getCurrentHero().getHand().get(cHand.indexOf(e.getSource()));
				if (c instanceof Minion) {
					usedSpell = null;
					summonMinion(e, c);
				} else {
					usedSpell = null;
					val = JOptionPane.showOptionDialog(null, "Actions", "", JOptionPane.DEFAULT_OPTION, 0, null,
							posibleValuesSpell, posibleValuesSpell[2]);
					if (val == 0) {
						s = (JButton) e.getSource();
						castSpell(e, c);
					} else if (val == 1)
						viewDetails(e);
					model.repaint();
				}

			} else if (usedSpell == null)
			{
				if (cField.indexOf(e.getSource()) != -1) 
				{
					c = g.getCurrentHero().getField().get(cField.indexOf(e.getSource()));
					val = JOptionPane.showOptionDialog(null, "Actions", "", JOptionPane.DEFAULT_OPTION, 0, null,
							posibleValuesMinionOnField, posibleValuesMinionOnField[2]);
					if (val == 1)
						viewDetails(e);
					else if (val == 0)
						attack(e, c);
				} else if (oField.indexOf(e.getSource()) != -1) 
				{
					c = g.getOpponent().getField().get(oField.indexOf(e.getSource()));
					val = JOptionPane.showConfirmDialog(null, "View Details");
					if (val == JOptionPane.YES_OPTION)
						viewDetails(e);
				} else
					throw new NotYourTurnException("This is not your turn");
			} else if (usedSpell != null) 
			{
				if (cField.indexOf(e.getSource()) != -1)
					c = g.getCurrentHero().getField().get(cField.indexOf(e.getSource()));
				else if (oField.indexOf(e.getSource()) != -1)
					c = g.getOpponent().getField().get(oField.indexOf(e.getSource()));
				else
					throw new InvalidTargetException("You can only target cards on either fields");
				castSpell(e, c);
			}

			model.getcText().setText(name1 + "\nMana: "
					+ g.getCurrentHero().getCurrentManaCrystals() + "\nHp: " + g.getCurrentHero().getCurrentHP() + "\nCards Left in Deck: "+ g.getCurrentHero().getDeck().size());
			model.getoText().setText(name2 + "\nMana: " + g.getOpponent().getCurrentManaCrystals()
					+ "\nHp: " + g.getOpponent().getCurrentHP()+"\nCards Left in Deck: "+ g.getOpponent().getDeck().size());

			model.repaint();

		} catch (NotYourTurnException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (NotEnoughManaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			usedSpell = null;
		} catch (InvalidTargetException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			attacker = null;
			usedSpell = null;
		} catch (FullFieldException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "Not Your turn");
		} catch (CannotAttackException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			attacker = null;
		} catch (TauntBypassException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			attacker = null;
		} catch (NotSummonedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			attacker = null;
		} catch (HeroPowerAlreadyUsedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (FullHandException e1) {
			updateScreen();
			JOptionPane.showMessageDialog(null, "", "Your Hand was full, this card was burnt",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/" + e1.getBurned().getName() + ".png"));
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			//JOptionPane.showMessageDialog(null, "UNEXPECTED ERROR PLEASE REPORT");
		}
	}

	public static void main(String[] args) throws IOException {
		new Selector();
	}

}
