package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.*;
import engine.*;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;


class Selector extends JFrame implements ActionListener
{
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	private JPanel player1 = new JPanel();
	private JPanel player2 = new JPanel();
	private JRadioButton cPaladin = new JRadioButton("Paladin");
	private JRadioButton cMage = new JRadioButton("Mage");
	private JRadioButton cHunter = new JRadioButton("Hunter");
	private JRadioButton cWarlock = new JRadioButton("Warlock");
	private JRadioButton cPriest = new JRadioButton("Priest");
	private JRadioButton oPaladin = new JRadioButton("Paladin");
	private JRadioButton oMage = new JRadioButton("Mage");
	private JRadioButton oHunter = new JRadioButton("Hunter");
	private JRadioButton oWarlock = new JRadioButton("Warlock");
	private JRadioButton oPriest = new JRadioButton("Priest");
	public Selector()
	{
		this.setVisible(true);
		this.setBounds(500, 200, 500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		JLabel label1 = new JLabel("Player one");
		label1.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(label1);
		player1.add(cPaladin);
		player1.add(cMage);
		player1.add(cHunter);
		player1.add(cWarlock);
		player1.add(cPriest);
		
		group1.add(cPaladin);
		group1.add(cMage);
		group1.add(cHunter);
		group1.add(cWarlock);
		group1.add(cPriest);
		
		this.add(player1);
		
		JLabel label2 = new JLabel("Player two",FlowLayout.TRAILING);
		label2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(label2);
		player2.add(oPaladin);
		player2.add(oMage);
		player2.add(oHunter);
		player2.add(oWarlock);
		player2.add(oPriest);
		
		group2.add(oPaladin);
		group2.add(oMage);
		group2.add(oHunter);
		group2.add(oWarlock);
		group2.add(oPriest);
		
		this.add(player2);
		JButton b = new JButton("Click");
		b.addActionListener(this);
		this.add(b);
	}
	public void actionPerformed(ActionEvent e) 
	{
		Hero p1 = null, p2 = null;
			try {
				if(cPaladin.isSelected())
					p1 = new Paladin();
				else if(cMage.isSelected())
					p1 = new Mage();
				else if(cPriest.isSelected())
					p1 = new Priest();
				else if(cHunter.isSelected())
					p1 = new Hunter();
				else if(cWarlock.isSelected())
					p1 = new Warlock();
				
				if(oPaladin.isSelected())
					p2 = new Paladin();
				else if(oMage.isSelected())
					p2 = new Mage();
				else if(oPriest.isSelected())
					p2 = new Priest();
				else if(oHunter.isSelected())
					p2 = new Hunter();
				else if(oWarlock.isSelected())
					p2 = new Warlock();
				if(p1 != null && p2 != null)
				{
					if(p1.getName().equals(p2.getName())) {
						p1.setName(p1.getName()+"(1)");
						p2.setName(p2.getName()+"(2)");
					}
					new Controller(p1, p2);
					this.dispose();
				}
			} catch (IOException | CloneNotSupportedException | FullHandException e1) {
				e1.printStackTrace();
			}
		
	}
}

public class Controller implements ActionListener, GameListener 
{
	private Game g;
	private GameView model;
	private ArrayList<JButton> cHand = new ArrayList<JButton>();
	private ArrayList<JButton> cField = new ArrayList<JButton>();
	private ArrayList<JButton> oField = new ArrayList<JButton>();
	private ArrayList<JButton> oHand = new ArrayList<JButton>();
	
	
	public Controller(Hero p1, Hero p2) throws IOException, FullHandException, CloneNotSupportedException 
	{
		
		model = new GameView();
		g = new Game(p1, p2);
		g.getCurrentHero().setCurrentManaCrystals(10);
		model.getcText().setText(g.getCurrentHero().getName()+"\nMana: "+g.getCurrentHero().getCurrentManaCrystals()+"\nHp: "+g.getCurrentHero().getCurrentHP());
		model.getoText().setText(g.getOpponent().getName()+"\nMana: "+g.getOpponent().getCurrentManaCrystals()+"\nHp: "+g.getOpponent().getCurrentHP());
		genButtonHand(g.getCurrentHero().getHand(), cHand,model.getCurrentHandPanel());
		genButtonHand(g.getOpponent().getHand(), oHand,model.getOpponentHandPanel());
	}

	public void genButtonHand(ArrayList<Card> hand, ArrayList<JButton> l,JPanel p )
	{
		for (int i = 0; i < hand.size(); i++) 
		{
			JButton b = new JButton(hand.get(i).getName()+"\n"+hand.get(i).getManaCost());
			l.add(b);
			p.add(b);
			b.addActionListener(this);
		}
	}
	
	public void onGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		try {
			System.out.println(cHand.indexOf(e.getSource()));
			if(cHand.indexOf(e.getSource())==-1)
				throw new NotYourTurnException();
			Card c = g.getCurrentHero().getHand().get(cHand.indexOf(e.getSource()));
			Hero targetHero = null;
			Minion targetMinion = null;
			if (c instanceof Minion) {
				g.getCurrentHero().playMinion((Minion) g.getCurrentHero().getHand().get(cHand.indexOf(e.getSource())));
				model.getCurrentHandPanel().remove(cHand.indexOf(e.getSource()));
				cField.add(cHand.get(cHand.indexOf(e.getSource())));
				model.getCurrentFieldPanel().add(cHand.get(cHand.indexOf(e.getSource())));
				cHand.remove(cHand.get(cHand.indexOf(e.getSource())));
			}
			else if (c instanceof Spell) {
				if (c instanceof AOESpell)
					g.getCurrentHero().castSpell((AOESpell) c, g.getOpponent().getField());
				else if (c instanceof FieldSpell)
					g.getCurrentHero().castSpell((FieldSpell) c);
				else if (c instanceof HeroTargetSpell)
					g.getCurrentHero().castSpell((HeroTargetSpell) c, targetHero);
				else if (c instanceof LeechingSpell)
					g.getCurrentHero().castSpell((LeechingSpell) c, targetMinion);
				else if (c instanceof MinionTargetSpell)
					g.getCurrentHero().castSpell((MinionTargetSpell) c, targetMinion);
			}
			model.getcText().setText(g.getCurrentHero().getName()+"\nMana: "+g.getCurrentHero().getCurrentManaCrystals()+"\nHp: "+g.getCurrentHero().getCurrentHP());
		} catch (NotYourTurnException e1) {
			JOptionPane.showMessageDialog(null, "Not Your Turn");
		} catch (NotEnoughManaException e1) {
			JOptionPane.showMessageDialog(null, "Not Enough Mana");
		} catch (FullFieldException e1) {
			JOptionPane.showMessageDialog(null, "Field is Full");
		} catch (InvalidTargetException e1) {
			JOptionPane.showMessageDialog(null, "Invalid Target");
		}
	}

	public static void main(String[] args) 
	{
		new Selector();

	}

}
