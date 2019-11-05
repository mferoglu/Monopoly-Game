import java.util.ArrayList;

public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private Board board;
	private int initialMoney;
	private static int howManyTaxedCells;
	private int taxedMoneyAmount;
	private Bank bank ;
	private Dice dice;



	Game() {
		//System.out.println("taxed cell no :"+howManyTaxedCells);

	}


	public int getTaxedMoneyAmount() {
		return taxedMoneyAmount;
	}


	public void setTaxedMoneyAmount(int taxedMoneyAmount) {
		this.taxedMoneyAmount = taxedMoneyAmount;
	}


	public void Play(){
		bank = new Bank();
		players.get(0).onTurn();

	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getInitialMoney() {
		return initialMoney;
	}
	public void setInitialMoney(int initialMoney) {
		this.initialMoney = initialMoney;
	}
	public static int getHowManyTaxedCells() {
		return howManyTaxedCells;
	}
	public void setHowManyTaxedCells(int howManyTaxedCells) {
		this.howManyTaxedCells = howManyTaxedCells;
		//System.out.println("----" + howManyTaxedCells + "----");
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public void setNames(String playerNames[]){
		for(int i = 0; i < playerNames.length; i++){

			players.add(new Player(playerNames[i],20,initialMoney,i));
			if(i == playerNames.length-1){players.get(i).setNextTurn(0);continue;}
			players.get(i).setNextTurn(i+1);
		}
		System.out.println("setnames'deyim");
		//board = new Board();
		board.fillTheBoard();
		for(int i  = 0; i < playerNames.length; i++){
			players.get(i).setPiece(board.getPieces()[i]);System.out.println(i+"inci");
		}
	}


	public Dice getDice() {
		return dice;
	}

	public void createBoard(){
		Board board = new Board();
		this.board = board;
	}






}
