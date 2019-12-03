// 03.12.2019
public class Bank {
	private int totalMoney;

	Bank() {
		totalMoney = 10000000;
	}

	public void outMoney(int amount) {
		totalMoney -= amount;
	}

	public void inMoney(int amount) {
		totalMoney += amount;

	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
