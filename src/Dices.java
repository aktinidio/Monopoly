import java.util.Random;

public class Dices
{	
	private int firstDiceValue;
	private int secondDiceValue;
	
	public int throwDices()
	{
		firstDiceValue = new Random().nextInt(6) + 1;
		secondDiceValue = new Random().nextInt(6) + 1;
		
		return firstDiceValue + secondDiceValue;
	}
	
	public boolean areDouble()
	{
		return firstDiceValue == secondDiceValue;
	}
	
	public int getFirstDice()
	{
		return firstDiceValue;
	}
	
	public int getSecondDice()
	{
		return secondDiceValue;
	}
}
