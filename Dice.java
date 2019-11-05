
public class Dice extends Board {
	private int faceValue1;
	private int faceValue2;
	private int initialMoney;
	private int howManyTaxedCells;
	private int taxedMoneyAmount;

	Dice() {
		//System.out.println("hello ý am dice");
		System.out.println("wfsf");
	}

	public int rollDices() {
		System.out.println("dddd");
		faceValue1 = (int) (Math.random() * 6 + 1);
		faceValue2 = (int) (Math.random() * 6 + 1);
		System.out.println("Face value 1 : " + faceValue1+" Face Value 2 : "+faceValue2);

		return (faceValue1 + faceValue2);

	}


}
