package GUI;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameView extends JFrame  {
	
	
	private JPanel mainPanel;
	private JPanel currentHandPanel = new JPanel();
	private JPanel opponentHandPanel = new JPanel();
	private JPanel currentFieldPanel = new JPanel();
	private JPanel opponentFieldPanel = new JPanel();
	private JPanel info = new JPanel();
	
	private JTextArea cText;
	private JTextArea oText;
	
	private JButton heroPower;
	private JButton endTurn;
	private JLabel cardDisplay;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	public GameView() throws IOException 
	{
		super();		
		this.setVisible(true);
		this.setBounds(500, 200, 1050, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		oText = new JTextArea();
		cText = new JTextArea();
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(750,this.getHeight()));
		this.add(mainPanel,BorderLayout.CENTER);
		
		
		info.setPreferredSize(new Dimension(300,this.getHeight()));
		info.setLayout(new GridBagLayout());
		
			
		oText.setEditable(false);
		cText.setEditable(false);
		oText.setFont(oText.getFont().deriveFont(32f));
		cText.setFont(cText.getFont().deriveFont(32f));
		
		heroPower = new JButton("Hero Power");
		heroPower.setFont(heroPower.getFont().deriveFont(32f));
		endTurn = new JButton("End Turn");
		endTurn.setFont(endTurn.getFont().deriveFont(32f));
		cardDisplay = new JLabel();
		
	    addComp(info, oText, 0, 0, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    addComp(info, cardDisplay, 0, 1, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    addComp(info, cText, 0, 2, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    
	    this.add(info,BorderLayout.EAST);
	    cText.setText("current Hero");
	    oText.setText("Opponent");
	    cardDisplay.setIcon(new ImageIcon("card.png"));
		mainPanel.setLayout(new GridLayout(4,0));
		opponentHandPanel.setLayout(new GridLayout(0,10));
		currentHandPanel.setLayout(new GridLayout(0,10));		

		mainPanel.add(opponentHandPanel);
		mainPanel.add(opponentFieldPanel);
		mainPanel.add(currentFieldPanel);
		mainPanel.add(currentHandPanel);
		
		JPanel buttonsBottom = new JPanel();
		buttonsBottom.add(heroPower);
		buttonsBottom.add(endTurn);
		this.add(buttonsBottom,BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
	}
	
	public void setCurrentHandPanel(JPanel currentHandPanel) {
		this.currentHandPanel = currentHandPanel;
	}

	public void setOpponentHandPanel(JPanel opponentHandPanel) {
		this.opponentHandPanel = opponentHandPanel;
	}

	public JButton getHeroPower() {
		return heroPower;
	}
	public JLabel getCardDisplay() {
		return cardDisplay;
	}
	public JButton getEndTurn() {
		return endTurn;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	public JPanel getCurrentHandPanel() {
		return currentHandPanel;
	}
	public JPanel getOpponentHandPanel() {
		return opponentHandPanel;
	}
	public JPanel getCurrentFieldPanel() {
		return currentFieldPanel;
	}
	public JPanel getOpponentFieldPanel() {
		return opponentFieldPanel;
	}
	public JPanel getInfo() {
		return info;
	}

	private void addComp(JPanel panel, JComponent comp, int x, int y, int gWidth, int gHeight, int fill, double weightx,double weighty) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gWidth;
		gbc.gridheight = gHeight;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		panel.add(comp, gbc);
	}
	public static void main(String[] args) throws IOException
	{
		new GameView();
	}

	public JTextArea getcText() {
		return cText;
	}

	public JTextArea getoText() {
		return oText;
	}

	
}
