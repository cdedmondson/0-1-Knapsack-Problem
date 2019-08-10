import java.util.Scanner;

public class Knapsack_a {

    public static int numberOfItems = 4, maxProfit = 0, maxWeight = 16, nodeCount = 1;
    public static int[] profitArr, weightArr, bestSet, include;

    //*****************************************main begin**************************************************
    public static void main(String[] args) {

        promptUser();
        orderItemsInNonIncreasingOrder();

        solveKnapsack(0, 0, 0);

        System.out.print("\nBest set: {");
        for (int i = 0; i < bestSet.length; i++) {
            System.out.print(bestSet[i] + ",");
        }
        System.out.print("}\n");

        System.out.println("\nMaximum profit: " + maxProfit);
        System.out.println("\nNumber of visited nodes: " + nodeCount);

    }
    //*****************************************main end****************************************************

    //*****************************************solveKnapsack begin*****************************************
    private static void solveKnapsack(int i, int profit, int weight) {

        /*
            if 'i' is equal to number of items and current weight is
            less than or equal to maximum weight and current profit
            is greater than maximum profit set current profit to
            maxProfit and bestSet to include best set so far
         */
        if (i == numberOfItems && weight <= maxWeight && profit > maxProfit) {
            maxProfit = profit;
            bestSet = include.clone();
        }

        // Base case: if 'i' is equal to number of items end recursion
        if (i == numberOfItems) {
            return;
        }

        nodeCount++;    // keep track of visited nodes
        include[i] = 1; // include item i
        solveKnapsack(i + 1, profit + profitArr[i], weight + weightArr[i]);
        nodeCount++;    // keep track of visited nodes
        include[i] = 0; // Don't include item i
        solveKnapsack(i + 1, profit, weight);
    }
    //*****************************************solveKnapsack end*******************************************

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

    //*****************************************orderItemsInNonIncreasingOrder begin**********************
    /*
     *  Order items in non-decreasing order
     *  by dividing profit by weight
     */
    public static void orderItemsInNonIncreasingOrder() {

        int tempProfit, tempWeight;

        for (int i = 0; i < numberOfItems; i++) {

            for (int j = i + 1; j < numberOfItems; j++) {

                if (profitArr[i] / weightArr[i] < profitArr[j] / weightArr[j]) {

                    tempProfit = profitArr[j];
                    tempWeight = weightArr[j];

                    profitArr[j] = profitArr[i];
                    weightArr[j] = weightArr[i];

                    profitArr[i] = tempProfit;
                    weightArr[i] = tempWeight;

                } // end if statement

            } // end for loop j

        } // end for loop i

    }
    //*****************************************orderItemsInNonIncreasingOrder end*************************

} // end knapsack_a