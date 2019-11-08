// 07.11.2019
public class RegularCell extends Cell {
	private String name = "Regular cell";

	RegularCell(int id, Player owner){
		super(id,owner);
	}
	public void MoneyFunc(Player player,Bank bank){
		System.out.println("Nothing will happen in this cell !");
		// Will be implemented in subclass of Cell -> "PayMoneyCell"
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
