package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.*;
import engine.*;
import exceptions.FullHandException;
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
	private ArrayList<JButton> cHand;
	private ArrayList<JButton> cField;
	private ArrayList<JButton> oField;
	private ArrayList<JButton> oHand;
	
	
	public Controller(Hero p1, Hero p2) throws IOException, FullHandException, CloneNotSupportedException 
	{
		model = new GameView();
		g = new Game(p1, p2);
		model.getcText().setText(p1.getName());
		model.getoText().setText(p2.getName());
	}

	
	public void onGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new Selector();

	}

}
