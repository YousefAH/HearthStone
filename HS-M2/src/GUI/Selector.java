package GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import exceptions.FullHandException;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class Selector extends JFrame implements ActionListener
{
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	
	private JPanel player1Buttons = new JPanel();
	private JPanel player1Images = new JPanel();
	private JPanel player2Buttons = new JPanel();
	private JPanel player2Images = new JPanel();
	
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
	
	private JPanel main = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	public Selector()
	{
		this.setVisible(true);
		this.setBounds(500, 200, 1100, 650);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel back = new JLabel();
        back.setIcon(new ImageIcon("images/Selector Background.png"));

		main.setLayout(new GridBagLayout());
		main.setOpaque(false);
		
		this.add(back);
		JLabel label1 = new JLabel("Player one");
		label1.setFont(label1.getFont().deriveFont(32f));
		addComp(main, label1, 0, 0, 1, 1, 0, 1, 1);
		
		JLabel paladin1 = new JLabel(new ImageIcon("images/Paladin.png"));
		JLabel warlock1 = new JLabel(new ImageIcon("images/Warlock.png"));
		JLabel priest1 = new JLabel(new ImageIcon("images/Priest.png"));
		JLabel hunter1 = new JLabel(new ImageIcon("images/Hunter.png"));
		JLabel mage1 = new JLabel(new ImageIcon("images/Mage.png"));
		JLabel paladin2 = new JLabel(new ImageIcon("images/Paladin.png"));
		JLabel warlock2 = new JLabel(new ImageIcon("images/Warlock.png"));
		JLabel priest2 = new JLabel(new ImageIcon("images/Priest.png"));
		JLabel hunter2 = new JLabel(new ImageIcon("images/Hunter.png"));
		JLabel mage2 = new JLabel(new ImageIcon("images/Mage.png"));
				
		player1Images.add(paladin1);
		player1Images.add(mage1);
		player1Images.add(hunter1);
		player1Images.add(warlock1);
		player1Images.add(priest1);
		addComp(main, player1Images, 0, 1, 1, 1, 0, 1, 1);

	    GridLayout layout = new GridLayout(0,5);
	    layout.setHgap(180);
	    
	    cPaladin.setFont(cPaladin.getFont().deriveFont(20f));
		cPriest.setFont(cPriest.getFont().deriveFont(20f));
		cWarlock.setFont(cWarlock.getFont().deriveFont(20f));
		cMage.setFont(cPaladin.getFont().deriveFont(20f));
		cHunter.setFont(cPaladin.getFont().deriveFont(20f));
	    
		player1Buttons.setLayout(layout);
		player1Buttons.add(cPaladin);
		player1Buttons.add(cMage);
		player1Buttons.add(cHunter);
		player1Buttons.add(cWarlock);
		player1Buttons.add(cPriest);
		
		group1.add(cPaladin);
		group1.add(cMage);
		group1.add(cHunter);
		group1.add(cWarlock);
		group1.add(cPriest);
		addComp(main, player1Buttons, 0, 2, 1, 1, 0, 1, 1);
		
		JLabel label2 = new JLabel("Player two");
		label2.setFont(label2.getFont().deriveFont(32f));
		addComp(main, label2, 0, 3, 1, 1, 0, 1, 1);

		
		player2Images.add(paladin2);
		player2Images.add(mage2);
		player2Images.add(hunter2);
		player2Images.add(warlock2);
		player2Images.add(priest2);
		addComp(main, player2Images, 0, 4, 1, 1, 0, 1, 1);

		oPaladin.setFont(oPaladin.getFont().deriveFont(20f));
		oPriest.setFont(oPriest.getFont().deriveFont(20f));
		oWarlock.setFont(oWarlock.getFont().deriveFont(20f));
		oMage.setFont(oPaladin.getFont().deriveFont(20f));
		oHunter.setFont(oPaladin.getFont().deriveFont(20f));
		
		player2Buttons.setLayout(layout);
		player2Buttons.add(oPaladin);
		player2Buttons.add(oMage);
		player2Buttons.add(oHunter);
		player2Buttons.add(oWarlock);
		player2Buttons.add(oPriest);
		
		group2.add(oPaladin);
		group2.add(oMage);
		group2.add(oHunter);
		group2.add(oWarlock);
		group2.add(oPriest);		
		addComp(main, player2Buttons, 0, 5, 1, 1, 0, 1, 1);
		
//		submit choices
		JButton b = new JButton("Click");
		b.addActionListener(this);
		addComp(main, b, 0, 6, 1, 1, 0, 1, 1);

		this.add(main);
		
		//making all components transparent
		player1Images.setOpaque(false);
		player1Buttons.setOpaque(false);
		player2Images.setOpaque(false);
		player2Buttons.setOpaque(false);
		for (Enumeration<AbstractButton> buttons = group1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setOpaque(false);
		}
		for (Enumeration<AbstractButton> buttons = group2.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setOpaque(false);
		}
 
		
		this.repaint();
		this.revalidate();
	}
	private void addComp(JPanel panel, JComponent comp, int x, int y, int gWidth, int gHeight, int fill, double weightx,double weighty) {
		//https://stackoverflow.com/questions/42594962/making-a-component-span-multiple-rows-with-gridbaglayout
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gWidth;
		gbc.gridheight = gHeight;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		panel.add(comp, gbc);
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
	
	public static void main(String[] args) {
		new Selector();
	}
}