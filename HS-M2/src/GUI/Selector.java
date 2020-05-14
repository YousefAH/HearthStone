package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.*;

import exceptions.FullHandException;
import model.heroes.*;

@SuppressWarnings("serial")
public class Selector extends JFrame implements ActionListener
{
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	
	private JPanel player1Buttons = new JPanel();
	private JPanel player1Images = new JPanel();
	private JPanel player2Buttons = new JPanel();
	private JPanel player2Images = new JPanel();
	
	private JRadioButton cPaladin = new JRadioButton("<html><font color='white'>Paladin</font></html>");
	private JRadioButton cMage = new JRadioButton("<html><font color='white'>Mage</font></html>");
	private JRadioButton cHunter = new JRadioButton("<html><font color='white'>Hunter</font></html>");
	private JRadioButton cWarlock = new JRadioButton("<html><font color='white'>Warlock</font></html>");
	private JRadioButton cPriest = new JRadioButton("<html><font color='white'>Priest</font></html>");
	private JRadioButton oPaladin = new JRadioButton("<html><font color='white'>Paladin</font></html>");
	private JRadioButton oMage = new JRadioButton("<html><font color='white'>Mage</font></html>");
	private JRadioButton oHunter = new JRadioButton("<html><font color='white'>Hunter</font></html>");
	private JRadioButton oWarlock = new JRadioButton("<html><font color='white'>Warlock</font></html>");
	private JRadioButton oPriest = new JRadioButton("<html><font color='white'>Priest</font></html>");
	
	private JTextArea label1 = new JTextArea("Player 1");
	private JTextArea label2 = new JTextArea("Player 2");

	private JPanel main = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	public Selector() throws IOException
	{
		
		this.setVisible(true);
		setLayout(new BorderLayout());
		setContentPane((new JLabel(new ImageIcon("images/Selector Background.png"))));
		setResizable(false);
		setLayout(new FlowLayout());		
		this.setBounds(500, 200, 1100, 650);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.setLayout(new GridBagLayout());
		main.setOpaque(false);
		
		label1.setFont(label1.getFont().deriveFont(32f));
		label1.setForeground(Color.WHITE);
		addComp(main, label1, 0, 0, 1, 1, 0, 1, 1);
		
		
		ImageIcon paladin = new ImageIcon("images/Paladin.png");
		ImageIcon warlock = new ImageIcon("images/Warlock.png");
		ImageIcon priest  = new ImageIcon("images/Priest.png");
		ImageIcon hunter = new ImageIcon("images/Hunter.png");
		ImageIcon mage  = new ImageIcon("images/Mage.png");
		ImageIcon[] icons = {paladin,warlock,priest,hunter,mage};
		
		JLabel paladin1 =  new JLabel();
		JLabel warlock1 =  new JLabel();
		JLabel priest1 =  new JLabel();
		JLabel hunter1 =  new JLabel();
		JLabel mage1 =  new JLabel();
		
		JLabel paladin2 =  new JLabel();
		JLabel warlock2 =  new JLabel();
		JLabel priest2 =  new JLabel();
		JLabel hunter2 =  new JLabel();
		JLabel mage2 =  new JLabel();
		
		JLabel[] labels1 = {paladin1,warlock1,priest1,hunter1,mage1};
		JLabel[] labels2 = {paladin2,warlock2,priest2,hunter2,mage2};

				
		player1Images.add(paladin1);
		player1Images.add(mage1);
		player1Images.add(hunter1);
		player1Images.add(warlock1);
		player1Images.add(priest1);
		player1Images.setSize(1, 1);
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
		
		label2.setFont(label2.getFont().deriveFont(32f));
		label2.setForeground(Color.WHITE);
		addComp(main, label2, 0, 3, 1, 1, 0, 1, 1);
		
		label1.setOpaque(false);
		label2.setOpaque(false);

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
		
		for (int i = 0; i < icons.length; i++) 
		{
			Image img = icons[i].getImage();
			labels1[i].setSize(icons[i].getIconWidth(),(int)(this.getHeight()*0.36));
			Image newimg = img.getScaledInstance(labels1[i].getWidth(), labels1[i].getHeight(), Image.SCALE_DEFAULT);
			icons[i] = new ImageIcon(newimg);
			labels1[i].setIcon(icons[i]);
			labels2[i].setIcon(icons[i]);
		}
		
		JOptionPane.showMessageDialog(null, "Pick a class and type your name in the player field");
		
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
					new Controller(p1, p2,label1.getText(),label2.getText());
					this.dispose();
				}
			} catch (IOException | CloneNotSupportedException | FullHandException e1) {
				JOptionPane.showConfirmDialog(null, e1.getMessage());
			}
	}
	
	public static void main(String[] args) throws IOException {
		new Selector();
	}
}