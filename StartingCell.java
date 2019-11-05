public class StartingCell extends Cell {
	int initialMoney;

	StartingCell(int id) {
		super(id);
		this.initialMoney = initialMoney;
	}

	public void MoneyFunc(Player player) {

		int amountOfMoney = player.getAmountOfMoney() + initialMoney;
		player.setAmountOfMoney(amountOfMoney);
		(super.getBank()).outMoney(initialMoney);
	}

}