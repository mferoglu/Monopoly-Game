// 07.11.2019
import java.util.ArrayList;

public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private Board board;
	private int initialMoney;
	private int howManyTaxedCells;
	private int taxedMoneyAmount;
	private Bank bank ;

	Game(int initialMoney, int howManyTaxedCells, String[] playerNames, int taxedMoneyAmount) {
		this.initialMoney = initialMoney;
		this.taxedMoneyAmount = taxedMoneyAmount;
		this.howManyTaxedCells = howManyTaxedCells;
		bank = new Bank();

		for(int i = 0; i < playerNames.length; i++){

			players.add(new Player(playerNames[i],20,initialMoney,i));
			if(i == playerNames.length-1){players.get(i).setNextTurn(0);continue;}
			players.get(i).setNextTurn(i+1);
		}
		//board.assignAllPieces(players);
		board = new Board(howManyTaxedCells,playerNames.length, taxedMoneyAmount, players);
		for(int i  = 0; i < playerNames.length; i++){
			players.get(i).setPiece(board.getPieces()[i]);
		}

		System.out.println("Players:");
		for (int i = 0; i < players.size(); i++) {
			System.out.println( (i+1) +". " +  players.get(i).getName());
		}
		System.out.println();

	}


	public int getTaxedMoneyAmount() {
		return taxedMoneyAmount;
	}


	public void setTaxedMoneyAmount(int taxedMoneyAmount) {
		this.taxedMoneyAmount = taxedMoneyAmount;
	}


	public void Play(){
		players.get(0).onTurn(board.getDice(),board.getCells(),players,bank);



	}



}
