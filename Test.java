// 07.11.2019
import java.util.Scanner;
import java.io.*;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Test {

	public static void main(String args[]) {
		FunBegins();
	}

	public static void FunBegins() {
		Scanner sc = new Scanner(System.in);


		int numberOfPlayers = 0;
		int taxedMoneyAmount = 0;
		int howManyTaxedCells = 0;
		int initialMoney = 0;

		System.out.println("Welcome to Monopoly Game!");
		System.out.print("Initial Money: ");
		initialMoney = sc.nextInt();
		System.out.print("Tax Cells Number: ");
		howManyTaxedCells = sc.nextInt();
		System.out.print("Please enter the tax amount which will be taken from the players : ");
		taxedMoneyAmount = sc.nextInt();
		System.out.print("How Many People Will Play Monopoly? ");
		numberOfPlayers = sc.nextInt();

		String nameOfPlayers[] = new String[numberOfPlayers] ;
		for (int i = 0; i < numberOfPlayers ; i++) {
			System.out.print("Enter the name of player " + (i + 1) + ": ");
			nameOfPlayers[i] = sc.next();

		}

		Game Monopoly = new Game(initialMoney, howManyTaxedCells, nameOfPlayers, taxedMoneyAmount);
		Monopoly.Play();


	/*	File file = new File("C:/Users/dell/Desktop/eclipse-workspace/Monopoly Game/src/test.txt");
		try {

			Scanner sc = new Scanner(file);

			int i = 0;
			String nameOfPlayers[] = new String[i] ;
			int numberOfPlayers = 0;
			int taxedMoneyAmount = 0;
			int howManyTaxedCells = 0;
			int initialMoney = 0;
			while (sc.hasNextLine()) {
				switch (i) {
				case 0:
					initialMoney = sc.nextInt();
					i++;
					break;
				case 1:
					howManyTaxedCells = sc.nextInt();
					i++;
					break;
				case 2:
					taxedMoneyAmount = sc.nextInt();
					i++;
					break;
				case 3:
					numberOfPlayers = sc.nextInt();


					i++;
					break;
				}

				if (i == 4) {

					nameOfPlayers =  new String[numberOfPlayers];
					for (int j = 0; j < numberOfPlayers ; j++) {
						nameOfPlayers[j] = sc.next();

					}
					break;
				}

			}

			Game Monopoly = new Game(initialMoney, howManyTaxedCells, nameOfPlayers, taxedMoneyAmount);
			Monopoly.Play();

		} catch (FileNotFoundException ex) {
			System.out.println("File is not found");
		}*/

	}

}
