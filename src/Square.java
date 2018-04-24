public class Square
{
	private String fieldName;
	private Player ownedBy;
	
	public Square(String name)
	{
		fieldName = name;
		ownedBy = null;
	}

	public void setOwner(Player owner)
	{
		ownedBy = owner;
	}
	
	public String getFieldName()
	{
		return fieldName;
	}
	
	public Player getOwner()
	{
		return ownedBy;
	}
}
