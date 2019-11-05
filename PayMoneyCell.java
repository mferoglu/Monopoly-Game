
public class PayMoneyCell extends Cell {
	private int amountMoneyToBeTaken;

	PayMoneyCell(int id,int amountMoneyToBeTaken ) {
		super(id);
		this.amountMoneyToBeTaken = amountMoneyToBeTaken;
	}

	public void MoneyFunc(Player player){
		int amountOfMoney = player.getAmountOfMoney()-amountMoneyToBeTaken;
			player.setAmountOfMoney(amountOfMoney);
			(super.getBank()).inMoney(amountMoneyToBeTaken);

	}

}
