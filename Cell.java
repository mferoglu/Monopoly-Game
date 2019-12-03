// 07.11.2019
public abstract class Cell {

	private int cellId;
	private Player owner;
	private boolean canBeBought = true;

	Cell(int id, Player owner) {
		this.cellId = id;
		this.owner = owner;

	}

	public abstract void MoneyFunc(Player player,Bank bank);

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}
	
	
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isCanBeBought() {
		return canBeBought;
	}

	public void setCanBeBought(boolean canBeBought) {
		this.canBeBought = canBeBought;
	}

	public abstract String getName();

}
