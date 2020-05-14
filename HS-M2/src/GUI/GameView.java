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
	private JButton callAFreind;
	public JButton getCallAFreind() {
		return callAFreind;
	}

	private JLabel cardDisplay;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	public GameView() throws IOException 
	{
		super();		
		this.setVisible(true);
		this.setBounds(500, 200, 1100, 650);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setResizable(false);

		oText = new JTextArea();
		cText = new JTextArea();
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(750,this.getHeight()));
		this.add(mainPanel,BorderLayout.CENTER);
		
		
		info.setPreferredSize(new Dimension(350,this.getHeight()));
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
		cardDisplay.setSize(info.getWidth(),(int)(info.getHeight()*0.3));
	    addComp(info, oText, 0, 0, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    addComp(info, cardDisplay, 0, 1, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    addComp(info, cText, 0, 2, 1, 1, GridBagConstraints.BOTH, 1, 1);
	    
	    this.add(info,BorderLayout.EAST);
	    cText.setText("current Hero");
	    oText.setText("Opponent");
	    cardDisplay.setSize(info.getWidth(),(int)(this.getHeight()*0.6));
	    cardDisplay.setMaximumSize(cardDisplay.getSize());
	    cardDisplay.setIcon(new ImageIcon("images/card.png"));
		mainPanel.setLayout(new GridLayout(4,0));
		opponentHandPanel.setLayout(new GridLayout(0,10));
		currentHandPanel.setLayout(new GridLayout(0,10));		

		mainPanel.add(opponentHandPanel);
		mainPanel.add(opponentFieldPanel);
		mainPanel.add(currentFieldPanel);
		mainPanel.add(currentHandPanel);
		
		//callAFreind = new JButton("Call A Friend");
		
		JPanel buttonsBottom = new JPanel();
		buttonsBottom.add(heroPower);
		//buttonsBottom.add(callAFreind);
		buttonsBottom.add(endTurn);
		
		this.add(buttonsBottom,BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
	}
	
	

	public void setCurrentFieldPanel(JPanel currentFieldPanel) {
		this.currentFieldPanel = currentFieldPanel;
	}

	public void setOpponentFieldPanel(JPanel opponentFieldPanel) {
		this.opponentFieldPanel = opponentFieldPanel;
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
