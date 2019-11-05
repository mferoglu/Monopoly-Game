import java.util.ArrayList;

public class Player extends Game implements Moveable{
	private String name = new String();
	private int turnNumber;
	private int nextTurn;
	private int amountOfMoney;
	private Piece piece;
	private int cycleNumber;
	private int cellLocation;
	private Cell cellFinal;
	private boolean isBankrupt;
	private boolean isInJail;
	private ArrayList<Cell> ownedCells;// = new ArrayList<Cell>();
	private int waitedTurnsInJail;
	private int possibilityOfTakingRisk;

	Player(String name, int possibilityOfTakingRisk, int initialMoney, int turnNumber) {
		setName(name);
		setPossibilityOfTakingRisk(possibilityOfTakingRisk);
		setAmountOfMoney(initialMoney);
		setTurnNumber(turnNumber);
		this.cellLocation = 0;


	}

	public void onTurn(){
		System.out.println("ss" + super.getBoard());
		move(super.getBoard().getDice().rollDices());
		System.out.println("Player "+turnNumber+" is rolling dice right now...");
		System.out.println();
		//piece.setOwner(players.get(turnNumber));
		piece.move(this.cellLocation);
		System.out.println("Piece is being moved right now ...");
		cellFinal = super.getBoard().getCells()[this.cellLocation];System.out.println("buraya bakacağız"+this.cellLocation);
		System.out.println("After cell final: "+ cellFinal.getCellId());
		cellFinal.MoneyFunc(super.getPlayers().get(turnNumber));
		System.out.println("Player "+turnNumber+" has given the dices to Player "+nextTurn);
		System.out.println("Player  " +(turnNumber+1) + " has money amount of"+super.getPlayers().get(turnNumber).getAmountOfMoney());
		PassTheDice(super.getBoard().getDice(),super.getPlayers(),nextTurn,super.getBoard().getCells(),super.getBank());


	}
	public void PassTheDice(Dice dice , ArrayList<Player> players,int nextTurn,Cell[] cells,Bank bank){
		System.out.println("Player nextturn name "+players.get(nextTurn).getName());

		(players.get(nextTurn)).onTurn();
	}

	public void move(int numberOfCellToMove){
		setCellLocation(numberOfCellToMove);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;

	}

	public int getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getCycleNumber() {
		return cycleNumber;
	}

	public void setCycleNumber(int cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	public int getCellLocation() {

		return cellLocation;
	}

	public void setCellLocation(int cellLocation) {

		this.cellLocation += cellLocation;
		if(this.cellLocation>39)
		{
			this.cellLocation %= 40;
		}

		System.out.println("Cell location : "+cellLocation);

	}

	public boolean isBankrupt() {
		return isBankrupt;
	}

	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	public boolean isInJail() {
		return isInJail;
	}

	public void setInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}

	public ArrayList<Cell> getOwnedCells() {
		return ownedCells;
	}

	public void setOwnedCells(ArrayList<Cell> ownedCells) {
		this.ownedCells = ownedCells;
	}

	public int getWaitedTurnsInJail() {
		return waitedTurnsInJail;
	}

	public void setWaitedTurnsInJail(int waitedTurnsInJail) {
		this.waitedTurnsInJail = waitedTurnsInJail;
	}

	public int getPossibilityOfTakingRisk() {
		return possibilityOfTakingRisk;
	}

	public void setPossibilityOfTakingRisk(int possiblyOfTakingRisk) {
		this.possibilityOfTakingRisk = possiblyOfTakingRisk;
	}

	public Cell getCellFinal() {
		return cellFinal;
	}

	public void setCellFinal(Cell cellFinal) {
		if(this.cellLocation >= 39){
			this.cellLocation %= 40;
		}

		this.cellFinal = cellFinal;
	}

	public int getNextTurn() {
		return nextTurn;
	}

	public void setNextTurn(int nextTurn) {
		this.nextTurn = nextTurn;
	}


}
