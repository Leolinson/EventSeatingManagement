import java.util.Scanner;

public class EventTableCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of groups: ");
        int numGroups = scanner.nextInt();

        int[] groupSizes = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            System.out.print("Enter the size of group " + (i + 1) + ": ");
            groupSizes[i] = scanner.nextInt();
        }

        int totalGuests = calculateTotalGuests(groupSizes);
        int tablesNeeded = calculateTablesNeeded(groupSizes);

        System.out.println("The total number of guests is: " + totalGuests);
        System.out.println("The lowest number of tables needed is: " + tablesNeeded);

        displaySeatingArrangement(groupSizes, tablesNeeded);

        scanner.close();
    }

    public static int calculateTotalGuests(int[] groupSizes) {
        int totalGuests = 0;

        for (int groupSize : groupSizes) {
            totalGuests += groupSize;
        }

        return totalGuests;
    }

    public static int calculateTablesNeeded(int[] groupSizes) {
        int tablesNeeded = 0;
        int[] availableTables = new int[groupSizes.length];

        for (int i = 0; i < groupSizes.length; i++) {
            int groupSize = groupSizes[i];
            boolean assigned = false;

            for (int j = 0; j < tablesNeeded; j++) {
                if (availableTables[j] + groupSize <= 8) {
                    availableTables[j] += groupSize;
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                availableTables[tablesNeeded] = groupSize;
                tablesNeeded++;
            }
        }

        return tablesNeeded;
    }

    public static void displaySeatingArrangement(int[] groupSizes, int tablesNeeded) {
        System.out.println("Seating Arrangement per Table:");
        
        for (int i = 0; i < tablesNeeded; i++) {
            System.out.println("Table " + (i + 1) + ":");
            int remainingSeats = 8;
            
            for (int j = 0; j < groupSizes.length; j++) {
                int groupSize = groupSizes[j];
                
                if (groupSize <= remainingSeats) {
                    System.out.println("  Group " + (j + 1) + ": " + groupSize + " guests");
                    remainingSeats -= groupSize;
                    groupSizes[j] = 0;
                }
            }
            
            System.out.println("  Empty Seats: " + remainingSeats);
        }
    }
}
