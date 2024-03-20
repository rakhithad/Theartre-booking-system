import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;

public class Theatre {
    // Rows Arrays
    static int[]row1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[]row2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[]row3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[][] seats={row1,row2,row3};
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        // Menu
        String menu = """
                --------------------------------------------------
                Please select an option:
                1) Buy a ticket
                2) Print seating area
                3) Cancel ticket
                4) List available seats
                5) Save to file
                6) Load from file
                7) Print ticket information and total price
                8) Sort ticket by price
                0) Quit
                --------------------------------------------------
                """;
        System.out.println(menu);
        // Option Control Structure
        int option;
        while (true) {
            try {
                do {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter an option: ");
                    option = sc.nextInt();
                    switch (option) {
                        case 1 -> buy_ticket();
                        case 2 -> print_seating_area();
                        case 3 -> cancel_ticket();
                        case 4 -> show_available();
                        case 5 -> save();
                        case 6 -> {
                            load(row1, 0);
                            load(row2, 12);
                            load(row3, 28);
                        }
                        case 7 -> show_ticket_info();
                        case 0 -> System.out.println("Exiting program............");
                        default -> System.out.println("Invalid option. Please try again and input another option.");
                    }
                } while (option != 0);
            } catch (Exception e) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
            break;
        }
    }
    static int row_number;
    static int seat_number;
    static String name;
    static String surname;
    static String email;
    static Double price;
    static double total_price = 0;
    static boolean valid;

    static ArrayList<Ticket> Ticket_info = new ArrayList<>();

    // buy_ticket method
    public static void buy_ticket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        name=sc.nextLine();

        System.out.print("Enter your surname: ");
        surname=sc.nextLine();

        System.out.print("Enter your email: ");
        email=sc.nextLine();

        Person person_object = new Person(name,surname,email);

        while(true) {
            try {
                Scanner myObj = new Scanner(System.in);
                System.out.print("Enter a row number: ");
                row_number = myObj.nextInt();

                if (row_number < 4 && row_number > 0) {
                    boolean available_seats = false;
                    for(int seat: seats[row_number-1]) {
                        if(seat == 0) {
                            available_seats=true;
                            break;
                        }
                    }
                    if(!available_seats) {
                        System.out.println("No seats available in this row.");
                        continue;
                    }
                    while(true) {
                        try {
                            Scanner myObj1 = new Scanner(System.in);
                            System.out.print("Enter seat number: ");
                            seat_number = myObj1.nextInt();

                            if (seat_number > 0 && seat_number <= seats[row_number - 1].length){
                                if(seats[row_number - 1][seat_number - 1]==0) {
                                    while (true) {
                                        try {
                                            Scanner myObj2=new Scanner(System.in);
                                            System.out.print("Enter price: ");
                                            price = myObj2.nextDouble();
                                            total_price += price;
                                            break;
                                        }catch (Exception e) {
                                            System.out.println("Enter a valid input");
                                        }
                                    }
                                    seats[row_number - 1][seat_number - 1] = 1;
                                    Ticket ticket_object=new Ticket(row_number,seat_number,price,person_object);
                                    Ticket_info.add(ticket_object);
                                    break;
                                }else {
                                    System.out.println("Sorry this seat is not available.");
                                }

                            } else {
                                System.out.println("Enter a valid seat number");
                            }
                        }catch (Exception e) {
                            System.out.println("Please enter a valid input");
                        }
                    }
                } else {
                    System.out.println("Enter row number in range 1-3");
                    continue;
                }
                break;
            }catch(Exception e) {
                System.out.println("Please enter a valid Input");
            }
        }
        System.out.println("Ticket purchased successfully.\n");
    }
    // print_seating_area method
    public static void print_seating_area() {
        System.out.println("""
                     ***********
                     *  STAGE  *
                     ***********
                """);
        for (int row = 0; row < seats.length; row++) {
            if(row == 0) {
                System.out.print("    ");
            }
            if(row == 1) {
                System.out.print("  ");
            }

            for(int seat = 0; seat < seats[row].length; seat++) {
                if (seat == (seats[row].length/2)) {
                    System.out.print(" ");
                }
                if(seats[row][seat] == 0) {
                    System.out.print("O");
                }else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    // cancel_ticket method
    public static void cancel_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name: ");
        name=input.nextLine();

        System.out.print("Enter surname: ");
        surname=input.nextLine();

        System.out.print("Enter email: ");
        email=input.nextLine();

        while(true) {
            try {
                Scanner input1=new Scanner(System.in);
                System.out.print("Enter row number: ");
                row_number = input1.nextInt();

                System.out.print("Enter seat number: ");
                seat_number = input1.nextInt();


                if(row_number<1 || row_number >3|| seat_number<1 || seat_number>4*(2+row_number)){
                    System.out.println("Invalid row or seat number.");

                }else {
                    if (seats[row_number - 1][seat_number - 1] == 0) {
                        System.out.println("This seat is available");

                    } else {
                        while (true) {
                            try {

                                Scanner input2 = new Scanner(System.in);
                                System.out.print("Enter price: ");
                                price = input2.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("Enter a valid input");
                            }
                        }
                        seats[row_number - 1][seat_number - 1] = 0;
                        total_price -= price;
                        for (Ticket object : Ticket_info) {
                            if (Ticket_info.contains(object)) {
                                Ticket_info.remove(object);
                                valid=true;
                            }
                        }
                        if(valid) {
                            System.out.println("Ticket canceled successfully.");
                            break;
                        }else {
                            System.out.println("Details you have entered are not matching");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid input");
            }
        }
    }
    // show_available method
    public static void show_available() {
        System.out.print("\nSeats available in row 1 : ");
        int seatnumber1 = 1;
        for(int i=0; i<seats[0].length;i++){
            if(seats[0][i] == 0) {
                System.out.print(seatnumber1+", ");
            }
            seatnumber1 += 1;
        }
        System.out.println("\nSeats available in row 2 : ");
        int seatnumber2 = 1;
        for(int i=0;i<seats[1].length;i++){
            if(seats[1][i]==0){
                System.out.print(seatnumber2+", ");
            }
            seatnumber2+=1;
        }
        System.out.print("\nSeats available in row 3 : ");
        int seatnumber3 = 1;
        for(int i=0;i<seats[2].length;i++){
            if(seats[2][i]==0){
                System.out.print(seatnumber3+", ");
            }
            seatnumber3 += 1;
        }
        System.out.println(" ");
    }
    // array file save method
    public static void save(){
        try {
            FileWriter myWriter = new FileWriter("seat_info.txt");
            for (int[] seat : seats) {
                    for (int i : seat) {
                    myWriter.write(i + " ");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Saved successfully.....");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    // array file load method
    public static void load(int[] row, int listStartIndex){
        ArrayList<Integer> listOfChars = new ArrayList<>();
        try {
            FileReader reader = new FileReader("seat_info.txt");
            char c;
            while (reader.ready()){
                c = (char) reader.read();
                if (c!=32 & c!= 10){
                    listOfChars.add((int) c);
                }
            }
            int i =0;
            for (int g = listStartIndex; i<row.length; g++){
                if(listOfChars.get(g)==48){
                    row[i] =0;
                }else {
                    row[i]=1;
                }
                i++;
            }

        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
    // show_ticket_info method
    public static void show_ticket_info(){
        for(Ticket details:Ticket_info){
            details.print();
            System.out.println();
        }
        System.out.println("Total price = "+total_price);
    }
}