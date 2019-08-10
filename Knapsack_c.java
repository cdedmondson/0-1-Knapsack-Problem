import java.util.Scanner;
import java.util.PriorityQueue;

public class Knapsack_c {

    public static int numberOfItems, maxProfit = 0, maxWeight, nodeCount = 1;
    public static int[] profitArr, weightArr, bestSet, include;

    //*****************************************main begin**************************************************
    public static void main(String[] args) {

        promptUser();

        knapsack(profitArr, weightArr);

        bestSet = include.clone();

        System.out.println("\nMaximum profit: " + maxProfit);
        System.out.println("\nNumber of visited nodes: " + nodeCount);

    }
    //*****************************************main end*****************************************************

    private static void knapsack(int profitArr[], int weightArr[]) {

        Node u;
        Node v = new Node(); // tree root
        PriorityQueue<Node> PQ = new PriorityQueue<>(); // initialize PQ to be empty

        PQ.add(v); // place v in the queue

        v.bound = bound(v);

        PQ.add(v); // insert root to PQ

        // while queue is not empty continue
        while (!PQ.isEmpty()) {

            v = PQ.poll();  // remove unexpanded node with best bound

            if (v.bound > maxProfit) { // see if node is still promising

                u = new Node(); // create new instance of 'u'
                u.setLevel((v.getLevel() + 1));
                u.setWeight(v.getWeight() + weightArr[u.getLevel()]); // set u to the child of v
                u.setProfit(v.getProfit() + profitArr[u.getLevel()]); // that indicates taking the next item

                u.bound = bound(u);

                if ((u.getWeight() <= maxWeight) && (u.getProfit() > maxProfit)) {
                    maxProfit = u.getProfit();  // taking u gives us the best profit so far
                }

                if (u.bound > maxProfit) {
                    nodeCount++;
                    include[u.getLevel()] = 1;
                    PQ.add(u);   // if u is promising, add to queue
                }

                u = new Node(); // create new instance of 'u'
                u.setLevel((v.getLevel() + 1));
                u.setWeight(v.getWeight());
                u.setProfit(v.getProfit()); // set u to the child of v that indicates not taking the next item
                u.bound = bound(u);

                if (u.bound > maxProfit) {
                    nodeCount++;
                    include[u.getLevel()] = 0;
                    PQ.add(u); // if u is promising, add to queue
                }
            }
        }
    }

    public static float bound(Node u) {

        int j; // the index of the first item we have not yet accepted/rejected
        int k; // the index of the item that, if taken, would bring totalWeight over maxWeight
        int totalWeight;
        float result;

        if (u.getWeight() >= maxWeight) {
            return 0;
        } else {

            result = u.getProfit();
            j = u.getLevel() + 1;
            totalWeight = u.getWeight();

            /*
                While j is less than or equal to the number of potential items and
                the totalWeight of potential items is less than or equal to maxWeight
                continue to grab as many items as possible
             */
            while ((j <= numberOfItems) && (totalWeight + weightArr[j] <= maxWeight)) {

                totalWeight = totalWeight + weightArr[j];
                result = result + profitArr[j];
                j++;

            }

            k = j;

            if (k <= numberOfItems) {
                result = result + (maxWeight - totalWeight) * (float) profitArr[k] / (float) weightArr[k];
            }
        }

        return result;
    }

    //*****************************************promptUser begin********************************************
    /*
     * Prompt user for following information:
     * 1. How many items are there to potentially take? ---> numberOfItems
     * 2. What is the weight and profit of each item? ---> weight[i], ---> profit[i]
     * 3. What is the max weight the bag can hold? ---> maximumWeight
     */
    public static void promptUser() {

        Scanner keyboard = new Scanner(System.in);
        int item;

        System.out.print("\nHow many items are there to potentially take? ");
        item = keyboard.nextInt();

        numberOfItems = item;
        weightArr = new int[item + 1];
        profitArr = new int[item + 1];
        bestSet = new int[item];
        include = new int[item];

        System.out.println();

        for (int i = 0; i < numberOfItems; i++) {

            System.out.println("What is the weight of item " + (i + 1) + "? ");
            item = keyboard.nextInt();
            weightArr[i] = item;

            System.out.println("What is the profit of item " + (i + 1) + "? ");
            item = keyboard.nextInt();
            profitArr[i] = item;

            System.out.println();

        }

        System.out.println("What is the max weight the bag can hold? ");
        maxWeight = keyboard.nextInt();

    }
    //*****************************************promptUser end********************************************

} // end knapsack_c class