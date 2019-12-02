// 07.11.2019
import java.util.ArrayList;

public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private Board board;
	private int initialMoney;
	private int howManyTaxedCells;
	private int taxedMoneyAmount;
	private int turnNumber = 0;
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
	
	
	public void onTurn(Player current){
		turnNumber = current.getTurnNumber();
		int beforeCell = players.get(turnNumber).getCellLocation();
		if(players.get(turnNumber).isInJail())
		{
			int diceInJail = board.getDice().rollDices();
			if(diceInJail % 2 == 0 )
			{
				System.out.println(players.get(turnNumber).getName() +" is free because the player rolled even number.");
				players.get(turnNumber).setWaitedTurnsInJail(2);

			}
			players.get(turnNumber).setWaitedTurnsInJail(players.get(turnNumber).getWaitedTurnsInJail()+1);;
			if(players.get(turnNumber).getWaitedTurnsInJail() == 3)
			{
				players.get(turnNumber).setWaitedTurnsInJail(0);
				players.get(turnNumber).setInJail(false);
			}


			PassTheDice(current);

		}

		System.out.println(players.get(turnNumber).getName() +" is rolling dice right now...");
		players.get(turnNumber).setCellLocation(board.getDice().rollDices());


		players.get(turnNumber).getPiece().move(board.getCells(), players.get(turnNumber).getCellLocation());

		System.out.println(players.get(turnNumber).getName() + "'s " +players.get(turnNumber).getPiece().getShape() + " is being moved right now ...");
		players.get(turnNumber).setCellFinal(board.getCells()[players.get(turnNumber).getCellLocation()]); 

		System.out.println("After moving, " + players.get(turnNumber).getName() + " is currently on Cell " + players.get(turnNumber).getCellFinal().getCellId()+", "+board.getCells()[players.get(turnNumber).getCellLocation()].getName());

		if(players.get(turnNumber).getCellLocation() == 9 || players.get(turnNumber).getCellLocation() == 19)
		{
			System.out.println("HAPÝSTESÝNÝZ !!!!!!!!!!!!!!");
			players.get(turnNumber).setInJail(true);
			PassTheDice(current);
		}

		int afterCell = players.get(turnNumber).getCellLocation();
		if(beforeCell - players.get(turnNumber).getCellFinal().getCellId() > 0){
			board.getCells()[0].MoneyFunc(players.get(turnNumber), bank); // will give money to player
		}

		if (players.get(turnNumber).getCellFinal().getCellId() != 0){
			players.get(turnNumber).getCellFinal().MoneyFunc(players.get(turnNumber), bank); // will take money from player
		}

		/* Burada oyuncu para verme hücresine gelirse eðer, para verdiðinde kalan parasýnýn pozitif olup olmadýðýný kontrol edeceðiz, negatif çýkarsa oyuncuyu
		Player ArrayList'inden çýkartacaðýz, konsola da "xx oyuncu parasý bittiði için oyundan çýktý" yazdýracaðýz. */

		System.out.println(players.get(turnNumber).getName() + " has money amount of "+players.get(turnNumber).getAmountOfMoney() +" $");

	//	System.out.println(players.get(turnNumber).name+" has given the dices to "+players.get(nextTurn).name);
	//	System.out.println();
		PassTheDice(current);




	}
	public void PassTheDice(Player current){


		int firstsize = players.size();
		int secondsize = players.size();
		if(current.isBankrupt(current))
		{

			if(current.getTurnNumber() == players.size()-1)
			{
				players.get(current.getTurnNumber()-1).setNextTurn(0);
			}
			System.out.println("**********  "+players.get(current.getTurnNumber()).getName()+" has bankrupted and left the game ..."+"  **********");
			System.out.println("Dices are given automatically to "+players.get(current.getNextTurn()).getName());
			players.remove(current.getTurnNumber());
			secondsize = players.size();
			if(current.getNextTurn() == players.size())
			{
				current.setNextTurn(current.getNextTurn()-1);
			}
			for(int i=current.getTurnNumber() ; i<players.size();i++){
				players.get(i).setTurnNumber(players.get(i).getTurnNumber()-1);

				if(i == players.size()-1)
				{
					players.get(i).setNextTurn(0);
					break;
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
			System.out.println(players.get(current.getTurnNumber()).getName()+" has given the dices to "+players.get(current.getNextTurn()).getName());
		}
		System.out.println();
		onTurn((players.get(current.getNextTurn())));
	}


	public int getTaxedMoneyAmount() {
		return taxedMoneyAmount;
	}


	public void setTaxedMoneyAmount(int taxedMoneyAmount) {
		this.taxedMoneyAmount = taxedMoneyAmount;
	}


	public void Play(){
		onTurn(players.get(0));
		



	}



}
