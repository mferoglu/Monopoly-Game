
public class Piece extends Board implements Moveable {

	private String shape = new String();
	private Player owner;
	private Cell cellId;


	Piece(String shape){
		super();
		this.shape = shape;
	}
	public void setOwner(Player owner){
		this.owner = owner;
	}

	public String getShape(){
		return shape;
	}

	public void move(int noOfCellToMove){

		int initialPosition = owner.getCellLocation();
		cellId = super.getBoard().getCells()[initialPosition];
	}


}
