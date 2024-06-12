package application;

public class Tree {
    private int treeCount;

	public Tree(int treeCount) {
		this.treeCount = Storage.loadTreeCount();
	}

    public int getCount() {
        return this.treeCount;
    }

    public void addTree(int amount) {
        this.treeCount += amount;
    }
}

