// 03.12.2019
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


		players.get(turnNumber).getPiece().move(board.getCells());

		System.out.println(players.get(turnNumber).getName() + "'s " +players.get(turnNumber).getPiece().getShape() + " is being moved right now ...");
		players.get(turnNumber).setCellFinal(board.getCells()[players.get(turnNumber).getCellLocation()]);

		System.out.println("After moving, " + players.get(turnNumber).getName() + " is currently on Cell " + players.get(turnNumber).getCellFinal().getCellId()+", "+board.getCells()[players.get(turnNumber).getCellLocation()].getName());

		if (board.getCells()[players.get(turnNumber).getCellLocation()].getName() == "Chance Card Cell"){
			int randomValue = (int)(Math.random() * 20);
			if(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 0) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfMoveCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(),board.getDice(), players.get(turnNumber));
				players.get(turnNumber).getPiece().move(board.getCells());
			}
			else if (board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 1){
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfMoveWithoutDiceCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), -3);
				players.get(turnNumber).getPiece().move(board.getCells());
			}
			else if (board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 2){
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfJailGetawayCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(), players.get(turnNumber));
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 3){
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfDirectlyToJailCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(), players.get(turnNumber));
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 4) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfMoveWithoutDiceCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), 3);
				players.get(turnNumber).getPiece().move(board.getCells());
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getCardID() == 5) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].ActionOfGetMoneyFromBankCard(board.getCells()[players.get(turnNumber).getCellLocation()].getChanceCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), bank);
			}
		}
		else if (board.getCells()[players.get(turnNumber).getCellLocation()].getName() == "Community Card Cell") {
			int randomValue = (int)(Math.random() * 24);
			if (board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getCardID() == 0) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].ActionOfPayAllPlayer25$(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), players);
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getCardID() == 1) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].ActionOfPay15$toBankAccordingToNumberOfPlayers(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getActionOfTheCard(), players.get(turnNumber),bank, players);
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getCardID() == 2) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].ActionOfPay20$ToAllPlayersBecauseGambling(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), players);
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getCardID() == 3) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].ActionOfTake20$FromAllPlayersCard(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getActionOfTheCard(), players.get(turnNumber), players);
			}
			else if(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getCardID() == 4) {
				board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].ActionOfTake50$AccordingToDiceValue(board.getCells()[players.get(turnNumber).getCellLocation()].getCommunityCards()[randomValue].getActionOfTheCard(),bank, players.get(turnNumber), board.getDice());
			}


		}

		if(players.get(turnNumber).getCellLocation() == 9 || players.get(turnNumber).getCellLocation() == 19)
		{
			System.out.println("HAPİSTESİNİZ !!!!!!!!!!!!!!");
			players.get(turnNumber).setInJail(true);
			if (players.get(turnNumber).isDoesHaveJailGetawayCard() == true){
				System.out.println(players.get(turnNumber).getName() + " has Jail Getaway Card!");
				players.get(turnNumber).setInJail(false);
				players.get(turnNumber).setDoesHaveJailGetawayCard(false);
				System.out.println(players.get(turnNumber).getName() + " is no longer in Jail!");
			}
			PassTheDice(current);
		}

		int afterCell = players.get(turnNumber).getCellLocation();
		if(beforeCell - players.get(turnNumber).getCellFinal().getCellId() > 0){
			board.getCells()[0].MoneyFunc(players.get(turnNumber), bank); // will give money to player
		}

		if (players.get(turnNumber).getCellFinal().getCellId() != 0){
			players.get(turnNumber).getCellFinal().MoneyFunc(players.get(turnNumber), bank); // will take money from player
		}

		/* Burada oyuncu para verme hücresine gelirse eğer, para verdiğinde kalan parasının pozitif olup olmadığını kontrol edeceğiz, negatif çıkarsa oyuncuyu
		Player ArrayList'inden çıkartacağız, konsola da "xx oyuncu parası bittiği için oyundan çıktı" yazdıracağız. */

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
