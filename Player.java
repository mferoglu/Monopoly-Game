// 01.12.2019
import java.util.ArrayList;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

public class Player {
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
	private int waitedTurnsInJail = 0;
	private int possibilityOfTakingRisk;


	Player(String name, int possibilityOfTakingRisk, int initialMoney, int turnNumber) {
		setName(name);
		setPossibilityOfTakingRisk(possibilityOfTakingRisk);
		setAmountOfMoney(initialMoney);
		setTurnNumber(turnNumber);
		this.cellLocation = 0;


	}

	public void onTurn(Dice dice,Cell[] cells,ArrayList<Player> players,Bank bank){
		int beforeCell = players.get(turnNumber).getCellLocation();
		if(players.get(turnNumber).isInJail)
		{
			int diceInJail = dice.rollDices();
			if(diceInJail % 2 == 0 )
			{
				System.out.println(players.get(turnNumber).name +" is free because the player rolled even number.");
				this.waitedTurnsInJail = 2;

			}
			this.waitedTurnsInJail += 1;
			if(this.waitedTurnsInJail == 3)
			{
				this.waitedTurnsInJail = 0;
				this.isInJail = false;
			}


			PassTheDice(dice ,players,nextTurn,cells,bank);

		}

		System.out.println(players.get(turnNumber).name +" is rolling dice right now...");
		setCellLocation(dice.rollDices());


		piece.move(cells, this.cellLocation);

		System.out.println(players.get(turnNumber).name + "'s " +players.get(turnNumber).getPiece().getShape() + " is being moved right now ...");
		cellFinal = cells[this.cellLocation];

		System.out.println("After moving, " + players.get(turnNumber).name + " is currently on Cell " + cellFinal.getCellId()+", "+cells[players.get(turnNumber).getCellLocation()].getName());

		if(players.get(turnNumber).getCellLocation() == 9 || players.get(turnNumber).getCellLocation() == 19)
		{
			System.out.println("HAPİSTESİNİZ !!!!!!!!!!!!!!");
			this.isInJail = true;
			PassTheDice(dice ,players,nextTurn,cells,bank);
		}

		int afterCell = players.get(turnNumber).getCellLocation();
		if(beforeCell - cellFinal.getCellId() > 0){
			cells[0].MoneyFunc(players.get(turnNumber), bank); // will give money to player
		}

		if (cellFinal.getCellId() != 0){
			cellFinal.MoneyFunc(players.get(turnNumber), bank); // will take money from player
		}

		/* Burada oyuncu para verme hücresine gelirse eğer, para verdiğinde kalan parasının pozitif olup olmadığını kontrol edeceğiz, negatif çıkarsa oyuncuyu
		Player ArrayList'inden çıkartacağız, konsola da "xx oyuncu parası bittiği için oyundan çıktı" yazdıracağız. */

		System.out.println(players.get(turnNumber).name + " has money amount of "+players.get(turnNumber).getAmountOfMoney() +" $");

	//	System.out.println(players.get(turnNumber).name+" has given the dices to "+players.get(nextTurn).name);
	//	System.out.println();
		PassTheDice(dice,players,nextTurn,cells,bank);




	}
	public void PassTheDice(Dice dice , ArrayList<Player> players,int nextTurn,Cell[] cells,Bank bank){

		int firstsize = players.size();
		int secondsize = players.size();
		if((isBankrupt(players.get(turnNumber))) == true)
		{

			if(turnNumber == players.size()-1)
			{
				players.get(turnNumber-1).setNextTurn(0);
			}
			System.out.println("**********  "+players.get(turnNumber).getName()+" has bankrupted and left the game ..."+"  **********");
			System.out.println("Dices are given automatically to "+players.get(nextTurn).name);
			players.remove(turnNumber);
			secondsize = players.size();
			if(nextTurn == players.size())
			{
				nextTurn--;
			}
			for(int i=turnNumber ; i<players.size();i++){
				players.get(i).setTurnNumber(players.get(i).getTurnNumber()-1);



				if(i == players.size()-1)
				{
					players.get(i).setNextTurn(0);
					break;
				}

				if(i == players.size()-1)
				{
					continue;
				}

				players.get(i).setNextTurn(players.get(i).getNextTurn()-1);





			}

		}
		if(players.size()== 1)
		{	System.out.println();
			System.out.println(players.get(0).getName()+" has won the game !");
			System.out.println("Game is over !");System.exit(1);;
		}
		if(firstsize == secondsize)
		{
			System.out.println(players.get(turnNumber).name+" has given the dices to "+players.get(nextTurn).name);
		}
		System.out.println();
		(players.get(nextTurn)).onTurn(dice, cells, players, bank);

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
		if(this.cellLocation>39){this.cellLocation %= 40;}
		//System.out.println("Sum of face values : "+cellLocation);

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

	public boolean isBankrupt(Player player) {

		if (player.getAmountOfMoney() < 0) {
			this.isBankrupt = true;
			return true;
		}

		else {
			this.isBankrupt = false;
			return false;
		}

	}


}
