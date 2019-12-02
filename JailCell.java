
public class JailCell extends Cell {

	JailCell(int id){
		super(id,null);
		super.setCanBeBought(false);

	}
	public void MoneyFunc(Player player,Bank bank){
		System.out.println("Nothing will happen with money in this cell !");
		// Will be implemented in subclass of Cell -> "PayMoneyCell"
	}
	public String getName() {
		return null;
	}

}
