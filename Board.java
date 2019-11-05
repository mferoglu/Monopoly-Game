import java.util.ArrayList;

public class Board extends Game {
	private Dice dice;
	private int noOfPlayers;
	private Piece[] pieces = new Piece[8];
	private Cell[] cells = new Cell[40];
	private String[] pieceTypes = { "Car", "Hat", "Racket", " Cat", "Shoe", "Ship", "Pac-Man", "Trumpet" };

	Board() {
		System.out.println("hey123");
		assignAllPieces(super.getPlayers());


	}

	public void fillTheBoard() {


		System.out.println();
		for (int i = 0; i < noOfPlayers; i++) {
			pieces[i] = new Piece(pieceTypes[i]);
		}

		int interval = 40 / super.getHowManyTaxedCells();
		this.noOfPlayers = (super.getPlayers()).size();
		cells[0] = new Cell(0);
		for (int i = 1; i < (40 - 40 % (super.getHowManyTaxedCells())); i++) {
			if ((i - 1) % (interval) == 0) {
				cells[i] = new PayMoneyCell(i, super.getTaxedMoneyAmount());
			} else {
				cells[i] = new Cell(i);
			}

		}

	}

	public void assignAllPieces(ArrayList<Player> players) {
	/*	for(int i = 0; i < 8 ; i++)
		{
			pieces[i] = new Piece(pieceTypes[i]);
		}*/

		for (int i = 0; i < noOfPlayers; i++) {
			System.out.println("noOfPlayers " + i );

			pieces[i].setOwner(((super.getPlayers()).get(i)));
		}

	}
	public void createDice(){
		Dice dice = new Dice();
		this.dice = dice;
	}

	public void removePiece(Piece pieceToRemove) {
		pieceToRemove.setOwner(null);
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public Piece[] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public String[] getPieceTypes() {
		return pieceTypes;
	}

	public void setPieceTypes(String[] pieceTypes) {
		this.pieceTypes = pieceTypes;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

}
