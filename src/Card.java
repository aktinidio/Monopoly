public class Card
{
	private String message;
	private int amount;
	private int position;
	
	public Card(String message, int amount, int position)
	{
		this.message = message;
		this.amount = amount;
		this.position = position;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void useCard(Player currentPlayer)
	{		
		if(message.equals("Βγες από την φυλακή!"))
		{
			currentPlayer.changeJailCardStatus(true);
		}
		else if(message.equals("Δεν κάλυψες καλά τα ίχνη σου, πήγαινε αμέσως στην φυλακή"))
		{
			currentPlayer.setJailStatus(true);
			currentPlayer.setPosition(8);
		}
		else if(amount == 0)
		{
			currentPlayer.setPosition(position);
		}
		else if(position == 0)
		{
			currentPlayer.addMoney(amount);
		}
	}
	
}
