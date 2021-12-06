package cinema;
import java.util.Scanner;
import java.lang.Math;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:" );
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        int ticketPrice;
        int input = 1;
        int income;
        int currentIncome = 0;
        int ticketPurchased = 0;
        double percentage = 0;
        char[][] cinema = new char[rows+1][seatsInRow+1];
        for (int i = 0; i < rows + 1; i++) {
            cinema[i][0] = (char) (i + '0');

        }
        for (int j = 0; j < seatsInRow + 1; j++) {
            cinema[0][j] = (char) (j + '0');
        }
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < seatsInRow + 1; j++) {
                cinema[i][j] = 'S';
            }
        }
        while (input != 0) {
            System.out.println("1. Show the seats" );
            System.out.println("2. Buy a ticket" );
            System.out.println("3. Statistics" );
            System.out.println("0. Exit" );
            input = scanner.nextInt() ;
            if (input == 0) {
                break;
            }
            switch (input) {
                case 1:
                    printSeats(cinema, rows + 1, seatsInRow + 1);
                    break;
                case 2:
                    System.out.println("Enter a row number:");
                    int row = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seatInRow = scanner.nextInt();
                    while (row>rows || seatInRow>seatsInRow) {
                        System.out.println("Wrong input!");
                        System.out.println("Enter a row number:");
                        row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatInRow = scanner.nextInt();
                    }
                    while (cinema[row][seatInRow] == 'B') {
                        System.out.println("That ticket has already been purchased!");
                        System.out.println("Enter a row number:");
                        row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatInRow = scanner.nextInt();
                    }
                    ticketPrice = getTicketPrice(rows,seatsInRow,row) ;
                    System.out.println("Ticket price: $" + ticketPrice);
                    cinema[row][seatInRow] = 'B';
                    currentIncome += ticketPrice;
                    ticketPurchased += 1;
                    percentage = (double) (ticketPurchased*100)/(rows*seatsInRow);
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d\n", ticketPurchased );
                    System.out.printf("Percentage: %.2f", percentage );
                    System.out.println("%");
                    System.out.printf("Current income: $%d\n" , currentIncome);
                    income = getTotalIncome(rows,seatsInRow);
                    System.out.printf("Total income: $%d\n" , income );

            }
        }

    }

    static void printSeats(char[][] cinema , int rows , int seatsInRow) {
        System.out.println("Cinema:" );
        cinema[0][0] = ' ';
        for (int i = 0; i<rows;  i++) {
            for (int j = 0; j < seatsInRow ; j++) {
                System.out.print(" " +cinema[i][j]);
            }
            System.out.print("\n");
        }
    }

    static int getTicketPrice(int rows , int seatsInRow, int row) {
        int frontSeats ;
        int ticketPrice;
        if (rows * seatsInRow <= 60) {
            ticketPrice = 10;
        } else {
            frontSeats = (int) Math.floor(rows / 2);
            if (row <= frontSeats) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        return ticketPrice;
    }

    static int getTotalIncome(int rows , int seatsInRow) {
        int income;
        int frontSeats;
        if (rows * seatsInRow <= 60) {
            income = 10* rows*seatsInRow;
        } else {
            frontSeats = (int) Math.floor(rows / 2);
            income = frontSeats*seatsInRow*10;
            income += (rows-frontSeats)*seatsInRow*8;
        }
        return income ;
    }
}
