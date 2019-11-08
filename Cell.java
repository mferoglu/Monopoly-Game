// 07.11.2019
public abstract class Cell {

	private int cellId;
	private Player owner;

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
	public abstract String getName();

}
