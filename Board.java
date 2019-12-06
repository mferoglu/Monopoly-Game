// 04.12.2019
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Board {
	private Dice dice = new Dice();
	private int noOfPlayers;
	private Piece[] pieces = new Piece[8];
	private Cell[] cells = new Cell[40];
	private CommunityCard[] communitycards = new CommunityCard[20];
	private ChanceCard[] chancecards = new ChanceCard[24];

	private String[] pieceTypes = { "Car", "Hat", "Racket", " Cat", "Shoe", "Ship", "Pac-Man", "Trumpet" };

	Board(int noOfPayingCells, int noOfPlayers,int amountOfMoneyToBeTaken, ArrayList<Player> players) {


		for (int i = 0; i < noOfPlayers; i++){
		pieces[i] = new Piece(pieceTypes[i]);
		}
		double interval1 = 40.0 / (noOfPayingCells+1);
		this.noOfPlayers = noOfPlayers;


		double interval2 = interval1;
		for(int i = 1; i<noOfPayingCells;i++)
		{
			cells[(int)(Math.round(interval1))] = new PayMoneyCell((int)(Math.round(interval1)),null,amountOfMoneyToBeTaken);
			interval1 += interval2;
		}
		


		setCommunityCardArray();
		setChanceCardArray();
		List<Card> tobeshuffled = Arrays.asList(communitycards);
		Collections.shuffle(tobeshuffled);
		tobeshuffled.toArray(communitycards);
		tobeshuffled = Arrays.asList(chancecards);
		Collections.shuffle(tobeshuffled);
		tobeshuffled.toArray(chancecards);

		cells[0] = new StartingCell();
		cells[1] = new PropertyCell(1,null,"Brown",50,25,"Silivri");
		cells[2] = new PropertyCell(2,null,"Brown",50,25,"Sile");
		cells[3] = new StationCell(3,null,100,"Yenikapi Gari",50);
		cells[4] = new PropertyCell(4,null,"Brown",50,25,"Tuzla");
		cells[5] = new PropertyCell(5,null,"Turquoise",100,50,"Adalar");
		cells[6] = new PropertyCell(6,null,"Turquoise",100,50,"Bagcilar");
		cells[7] = new JailCell(7);
		cells[8] = new PropertyCell(8,null,"Turquoise",100,50,"Bakirkoy");
		cells[9] = new PropertyCell(9,null,"Magenta",150,75,"Pendik");
		cells[10] = new CommunityCardCell(10,communitycards);
		cells[11] = new PropertyCell(11,null,"Magenta",150,75,"Esenler");
		cells[12] = new PropertyCell(12,null,"Magenta",150,75,"Bayrampasa");
		cells[13] = new PropertyCell(13,null,"Orange",200,100,"Beylikduzu");
		cells[14] = new ChanceCardCell(14,chancecards);
		cells[15] = new StationCell(15,null,100,"Haydarpasa Gari",50);
		cells[16] = new PropertyCell(16,null,"Orange",200,100,"Cekmekoy");
		cells[17] = new PropertyCell(17,null,"Orange",200,100,"Eyup");
		cells[18] = new PropertyCell(18,null,"Red",250,125,"Gungoren");
		cells[19] = new JailCell(19);
		cells[20] = new RegularCell(20,null);
		cells[21] = new ElectricCell(21 ,amountOfMoneyToBeTaken);
		cells[22] = new PropertyCell(22,null,"Red",250,125,"Basaksehir");
		cells[23] = new PropertyCell(23,null,"Red",250,125,"Fatih");
		cells[24] = new StationCell(24,null,100,"Halkali Gari",50);
		cells[25] = new PropertyCell(25,null,"Yellow",300,150,"Maltepe");
		cells[26] = new PropertyCell(26,null,"Yellow",300,150,"Bagdat");
		cells[27] = new WaterCell(27,amountOfMoneyToBeTaken);
		cells[28] = new PropertyCell(28,null,"Yellow",300,150,"Ortakoy");
		cells[29] = new CommunityCardCell(29,communitycards);
		cells[30] = new PropertyCell(30,null,"Green",350,175,"Atasehir");
		cells[31] = new ElectricCell(31 ,amountOfMoneyToBeTaken);
		cells[32] = new ChanceCardCell(32,chancecards);
		cells[33] = new PropertyCell(33,null,"Green",350,175,"Beykoz");
		cells[34] = new StationCell(34,null,100,"Sirkeci Gari",50);
		cells[35] = new PropertyCell(35,null,"Green",350,175,"Sariyer");
		cells[36] = new PropertyCell(36,null,"Blue",400,200,"Bebek");
		cells[37] = new WaterCell(37,amountOfMoneyToBeTaken);
		cells[38] = new PropertyCell(38,null,"Blue",400,200,"Taksim");
		cells[39] = new PropertyCell(39,null,"Blue",400,200,"Besiktas");
















		int j = 0;
		for(int i = 0; i< 40 ; i++)
		{

			if(cells[i] == null)
			{



			}
			/*	if(i==39)
				{
					cells[39]=new PayMoneyCell(39,null,amountOfMoneyToBeTaken);
					continue;
				}
				cells[i] = new RegularCell(i,null);*/
			}


		assignAllPieces(players);


	}
	private void setCommunityCardArray(){
		for(int i = 0; i<communitycards.length / 5;i++) {
			communitycards[i] = new CommunityCard("Community Card", "You lost the bet! Pay 25$ to each player!",0 );
		}
		for (int i = 4; i < 4 + (communitycards.length / 5); i++) {
			communitycards[i] = new CommunityCard("Community Card","Government Regulations! Pay 15$ tax per player to bank!",1);
		}
		for (int i = 8; i < 8 + (communitycards.length / 5); i++) {
			communitycards[i] = new CommunityCard("Community Card","Blackjack was not good! Pay 20$ to each player!",2);
		}
		for (int i = 12; i < 12 + (communitycards.length / 5); i++ ){
			communitycards[i] = new CommunityCard("Community Card","BAZINGAAAAA!! Get 20$ from each player!",3);
		}
		for (int i = 16; i < 16 + (communitycards.length / 5); i++ ){
			communitycards[i] = new CommunityCard("Community Card","Jackpot hits! Roll the dice again, if the sum of face values are higher than 6, take 50$ from the bank!",4);
		}
	}
	private void setChanceCardArray(){
		for(int i = 0; i<chancecards.length / 6;i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Let's lose some weight! Roll the dice again and move to that location!",0);
		}
		for(int i = 4; i< (4 + (chancecards.length / 6));i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Luck has never been your friend! Move 3 cells backwards!",1);
		}
		for(int i = 8; i< 8 + chancecards.length / 6;i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Bail has been paid by an Anonymous! You can get out of the jail with this card!",2);
		}
		for(int i = 12 ; i< 12 + chancecards.length / 6;i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Cops! Go directly to jail!",3);
		}
		for(int i = 16 ; i< 16 + chancecards.length / 6;i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Luck is with you my friend! Move 3 cells forwards!",4);
		}
		for(int i = 20 ; i< 20 + chancecards.length / 6;i++) {
			chancecards[i] = new ChanceCard("Chance Card", "Heritage from Egypt! Get 100$ from bank!",5);
		}

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
