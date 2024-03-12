import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {     
    String name;  // name for movies
    double childPrice; // prices for user if they are child, adult, senior
    double adultPrice;
    double seniorPrice; 

    public Movie(String name, double childPrice, double adultPrice, double seniorPrice) { //constructor
        this.name = name;
        this.childPrice = childPrice;
        this.adultPrice = adultPrice;
        this.seniorPrice = seniorPrice;
    }
}

class MovieTicket {
    Movie movie;
    int numberOfTickets;
    String ticketNumber;
    String pricingCategory;

    public MovieTicket(Movie movie, int numberOfTickets, String ticketNumber, String pricingCategory) {
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
        this.ticketNumber = ticketNumber;
        this.pricingCategory = pricingCategory;
    }

    public double getTotalCost() {     // Method to calculate the total cost of the ticket based on pricing category
        switch (pricingCategory) {
            case "Child":
                return movie.childPrice * numberOfTickets;
            case "Adult":
                return movie.adultPrice * numberOfTickets;
            case "Senior":
                return movie.seniorPrice * numberOfTickets;
            default:
                return 0.0;
        }
    }
}

 class MovieTicketSystem { // Main class for the movie ticket system
    private static List<MovieTicket> ticketList = new ArrayList<>();
    private static List<Movie> movieList = new ArrayList<>();
    private static int ticketCounter = 1;

    public static void main(String[] args) {
        initializeMovies();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n!!! WELCOME TO REGAL CINEMAS !!!!!");

        while (true) { // DISPLAY MENU OPTIONS   // START OF THE WHILE LOOP
            System.out.println("1. Book/Add Movie Ticket");
            System.out.println("2. View Movie Ticket");
            System.out.println("3. Delete Movie Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    bookMovieTicket(scanner);
                    break;
                case 2:
                    viewMovieTickets();
                    break;
                case 3:
                    deleteMovieTicket(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }  //END OF WHILE LOOP
    }

    private static void initializeMovies() { //METHOD TO INITIALIZE MOVIES WITH PRICES
        movieList.add(new Movie("Trolls", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Deadpool", 6.30, 14.30, 8.0));
        movieList.add(new Movie("The Dark Knight", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Silent Night", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Equalizer 4", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Lego Spiderman", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Oppenheimer", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Barbie", 6.30, 14.30, 8.00));
        movieList.add(new Movie("Spiderman:Across the Spider-Verse", 6.30, 14.30, 8.00));
        movieList.add(new Movie("The Little Mermaid", 6.30, 14.30, 8.00));

    }

    private static void bookMovieTicket(Scanner scanner) { //METHOD TO BOOK A MOVIE TICKET
        if (movieList.isEmpty()) {
            System.out.println("No movies available for booking.");
            return;
        }

        System.out.println("\nAvailable Movies:");  //DISPLAY THE MOVIES OUT FROM THEATHER
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + " . " + movieList.get(i).name);
        }

        System.out.print("\nEnter the movie number: ");  //USER SELECTS THE MOVIE FROM PREVIOUS LINE
        int movieIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (movieIndex < 1 || movieIndex > movieList.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        Movie selectedMovie = movieList.get(movieIndex - 1); //USER SELECTS FROM AGE PRICE CARTEGORY

        System.out.println("\nSelect Pricing Category:");
        System.out.println("1. Child - $6.30");
        System.out.println("2. Adult - $14.30");
        System.out.println("3. Senior - $8.00");
        System.out.print("Enter the pricing category number: ");
        int categoryIndex = scanner.nextInt();
        scanner.nextLine(); 

        String pricingCategory;
        switch (categoryIndex) {
            case 1:
                pricingCategory = "Child";
                break;
            case 2:
                pricingCategory = "Adult";
                break;
            case 3:
                pricingCategory = "Senior";
                break;
            default:
                System.out.println("Invalid category selection. Defaulting to Adult pricing.");
                pricingCategory = "Adult";
        }

        System.out.print("\nEnter number of tickets: "); //USER ENETERS THE NUMBER OF TICKETS
        int numberOfTickets = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numberOfTickets; i++) { //START OF FOR LOOP
            String ticketNumber = "TCK" + ticketCounter++;
            MovieTicket ticket = new MovieTicket(selectedMovie, numberOfTickets, ticketNumber, pricingCategory);
            ticketList.add(ticket);
            System.out.println("\nTicket booked successfully. Ticket Number: " + ticketNumber);
            System.out.println("Total Cost: $" + ticket.getTotalCost());
        } // END OF FOR LOOP
    }

    private static void viewMovieTickets() {  //USER TO VIEW THE TICKET WAS BOOKED
        if (ticketList.isEmpty()) {
            System.out.println("\nNo tickets available.");
        } else {
            System.out.println("\nMovie Tickets:");
            for (MovieTicket ticket : ticketList) {
                System.out.println("Ticket Number: " + ticket.ticketNumber);
                System.out.println("Movie: " + ticket.movie.name);
                System.out.println("Number of Tickets: " + ticket.numberOfTickets);
                System.out.println("Pricing Category: " + ticket.pricingCategory);
                System.out.println("Total Cost: $" + ticket.getTotalCost());
                System.out.println("---------------------------");
            }
        }
    }
    private static void deleteMovieTicket(Scanner scanner) { //USER DELETES A TICKET THAT WAS BOOKED
        if (ticketList.isEmpty()) {
            System.out.println("\nNo tickets available for deletion.");
            return;
        }

        System.out.print("\nEnter the ticket number to delete: ");
        String ticketNumberToDelete = scanner.nextLine();

        boolean ticketFound = false;
        for (MovieTicket ticket : ticketList) {  //START OF FOR LOOP
            if (ticket.ticketNumber.equals(ticketNumberToDelete)) {
                ticketList.remove(ticket);
                ticketFound = true;
                System.out.println("\nTicket " + ticketNumberToDelete + " deleted successfully.");
                break;
            }
        }// END OF FOR LOOP

        if (!ticketFound) {
            System.out.println("\nTicket not found.");
        }
    }
}
