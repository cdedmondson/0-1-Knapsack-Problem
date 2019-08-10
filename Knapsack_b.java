import java.util.Scanner;

public class Knapsack_b {

    public static int numberOfItems, maxProfit = 0, maxWeight = 16, numBest, nodeCount = 1;
    public static int[] profitArr, weightArr, bestSet, include;

    //*****************************************main begin***************************************************
    public static void main(String[] args) {

        promptUser();
        orderItemsInNonIncreasingOrder();

        solveKnapsack(-1, 0, 0);

        System.out.print("\nBest set: {");
        for (int i = 0; i < bestSet.length; i++) {
            System.out.print(bestSet[i] + ",");
        }
        System.out.print("}\n");

        System.out.println("\nMaximum profit: " + maxProfit);
        System.out.println("\nNumber of visited nodes: " + nodeCount);

    }
    //****************************************main end******************************************************

    //*****************************************solveKnapsack begin******************************************
    private static void solveKnapsack(int i, int profit, int weight) {

        /*
            If total weight is greater than or equal to maximum weight
            and total profit is greater than current maximum profit take
            current profit as maximum profit
         */
        if ((weight <= maxWeight) && (profit > maxProfit)) {
            maxProfit = profit; // set maxProfit to best profit so far
            numBest = i;    // number of items with best profit
            bestSet = include.clone();  // set bestSet to best set so far
        }

        // Check if item can be placed in bag
        if (promising(i, profit, weight)) {

            nodeCount++; // keep track of visited nodes
            include[i + 1] = 1; // include item i + 1
            solveKnapsack(i + 1, profit + profitArr[i + 1], weight + weightArr[i + 1]);
            nodeCount++; // keep track of visited nodes
            include[i + 1] = 0; // Don't include item i + 1
            solveKnapsack(i + 1, profit, weight);

        }
    }
    //*****************************************solveKnapsack end*********************************************

    //*****************************************promising begin***********************************************
    public static boolean promising(int i, int profit, int weight) {

        int j; // the index of the first item we have not yet accepted/rejected
        int k; // the index of the item that, if taken, would bring totalWeight over maxWeight
        int totalWeight;
        double bound;

        if (weight >= maxWeight) {
            return false;
        } else {

            j = i + 1;
            bound = profit;
            totalWeight = weight;

            /*
                While j is less than or equal to the number of potential items and
                the totalWeight of potential items is less than or equal to maxWeight
                continue to grab as many items as possible
             */
            while ((j <= numberOfItems) && (totalWeight + weightArr[j] <= maxWeight)) {

                totalWeight = totalWeight + weightArr[j];
                bound = bound + profitArr[j];
                j++;

            }

            k = j;

            if (k <= numberOfItems) {
                bound = bound + (maxWeight - totalWeight) * ((double) profitArr[k] / (double) weightArr[k]);
            }
        }

        return bound > maxProfit;
    }
    //*****************************************promising end***********************************************

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

} // end knapsack_b class