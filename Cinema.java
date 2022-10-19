package cinema;
import java.util.Scanner;

public class Cinema {

    public static void arrayS (String theater[][], int rows, int seatsInRow) {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seatsInRow; j++) {
                theater [i][j] = "S";
            }
        }
    }

    public static void display (String theater[][], int rows, int seatsInRow) {
        System.out.print("Cinema:");
        for (int i = 0; i <= rows; i++) {
            System.out.println();
            for (int j = 0; j <= seatsInRow; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" ");
                } else if (i == 0){
                    System.out.print(" "+ j);
                } else if (j == 0) {
                    System.out.print(i);
                } else {
                    System.out.print(" "+theater[i-1][j-1]);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void choiceOfPlace(String theater[][], int rows, int seatsInRow, int rowsC, int seatsInRowC) {
        theater[rowsC - 1][seatsInRowC - 1] = "B";
        int seats = rows * seatsInRow;
        int price;
        if (seats <= 60) {
            price = 10;
        } else {
            if (rowsC <= rows/2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println();
        System.out.printf("Ticket price: $%d", price);
        System.out.println();
        System.out.println();
        //display(theater, rows, seatsInRow);
    }

    public static void statistics(int rows, int seatsInRow, String theater[][]) {
        int seats = rows * seatsInRow;
        int income;
        int purchased = 0;
        int currentIncome = 0;
        float percentage = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j ++) {
                if (theater[i][j].equals("B")) {
                    purchased += 1;
                    if (seats <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i + 1 <= rows/2) {
                          currentIncome += 10;
                        } else {
                          currentIncome += 8;
                        }
                    }
                }
            }
        }

        if (seats <= 60) {
            income = seats * 10;
        } else {
            income = ((rows / 2) * seatsInRow * 10) + ((rows - (rows / 2)) * seatsInRow) * 8;
        }
        System.out.printf("Number of purchased tickets: %d%n", purchased);
        float purchasedf = purchased;
        float seatsf = seats;
        percentage = (purchasedf/seatsf) * 100;
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", income);
        System.out.println();



    }

    public static boolean isEmpty(int rowsC, int seatsInRowC, String theater[][]) {
        return theater[rowsC - 1][seatsInRowC - 1].equals("S");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        System.out.println();
        String[][] theater = new String[rows][seatsInRow];
        arrayS(theater, rows, seatsInRow);
        boolean loop = true;
        //boolean place = true;

        while (loop) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            System.out.println();
            int rowsC;
            int seatsInRowC;

            switch (choice) {
                case 1:
                    display(theater, rows, seatsInRow);
                    continue;
                case 2:
                    boolean place = true;
                    while(place) {
                        System.out.println("Enter a row number:");
                        rowsC = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatsInRowC = scanner.nextInt();
                        if (rowsC < 1 || rowsC > rows || seatsInRowC < 1 || seatsInRowC > seatsInRow) {
                            System.out.println("Wrong input!");
                            System.out.println();
                        } else if (isEmpty(rowsC, seatsInRowC, theater)) {
                            choiceOfPlace(theater, rows, seatsInRow, rowsC, seatsInRowC);
                            place = false;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                            System.out.println();
                        }
                    }
                    continue;
                case 3:
                    statistics(rows, seatsInRow, theater);
                    continue;
                case 0:
                    loop = false;
                    break;
            }


        }
    }
}
