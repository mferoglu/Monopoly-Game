import java.util.Scanner;

public class Test {

	public static void main(String args[]) {

		FunBegins();

	}

	public static void FunBegins() {

		Scanner sc = new Scanner(System.in);
		Game Monopoly = new Game();
		String playerName = new String();
		System.out.println("Welcome to Monopoly Game!");

		System.out.print("Initial Money: ");
		int initialMoney = sc.nextInt();
		Monopoly.setInitialMoney(initialMoney);

		System.out.print("Tax Cells Number: ");
		int howManyTaxedCells = sc.nextInt();
		Monopoly.setHowManyTaxedCells(howManyTaxedCells);
		//System.out.println("----" + Monopoly.getHowManyTaxedCells() + "----" );

		System.out.print("Please enter the tax amount which will be taken from the players : ");
		int taxedMoneyAmount = sc.nextInt();
		Monopoly.setTaxedMoneyAmount(taxedMoneyAmount);

		System.out.print("How Many People Will Play Monopoly? ");
		int numberOfPlayers = sc.nextInt();

		String nameOfPlayers[] = new String[numberOfPlayers];
		for (int i = 1; i <= numberOfPlayers; i++) {
			System.out.print("Please enter the name of Player " + i + ": ");
			nameOfPlayers[i - 1] = sc.next();
		}
		
		Monopoly.createBoard();
		Monopoly.getBoard().createDice();
		Monopoly.setNames(nameOfPlayers);
		Monopoly.Play();

	}

}
