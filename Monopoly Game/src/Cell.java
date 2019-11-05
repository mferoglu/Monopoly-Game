// Cell classý bir sonraki iterasyonda abstract olarak deðiþtirilecektir.

public class Cell extends Board {

	private int cellId;
	private Player owner;

	Cell(int id) {
		System.out.println("cell");
		this.cellId = id;
	}

	public void MoneyFunc(Player player){}

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

}
