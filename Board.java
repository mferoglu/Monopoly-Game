// 07.11.2019
import java.util.ArrayList;

public class Board {
	private Dice dice = new Dice();
	private int noOfPlayers;
	private Piece[] pieces = new Piece[8];
	private Cell[] cells = new Cell[40];
	private String[] pieceTypes = { "Car", "Hat", "Racket", " Cat", "Shoe", "Ship", "Pac-Man", "Trumpet" };

	Board(int noOfPayingCells, int noOfPlayers,int amountOfMoneyToBeTaken, ArrayList<Player> players) {
		for (int i = 0; i < noOfPlayers; i++){
		pieces[i] = new Piece(pieceTypes[i]);
		}
		double interval1 = 40.0 / (noOfPayingCells+1);
		this.noOfPlayers = noOfPlayers;
		cells[0] = new StartingCell();

		double interval2 = interval1;
		for(int i = 1; i<noOfPayingCells;i++)
		{
			cells[(int)(Math.round(interval1))] = new PayMoneyCell((int)(Math.round(interval1)),null,amountOfMoneyToBeTaken);
			interval1 += interval2;
		}

		cells[9] = new JailCell(9);
		cells[19] = new JailCell(19);
		cells[21] = new ElectricCell(21 ,amountOfMoneyToBeTaken);
		cells[31] = new ElectricCell(31 ,amountOfMoneyToBeTaken);
		cells[27] = new WaterCell(27,amountOfMoneyToBeTaken);
		cells[37] = new WaterCell(37,amountOfMoneyToBeTaken);


		for(int i = 0; i< 40 ; i++)
		{
			if(cells[i].isCanBeBought() == false)
			{
				continue;
			}
			if(cells[i] == null)
			{
				if(i==39)
				{
					cells[39]=new PayMoneyCell(39,null,amountOfMoneyToBeTaken);
					continue;
				}
				cells[i] = new RegularCell(i,null);
			}
		}

		assignAllPieces(players);


	}

	public void assignAllPieces(ArrayList<Player> players) {

		for (int i = 0; i < noOfPlayers; i++) {
			pieces[i].setOwner((players.get(i)));
		}

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
