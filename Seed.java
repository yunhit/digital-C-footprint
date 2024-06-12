package application;

public class Seed {
	private int seedCount;

	public Seed(int seedCount) {
		this.seedCount = Storage.loadSeedCount();
	}
	
	public void addSeed(int amount) {
		this.seedCount += amount;
	}
	
	public boolean useSeed(int amount) {
		if (this.seedCount >= amount) {
			this.seedCount -= amount;
			return true;
		}
		return false;
	}
	
	public int getCount() {
		return this.seedCount;
	}

}