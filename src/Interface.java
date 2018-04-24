public interface Interface
{
	void setStatus(int player,String name, int newMoney, boolean isPrisoned,boolean hasCard,boolean alive);
	
	void addText(String s);
	
	boolean askPlayer(String message);
	/* ��������� ��� JOptionPane - showConfirmDialog,
	 * �� ����� ���� �� ������ �� message.
	 * � ������� showConfirmDialog ������� �� �� �� ���������� (int)
	 * ��������� ��� �� �� ���������� ���� ��� askPlayer (true/false ����)
	 */
	
	void warnPlayer(String message);
	/* ��������� ��� JOptionPane - showMessageDialog,
	 * �� ����� ���� �� ������ �� message
	 */
	
	int getNumberOfPlayers();
	/* ��������� ��� JOptionPane - showInputDialog
	 * �� ����� ������ ��� ��� ������ ��� ������ �������.
	 * ������ � showInputDialog ������ �� ����� String,
	 * ������� parse �� int, ��� ��� int ����� ���������� � ���������
	 */
	
	void setOwner(int playerNumber, int position);
	/* ���������� � ����� ��������� ���� ��� �� ������
	 * ��� � ���������� ���� ������ position ����� ���
	 * ������ �� ������ playerNumber
	 */
	
	void buildHouse(int playerNumber, int position);
	/* ��������� ��� ����� � ��������� ���� ��� �� ������ 
	 *  ��� � ���������� ���� ������ position ���� ��� �������� �����.
	 *  ��� ��� �� ����� ���������� �� ����� ��� ���������� ���
	 *  � ���������� playerNumber
	 */
	
	void movePlayer(int playerNumber, int position);
	/* ��������� ��� ������ - �� ����� ��� ���� ���� position
	 * 
	 */
	
	void demolishProperty(int position);
	/* ���������� ������ ��� ����������
	 * ��� ���������� �� ������ position
	 */
	
	void killPlayer(int playerNumber);
	/*
	 * ������� ��� �� ������ �� ����� ��� ������,
	 * �� ������� ������� ������
	 */
}
