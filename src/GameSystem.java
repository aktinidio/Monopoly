import java.util.Random;

public class GameSystem{
	
	private int numberOfPlayersLeft = 0, numberOfPlayers = 0, currentPlayerInt = 0;
	private Player[] playerArray;
	private Square[] squareArray;
	private Card[] cardArray;
	private Dices dices;
	private GUI GUI;

	private int[] landPrices = {00000,60,60,80,00,100,100,120,00,140,140,160,000,180,180,200,000,220,220,240,000,260,260,280,000,300,300,320,000,350,400,450},
				  housePrices = {0000,50,50,50,00,50,50,50,00000,100,100,100,000,100,100,100,000,150,150,150,000,150,150,150,000,200,200,200,000,200,200,200},
				  landRents = {000,2,4,4,000,6,6,8,000,10,10,12,000,14,14,16,000,18,18,20,000,22,22,24,000,26,26,28,000,35,50,65},
				  houseRents = {000,15,25,30,0000,45,45,55,000,65,65,80,000,90,90,100,0000,110,110,120,000,130,130,140,000,150,150,160,000,180,200,220},
				  cardAmount = {0,50,-80,30,50,0,100,100,-40,30,-20},
				  cardPosition = {0,0,0,0,0,8,0,0,0,0,0};
	
	private String[] names = {"Start","avast","Kaspersky","AVG","�������","Intel","AMD","ASUS","Prison","Oracle","Wikipedia","���","�������","Firefox","Chrome","Opera","Parking","Arduino","Open Hardware","Raspberry","�������","Java","c++","Android App","Police","Tor","Greek Hackers","Anonymous","�������","Fedora","BackTrack","Ubuntu"},
	cardName = {"���� ��� ��� ������!",
				"������� �� ������� ���������������! ������������ ���� 50�",
				"��� ������ �����, ���������� �� ������������� ��������� Microsoft. ������� 80�",
				"� �. ������������� ������������� �� ��� �������, ���� 30�",
				"��� �� google ads, �������� 50�",
				"��� ������� ���� �� ���� ���, ������� ������ ���� ������",
				"����� ��� ���� ����� ��������� hacker, ���� 100�",
				"��� ��� ������ ��� ������ ��� ������ ��� ebay, �������� 100�",
				"����� �� ������ update �� antivirus ��� ��� ��� �����, ������� 40�",
				"� ��������� ��/��� ��� ��������� 30� ��� ��� ��������� ��� ��� ��������� ��������",
				"���������� �� ��� ������������� ��/���, ������� 20�"};
	
	public GameSystem(){
		GUI = new GUI(this);
		this.initiate();	
	}
	
	private void initiate()	{
		// GUI Message
		numberOfPlayersLeft = numberOfPlayers = GUI.getNumberOfPlayers();
				
		// GUI Message
		GUI.setNames(names);
		
		dices = new Dices();
		playerArray = new Player[numberOfPlayers];
		squareArray = new Square[names.length];
		cardArray = new Card[cardName.length];

		for (int i=0; i<playerArray.length; i++){
			playerArray[i] = new Player("Player "+(i+1), i, 800);
			Player currentPlayer = playerArray[i];
			// GUI Message
			GUI.setStatus(i, currentPlayer.getName(), currentPlayer.howMuchMoney(), currentPlayer.isInJail(), 
					currentPlayer.hasJailCard(), currentPlayer.isInGame(), currentPlayer.getPosition());
			}
		
		for (int i = 0; i <= 31; i++){
			if(i % 4 == 0)
				squareArray[i] = new Square(names[i]);
			else
				squareArray[i] = new PropertySquare(names[i], landPrices[i], housePrices[i], landRents[i], houseRents[i]);
		}
				
		for (int i = 0; i <cardArray.length ; i++)
			cardArray[i] = new Card(cardName[i], cardAmount[i], cardPosition[i]);
	}
	
	public boolean resume()	{
		
		if(numberOfPlayersLeft > 1){
			while(true){
				if(playerArray[currentPlayerInt].isInGame()){
					turn(playerArray[currentPlayerInt]);
					currentPlayerInt = (currentPlayerInt + 1) % numberOfPlayers;
					break;
				}
				currentPlayerInt = (currentPlayerInt + 1) % numberOfPlayers;
			}
			return true;
		}
		else{
			while(true){
				if(playerArray[currentPlayerInt].isInGame()) break;
				currentPlayerInt = (currentPlayerInt + 1) % numberOfPlayers;
			}
			// GUI Message
			if(numberOfPlayersLeft==1) {				
				GUI.warnPlayer("      Thanks for playing!!!\n      \\(~.~)  Bye Bye  (~.~)/");
				System.exit(0);
			}
			String message = "������ � �������: " + playerArray[currentPlayerInt].getName();
			GUI.warnPlayer(message); GUI.addText(message);
			
			numberOfPlayersLeft--;
						
			return false;
		}
	}
	
	private void turn(Player currentPlayer){
		
		String message;
		int doublesCounter = 0;
		boolean replay = true;
		int steps;
		GUI.setSelectedIndex(currentPlayerInt);
		
		while(replay && currentPlayer.isInGame()){
			steps = dices.throwDices();
			// GUI Message
			GUI.setDices(dices.getFirstDice(), dices.getSecondDice());
			// GUI Message
			message = "� ������� "+currentPlayer.getName()+" ����� �� ����� ��� ����� "+dices.getFirstDice()+" + "+dices.getSecondDice()+" = "+steps;
			GUI.addText(message);

			if(dices.areDouble()){
				doublesCounter++;
				if(doublesCounter < 3){
					if(currentPlayer.isInJail()){
						currentPlayer.setJailStatus(false);
						// GUI Message
						message = "� ������� "+currentPlayer.getName()+" ��������� ��� ��� ������";
						GUI.addText(message); GUI.warnPlayer(message);
					}
					play(currentPlayer, steps);
					if(currentPlayer.isInJail()) replay=false;
					else GUI.warnPlayer("� ������� "+currentPlayer.getName()+" ����������, ������ ����� ������!");
				}
				else{
					currentPlayer.setJailStatus(true);
					currentPlayer.setPosition(8);
					// GUI Message
					message = "� ������� " + currentPlayer.getName() + "���� ���� ������ ������ ����� 3 ����� ���������� ������";
					GUI.addText(message); GUI.warnPlayer(message);
					// GUI Message
					GUI.movePlayer(currentPlayer.getID(), currentPlayer.getPosition());
					replay = false;
				}
			}
			else{
				replay = false;
				
				if(currentPlayer.isInJail()){
					if(currentPlayer.hasJailCard()){
						// GUI Message
						if(GUI.askPlayer("��� �� ��������������� ��� ����� ������������;")){
							currentPlayer.changeJailCardStatus(false);
							currentPlayer.setJailStatus(false);
							// GUI Message
							message = "� ������� "+currentPlayer.getName()+" ��������� ��� ��� ������";
							GUI.addText(message); GUI.warnPlayer(message);

							play(currentPlayer, steps);
						}
					}
				}
				else
				{
					play(currentPlayer, steps);
				}
			}
			//if(replay)GUI.rollDices(false);
		}
	}
	
	private void play(Player currentPlayer, int steps){
		
		String message;
		int previousSquareNumber = currentPlayer.getPosition();
		int squareNumber = currentPlayer.movePosition(steps);
		// GUI Message
		GUI.movePlayer(currentPlayer.getID(), currentPlayer.getPosition());
		message = "� ������� "+currentPlayer.getName()+" ������ ��� ���������: "+squareNumber+" - "+squareArray[squareNumber].getFieldName();
		GUI.addText(message);
		
		if(((squareNumber==0)||(squareNumber<previousSquareNumber))&&(squareNumber!=8))
		{
			currentPlayer.addMoney(200);
			GUI.setStatusMoney(currentPlayer.getID(), currentPlayer.howMuchMoney());
			// GUI Message
			message = "� ������� "+currentPlayer.getName()+" ������� 200� ��� ��� ������";
			GUI.addText(message);
		}
		
		switch(squareNumber){
			case 0:	break;
			case 8:
				// GUI Message
				message = "� ������� "+currentPlayer.getName()+" ����� ����� ��� �������� ���� ������";
				GUI.addText(message);
				break;
			
			case 16:
				// GUI Message
				message = "� ������� "+currentPlayer.getName()+" ����� ����� ��� ��������� ��� Parking";
				GUI.addText(message);
				break;

			case 24:
				// GUI Message
				message = "O ������� "+currentPlayer.getName()+" ���� ���� ������ �������� ��� �����������";
				GUI.warnPlayer(message); GUI.addText(message);

				currentPlayer.setJailStatus(true);
				currentPlayer.setPosition(8);
				GUI.movePlayer(currentPlayer.getID(), currentPlayer.getPosition());
				break;
			
			case 4: case 12: case 20: case 28:
				Card currentCard = cardArray[new Random().nextInt(cardArray.length)];
				currentCard.useCard(currentPlayer);
				// GUI Message
				message = "� ������� " + currentPlayer.getName() + " ���� ��� �����: \n"+currentCard.getMessage();
				GUI.warnPlayer(message); GUI.addText(message);
				
				GUI.movePlayer(currentPlayer.getID(), currentPlayer.getPosition());				
				break;
				
			default:
				propertyScenario(currentPlayer, squareNumber);	
				break;
		}
		
		// GUI Message
		GUI.setStatus(currentPlayer.getID(), currentPlayer.getName(), currentPlayer.howMuchMoney(), currentPlayer.isInJail(), currentPlayer.hasJailCard(), currentPlayer.isInGame(), currentPlayer.getPosition());
		
		if(currentPlayer.howMuchMoney()<0) close(currentPlayer);
	}
		
	private void propertyScenario(Player currentPlayer, int squareNumber){
		
		String message ="";
		PropertySquare currentPropSquare = ((PropertySquare)squareArray[squareNumber]);
		
		if(currentPropSquare.getOwner() == null){
			// GUI Message
			if(GUI.askPlayer("��� �� ��������� ��� ���������� "+ currentPropSquare.getFieldName()+";\n ������: "+landPrices[squareNumber]+"�\n")){
				if(currentPropSquare.sellTo(currentPlayer)){
					message = "� ������� " + currentPlayer.getName()+ " �������: "+currentPropSquare.getFieldName();
					GUI.setOwner(currentPlayer.getID(), squareNumber);
					GUI.setStatusMoney(currentPlayer.getID(), currentPlayer.howMuchMoney());
				}
				else{
					message = "� ������� " + currentPlayer.getName()+ " ��� ���� ������ ������� ��� ��� ����� ��� "+currentPropSquare.getFieldName();
					// GUI Message
					GUI.warnPlayer(message);
				}
			}
		}
		else if(currentPropSquare.getOwner() == currentPlayer){
			// GUI Message
			if(GUI.askPlayer("��� �� ������� ��� �������� ���� ���������� "+ currentPropSquare.getFieldName()+";\n ������: "+housePrices[squareNumber]+"�\n")){
				if(currentPropSquare.buildHouse()){
					message = "� ������� "+currentPlayer.getName()+" ������ ���: "+currentPropSquare.getFieldName();
					GUI.buildHouse(squareNumber);
					GUI.setStatusMoney(currentPlayer.getID(), currentPlayer.howMuchMoney());
				}
				else{
					message = "O ������� "+currentPlayer.getName()+" ��� ���� ������ ������� � ���� ������ ��������� ���: "+currentPropSquare.getFieldName();
					// GUI Message
					GUI.warnPlayer(message);
				}
			}
		}
		else{
			currentPropSquare.payRentBy(currentPlayer);
			message = "� ������� "+currentPlayer.getName()+" ������� ����� ���: "+currentPropSquare.getFieldName();
			GUI.setStatusMoney(currentPlayer.getID(), currentPlayer.howMuchMoney());
			GUI.setStatusMoney(currentPropSquare.getOwner().getID(), currentPropSquare.getOwner().howMuchMoney());
		}
		// GUI Message
		GUI.addText(message);
	}

	private void close(Player currentPlayer){
		
		String message;
		
		for(int i = 0; i < squareArray.length; i++)
			if(squareArray[i].getOwner() == currentPlayer){	
				((PropertySquare)squareArray[i]).abandonSquare();
				GUI.demolishProperty(i);
				// GUI Message
				message = "� ������� " + currentPlayer.getName()+" ����� ��� ����������: "+squareArray[i].getFieldName();
				GUI.addText(message);
			}
		GUI.killPlayer(currentPlayer.getID());
		currentPlayer.deactivatePlayer();
		numberOfPlayersLeft = numberOfPlayersLeft - 1; 
		
		// GUI Message
		message = numberOfPlayersLeft+" ������� ����� ������ ��� ��������";
		GUI.warnPlayer(message); GUI.addText(message);
		
		// GUI Message
		message = "� ������� "+currentPlayer.getName()+" ����� �����";
		GUI.warnPlayer(message); GUI.addText(message);
	}

	public static void main(String args[]){
		new GameSystem();		
	}
}
