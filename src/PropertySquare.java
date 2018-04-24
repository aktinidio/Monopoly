public class PropertySquare extends Square
{
	private int landPrice;
	private int housePrice;
	private int landRent;
	private int houseRent;
	private int wholeRent;
	private int houses;
	
	public PropertySquare(String name, int landPrice, int housePrice, int landRent, int houseRent)
	{
		super(name);
		this.landPrice = landPrice;
		this.housePrice = housePrice;
		this.landRent = landRent;
		this.houseRent = houseRent;
		this.wholeRent = landRent;
		houses = 0;
	}
	
	public boolean sellTo(Player currentPlayer)
	{
		if(currentPlayer.howMuchMoney() >= landPrice){
			super.setOwner(currentPlayer);
			currentPlayer.takeMoney(landPrice);
			return true;
		}
		else return false;
	}
	
	public boolean buildHouse()	{
		if((super.getOwner().howMuchMoney() >= housePrice) && houses < 3){
			houses++;
			this.reCalculateWholeRent();
			super.getOwner().takeMoney(housePrice);
			return true;
		}
		else return false;
	}
	
	public void payRentBy(Player currentPlayer){
		super.getOwner().addMoney(wholeRent);
		currentPlayer.takeMoney(wholeRent);
	}
	
	public void abandonSquare()	{
		super.setOwner(null);
		houses = 0;
		reCalculateWholeRent();
	}
	
	public void reCalculateWholeRent(){
		wholeRent = (houseRent * houses) + landRent; 
	}
	
	public int getHouses(){
		return houses;
	}
}
