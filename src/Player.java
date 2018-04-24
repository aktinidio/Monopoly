public class Player
{
	private int id;
	private String name;
	private int money;
	private int position;
	private boolean inGame;
	private boolean inJail;
	private boolean jailCard;
	
	public Player(String name, int id, int money)
	{
		this.name = name;
		this.id = id;
		this.money = money;
		position = 0;
		inGame = true;
		inJail = false;
		jailCard = false;
	}
	
	// Name Methods
	
	public String getName()
	{
		return name;
	}
	
	// Money Methods
	
	public void addMoney(int amount)
	{
		money = money + amount;
	}
	
	public void takeMoney(int amount)
	{
		money = money - amount;
	}
	
	public int howMuchMoney()
	{
		return money;
	}
	
	// Position Methods
	
	public int movePosition(int pos)
	{
		return position = (position + pos) % 32;
	}
	
	public int setPosition(int pos)
	{
		return position = pos;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	// Jail Methods
	
	public void setJailStatus(boolean input)
	{
		inJail = input;
	}
	
	public boolean isInJail()
	{
		return inJail;
	}

	// Jail Card Methods
	
	public void changeJailCardStatus(boolean input)
	{
		jailCard = input;
	}
	
	public boolean hasJailCard()
	{
		return jailCard;
	}
	
	// Player Status Methods
	
	public void deactivatePlayer()
	{
		inGame = false;
	}
	
	public boolean isInGame()
	{
		return inGame;
	}
	
	// Player id Methods
	
	public int getID()
	{
		return id;
	}
}
