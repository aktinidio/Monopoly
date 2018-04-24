public interface Interface
{
	void setStatus(int player,String name, int newMoney, boolean isPrisoned,boolean hasCard,boolean alive);
	
	void addText(String s);
	
	boolean askPlayer(String message);
	/* Εμφανίζει ένα JOptionPane - showConfirmDialog,
	 * το οποίο έχει ως μήνυμα το message.
	 * Η μέθοδος showConfirmDialog ανάλογα με το τι επιστρέφει (int)
	 * καθορίζει και τι θα επιστραφεί μέσω της askPlayer (true/false τιμή)
	 */
	
	void warnPlayer(String message);
	/* Εμφανίζει ένα JOptionPane - showMessageDialog,
	 * το οποίο έχει ως μήνυμα το message
	 */
	
	int getNumberOfPlayers();
	/* Εμφανίζει ένα JOptionPane - showInputDialog
	 * το οποίο ζητάει από τον χρήστη τον αριθμό παικτών.
	 * Επειδή η showInputDialog μάλλον θα δώσει String,
	 * γίνεται parse σε int, και τον int αυτόν επιστρέφει η συνάρτηση
	 */
	
	void setOwner(int playerNumber, int position);
	/* Χρωματίζει ή κάνει οτιδήποτε άλλο για να δείξει
	 * ότι η ιδιοκτησία στον αριθμό position είναι του
	 * παίκτη με αριθμό playerNumber
	 */
	
	void buildHouse(int playerNumber, int position);
	/* Τοποθετεί ένα σπίτι ή οτιδήποτε άλλο για να δείξει 
	 *  ότι η ιδιοκτησία στον αριθμό position έχει ένα επιπλέον σπίτι.
	 *  Εάν δεν θα είναι χρωματιστό το σπίτι δεν χρειάζεται καν
	 *  η παράμετρος playerNumber
	 */
	
	void movePlayer(int playerNumber, int position);
	/* Μετακινεί τον παίκτη - το πιόνι του στην θέση position
	 * 
	 */
	
	void demolishProperty(int position);
	/* Εξαφανίζει σπίτια και ιδιοκτησία
	 * του τετραγώνου με αριθμό position
	 */
	
	void killPlayer(int playerNumber);
	/*
	 * Αφαιρεί από το ταμπλό το πιόνι του παίκτη,
	 * το γραφικό κομμάτι δηλαδή
	 */
}
