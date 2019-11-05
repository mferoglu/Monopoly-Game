
public class Bank extends Game {
	private int totalMoney;

	Bank() {
		super();
		totalMoney = 10000000;
	}

	public void outMoney(int amount) {
		totalMoney -= amount;
	}

	public void inMoney(int amount) {
		totalMoney += amount;

	}
}
