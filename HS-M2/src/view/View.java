package view;

import java.awt.*;
import javax.swing.*;





@SuppressWarnings("serial")
public class View extends JFrame{
	
	
	private JPanel mainPanel;
	private JPanel currentHandPanel = new JPanel();
	private JPanel opponentHandPanel = new JPanel();
	private JPanel currentFieldPanel = new JPanel();
	private JPanel opponentFieldPanel = new JPanel();
	private JPanel info = new JPanel();
	
	private JTextArea cText;
	private JTextArea oText;
	
	
	public View() 
	{
		super();
		
		
		this.setVisible(true);
		this.setBounds(500, 500, 1050, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		oText = new JTextArea();
		cText = new JTextArea();
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(750,this.getHeight()));
		this.add(mainPanel,BorderLayout.CENTER);
		info.setPreferredSize(new Dimension(300,this.getHeight()));
		info.setLayout(new GridLayout(2,0));
		
		oText.setEditable(false);
		cText.setEditable(false);
		oText.setFont(oText.getFont().deriveFont(32f));
	    info.add(oText);
		cText.setFont(cText.getFont().deriveFont(32f));
	    info.add(cText);
	    
	    this.add(info,BorderLayout.EAST);
	    
		
		mainPanel.setLayout(new GridLayout(4,0));
		opponentHandPanel.setLayout(new GridLayout(0,7));
		currentHandPanel.setLayout(new GridLayout(0,7));

		
		
		mainPanel.add(opponentHandPanel);
		mainPanel.add(opponentFieldPanel);
		mainPanel.add(currentFieldPanel);
		mainPanel.add(currentHandPanel);
		
		
		
		this.revalidate();
		this.repaint();
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


	public JTextArea getcText() {
		return cText;
	}


	public JTextArea getoText() {
		return oText;
	}
	
	public static void main(String[] args)
	{
		new View();
	}
}
