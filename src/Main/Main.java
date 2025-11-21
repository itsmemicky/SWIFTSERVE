package Main;

import adminDashboard.adminDashboard;
import costumerDashboard.customerDashboard;
import java.util.Scanner;

public class Main {
    
    public static Scanner inp = new Scanner(System.in);
    public static int currentUserID = -1; 
    
    public static void main(String[] args) {
        
        System.out.println("\n----------------------------------");
        System.out.println("Welcome to Catering Booking System");
        System.out.println("----------------------------------");
        
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        
        System.out.print("\nChoose an option: ");
        String option = inp.nextLine();
        
        authentication au = new authentication();
        
        switch(option){
            case "1":
                au.login();
                break;
            case "2":
                au.register();
                break;
            case "3":
                System.out.println("EXIT......");
                return;
            default:
                System.out.println("\nInvalid Input!");
                main(null);
                return;
        }
    }
    
    public static void adminDashborad(){
        String res;
        do{
            System.out.println("\n----------------");
            System.out.println("ADMIN DASHBOARD");
            System.out.println("----------------");

            System.out.println("1. Manage Users");
            System.out.println("2. Manage Events");
            System.out.println("3. Manage Menu");
            System.out.println("4. Manage Bookings");
            System.out.println("5. Logout");

            System.out.print("\nChoose an option: ");
            String option = inp.nextLine();

            adminDashboard ad = new adminDashboard();

            switch(option){
                case "1":
                    ad.manageUsers();
                    break;
                case "2":
                    ad.manageEvents();
                    break;
                case "3":
                    ad.manageMenu();
                    break;
                case "4":
                    ad.manageBookings();
                    break;
                case "5":
                    System.out.println("Logout....");
                    currentUserID = -1;
                    main(null);
                    return;
                default:
                    System.out.println("\nInvalid Input!");
                    main(null);
                    return;
            }
            
            System.out.print("\nDo you want to continue in Admin Dashboard? (yes / no): ");
            res = inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
        Main.adminDashborad(); return;
    }
    
    public static void customerDashboard(){
        String res;
        do {
            System.out.println("\n----------------");
            System.out.println("COSTUMER DASHBOARD");
            System.out.println("----------------");
        
            System.out.println("1. View Events");
            System.out.println("2. View Menus");
            System.out.println("3. Create Booking");
            System.out.println("4. View My Bookings");
            System.out.println("5. Logout");

            System.out.print("\nChoose an option: ");
            String option = inp.nextLine();

            adminDashboard ad = new adminDashboard();
            costumerDashboard.customerDashboard cd = new costumerDashboard.customerDashboard();

            switch(option){
                case "1":
                    ad.viewEvent();
                    break;
                case "2":
                    ad.viewMenu();
                    break;
                case "3":
                    cd.createBooking();
                    break;
                case "4":
                    cd.viewMyBooking();
                    break;
                case "5":
                    System.out.println("Logout....");
                    currentUserID = -1;
                    main(null);
                    return;
                default:
                    System.out.println("\nInvalid Input!");
                    main(null);
                    return;
            }

            System.out.print("\nDo you want to continue? (yes / no - Logout): ");
            res = inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
        Main.customerDashboard(); return;
    }
}
