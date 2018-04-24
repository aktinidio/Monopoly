import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class GUI extends JFrame implements ActionListener {
	
	private JPanel platform, log, dicePanel;
	private JPanel[] tabPanels;
	private GridBagConstraints c,c2;
	private JTextArea textArea;
	private MyLabel[] labels, head;
	private JLabel[] names, money, prison, card, paizi, thesi, avatar;
	private JScrollPane scrollPane;
	private MyLabel center;
	private JLabel centerLabel;
	private JButton rollButton;
	private JTabbedPane tabbedPane;
	private Dice diceL, diceR;
	private GameSystem gs;
	
	
	public GUI(GameSystem gs){
		
		super("Blogopoly");
		this.gs = gs;
				
		this.setLayout(new BorderLayout());
		
		platform = new JPanel(new GridBagLayout());
		log = new JPanel(new GridLayout(2,1));
		
		platform.setBorder(new LineBorder(Color.BLACK));
		log.setBorder(new LineBorder(Color.BLACK));
		
		add(log,BorderLayout.EAST);
		add(platform,BorderLayout.CENTER);
		
		createBoard();
				
		textArea = new JTextArea(20,28);
    	textArea.setEditable(false);
    	DefaultCaret caret = (DefaultCaret)textArea.getCaret();
    	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    	scrollPane = new JScrollPane(textArea);
    	scrollPane.setBorder(new LineBorder(Color.BLACK));
    	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	log.add(scrollPane);   	   	
    	
    	setNumberOfPlayers();
    	
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setSize(1150,840);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createBoard(){
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		
		labels = new MyLabel[32];
		head = new MyLabel[32];
		
		for(int j=0;j<2;j++)
		for(int i=2+(j*4);i<5+(j*4);i++){
			c.gridx = 0;
		    c.gridy = i;
		    c.weightx = 1.1;
		    c.weighty = 1.1;
		    labels[17-i] = new MyLabel(17-i);
			platform.add(labels[17-i], c);
			
			c.gridx = 1;
		    c.weightx = 0.2;
		    head[17-i] = new MyLabel();
			platform.add(head[17-i], c);
			////////////
			
			c.gridx = 10;
		    c.weightx = 1.1;
		    labels[i+23] = new MyLabel(i+23);
			platform.add(labels[i+23], c);
			
			c.gridx = 9;
		    c.weightx = 0.2;
		    head[i+23] = new MyLabel();
			platform.add(head[i+23], c);
			////////////
			
			c.gridx = i;
		    c.gridy = 0;
		    c.weightx = 1.1;
		    labels[i+15] = new MyLabel(i+15);
			platform.add(labels[i+15], c);
			
			c.gridy = 1;
		    c.weighty = 0.3;
		    head[i+15] = new MyLabel();
			platform.add(head[i+15], c);
			////////////
			
		    c.gridy = 10;
		    c.weighty = 1.1;
		    labels[9-i] = new MyLabel(9-i);
			platform.add(labels[9-i], c);
			
			c.gridy = 9;
		    c.weighty = 0.3;
		    head[9-i] = new MyLabel();
			platform.add(head[9-i], c);
		}
		
		c.weighty=1;
		//c.weightx=2;
		c.gridheight = 2;
		c.gridx = 5;
		c.gridy = 0;
		labels[20] = new MyLabel(20);
		platform.add(labels[20], c);

		c.gridy = 9;
		labels[4] = new MyLabel(4);
		platform.add(labels[4], c);
		
		c.gridheight = 1;
		c.gridwidth = 2;
		
		c.gridx = 0;
		c.gridy = 5;
		labels[12] = new MyLabel(12);
		platform.add(labels[12], c);

		c.gridx = 9;
		labels[28] = new MyLabel(28);
		platform.add(labels[28], c);
				
		c.gridheight = 2;
	    
	    for(int i=0;i<4;i++){
	    	c.gridx = (1-(1+(i % 3)) / 2)*9;
	    	c.gridy = (1-(i / 2))*9;
	    	labels[i*8] = new MyLabel(i*8);
			platform.add(labels[i*8], c);
			labels[i*8].setLayout(new GridLayout(2,1));
	    }
		
	    c.gridwidth = 8;
		c.gridheight = 8;
		c.gridx = 2;
    	c.gridy = 2;
    	center = new MyLabel(-1);
    	center.setLayout(new GridBagLayout());
    	dicePanel = new JPanel();
    	centerLabel = new JLabel("Blogopoly");
    	centerLabel.setFont(new Font("Curlz MT", Font.PLAIN, 110));
    	centerLabel.setForeground(new Color(123,13,13));
    	c2 = new GridBagConstraints();
    	c2.ipady=100;
    	center.add(centerLabel,c2);
    	c2.gridx=0;
    	c2.ipady=0;
    	center.add(dicePanel,c2);
    	
    	
    	platform.add(center, c);
    	
    	diceL  = new Dice(1, gs);
        diceR = new Dice(2);
                        
        rollButton = new JButton("Ρίξε τα ζάρια");
        rollButton.setFocusable(false);
        rollButton.setFont(new Font("Sansserif", Font.PLAIN, 18));
        rollButton.addActionListener(this);
        
        dicePanel.setLayout(new BorderLayout(5, 10));
        dicePanel.add(rollButton, BorderLayout.NORTH);
        dicePanel.add(diceL , BorderLayout.WEST);
        dicePanel.add(diceR, BorderLayout.EAST);
        
    	for(int i=0;i<3;i++){
			head[i+1].setBackground(Color.BLUE);
	    	head[i+5].setBackground(Color.CYAN);
	    	head[i+9].setBackground(Color.GREEN);
	    	head[i+13].setBackground(Color.MAGENTA);
	    	head[i+17].setBackground(Color.ORANGE);
	    	head[i+21].setBackground(Color.PINK);
	    	head[i+25].setBackground(Color.RED);
	    	head[i+29].setBackground(Color.YELLOW);
		}
	}
	
	private void createTabedPanel(int size){
		UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
		UIManager.put("TabbedPane.contentAreaColor", Color.LIGHT_GRAY);
		tabbedPane = new JTabbedPane();
        
        tabPanels = new JPanel[size];
        names = new JLabel[size];
        money = new JLabel[size];
        prison = new JLabel[size];
        card = new JLabel[size];
        paizi = new JLabel[size];
        thesi = new JLabel[size];
        
        for(int i=0;i<size;i++){
        	tabPanels[i] = new JPanel(new GridLayout(7,1));
        	
        	names[i] = new JLabel();
        	money[i] = new JLabel();
        	prison[i] = new JLabel();
        	card[i] = new JLabel();
        	paizi[i] = new JLabel();
        	thesi[i] = new JLabel();
        	
        	tabPanels[i].add(names[i]);
        	tabPanels[i].add(money[i]);
        	tabPanels[i].add(prison[i]);
        	tabPanels[i].add(card[i]);
        	tabPanels[i].add(paizi[i]);
        	tabPanels[i].add(thesi[i]);
        	
        	tabbedPane.addTab("Player "+(i+1),tabPanels[i]);
        }                        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        switch(size){
        	case 4: tabPanels[3].setBackground(new Color(255,203,244));
        	case 3: tabPanels[2].setBackground(new Color(185,255,118));			
        }
        tabPanels[1].setBackground(new Color(246,237,94));
        tabPanels[0].setBackground(new Color(186,246,240));
		        
        log.add(tabbedPane);
	}
	
	private void setNumberOfPlayers(){
		Object[] possibilities = {2,3,4};
		Integer size = (Integer)JOptionPane.showInputDialog(null, "Παρακαλώ επιλέξτε τον αριθμό των παιτκών.\n", "Αριθμός Παικτών", JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);
		if(size==null) System.exit(0);
		
		createTabedPanel(size);
		avatar = new JLabel[size];
		for(int i=0;i<size;i++){
			ImageIcon icon = new ImageIcon("Images/B"+(i+1)+".png");
			Image img = icon.getImage();
			avatar[i] = new JLabel(new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_SMOOTH)));
			labels[0].add(avatar[i]);
		}
	}
	
	public void setNames(String[] names){
		for(int i=0;i<32;i++){ 
			labels[i].setText(names[i]);
			labels[i].setPreferredSize(new Dimension(70,70));
		}
	}
	
	public void setStatus(int player,String name, int newMoney, boolean isPrisoned,boolean hasCard,boolean alive,int position){
		
		names[player].setText(" Όνομα παίκτη: "+name);
		
		money[player].setText(" Χρήματα παίκτη: "+newMoney+"€");
		
		if(isPrisoned) prison[player].setText(" Φυλακισμένος: Ναι");
		else prison[player].setText(" Φυλακισμένος: Όχι");
		
		if(hasCard) card[player].setText(" Έχει κάρτα: Ναι");
		else card[player].setText(" Έχει κάρτα: Όχι");
		
		if(alive) paizi[player].setText(" Είναι στο παιχνίδι: Ναι");
		else paizi[player].setText(" Είναι στο παιχνίδι: Όχι");
		
		thesi[player].setText(" Θέση παίκτη: "+position);
	}
	
	public void setStatusMoney(int player, int newMoney){
		money[player].setText(" Χρήματα παίκτη: "+newMoney+"€");
	}
	
	public void addText(String s){
		textArea.append("  " + s + "\n");
	}
	
	public boolean askPlayer(String message){
		
		Object[] options = {"Ναι", "Όχι"};
		int n = JOptionPane.showOptionDialog(null, message, "Ερώτηση?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if(n==0) return true;
		return false;
	}
	
	public void warnPlayer(String message){
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Προσοχή!");
		dialog.setVisible(true);
	}
	
	public void setOwner(int player, int posotion){
		switch(player){
			case 0: labels[posotion].setBackground(new Color(186,246,240)); break;
			case 1: labels[posotion].setBackground(new Color(246,237,94)); break;
			case 2: labels[posotion].setBackground(new Color(185,255,118)); break;
			case 3: labels[posotion].setBackground(new Color(255,203,244)); break;
		}		
	}
	
	public int getNumberOfPlayers(){
		return avatar.length;
	}
	
	public void buildHouse(int position){
		c = new GridBagConstraints();
		if(head[position].getWidth() < head[position].getHeight()) c.gridx=0;
		head[position].add(new JLabel(new ImageIcon("Images/house.png")),c);
	}
	
	public void demolishProperty(int position){
		head[position].setIcon(new ImageIcon("Images/null.png"));
		labels[position].setBackground(null);
	}
	
	public void movePlayer(int player, int position){
		labels[position].add(avatar[player]);
		this.repaint();
		this.invalidate();
		this.validate();
	}
	
	public void killPlayer(int player){
		avatar[player].setIcon(null);
	}
	
	public void setSelectedIndex(int player){
		tabbedPane.setSelectedIndex(player);
	}
	
	public void setDices(int number1, int number2){
		diceL.setNumber(number1);
    	diceR.setNumber(number2);
    	this.repaint();
	}
	
	public void rollDices(boolean flag){
		if(flag) diceL.roll(true);
		else diceL.roll(false);
		diceR.roll(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		rollDices(true);
    }
}
