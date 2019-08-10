public class Node implements Comparable<Node> {

    // level = nodes level in the tree
    public int level, weight, profit;
    public float bound;

    public Node() {
        level = -1;
        weight = 0;
        profit = 0;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Node other) {
        return other.getProfit() - profit;
    }

}