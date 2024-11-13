import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Room class representing each hotel room
class Room {
    private int roomNumber;
    private String roomType;
    private boolean isBooked;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Room " + roomNumber + " has been booked.");
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }

    public void releaseRoom() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Room " + roomNumber + " has been released.");
        } else {
            System.out.println("Room " + roomNumber + " is not booked.");
        }
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Type: " + roomType + ", Status: " + (isBooked ? "Booked" : "Available");
    }
}

// Hotel Management System
public class HotelManagementSystem {
    private List<Room> rooms = new ArrayList<>();
    private Scanner scanner;

    private static final int VIEW_ROOMS = 1;
    private static final int BOOK_ROOM = 2;
    private static final int RELEASE_ROOM = 3;
    private static final int EXIT = 4;

    public HotelManagementSystem() {
        this.scanner = new Scanner(System.in);
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(104, "Single"));
        rooms.add(new Room(105, "Double"));
    }

    public void displayMenu() {
        System.out.println("\n--- Hotel Management System ---");
        System.out.println(VIEW_ROOMS + ". View all rooms");
        System.out.println(BOOK_ROOM + ". Book a room");
        System.out.println(RELEASE_ROOM + ". Release a room");
        System.out.println(EXIT + ". Exit");
    }

    public void viewAllRooms() {
        System.out.println("\n--- Room List ---");
        rooms.forEach(System.out::println);
    }

    public void bookRoom() {
        System.out.print("Enter room number to book: ");
        int roomNumber = getUserInput();

        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            room.bookRoom();
        } else {
            System.out.println("Room not found!");
        }
    }

    public void releaseRoom() {
        System.out.print("Enter room number to release: ");
        int roomNumber = getUserInput();

        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            room.releaseRoom();
        } else {
            System.out.println("Room not found!");
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = getUserInput();

            switch (choice) {
                case VIEW_ROOMS:
                    viewAllRooms();
                    break;
                case BOOK_ROOM:
                    bookRoom();
                    break;
                case RELEASE_ROOM:
                    releaseRoom();
                    break;
                case EXIT:
                    exit = true;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();  // Close the scanner at the end of the program
    }

    public static void main(String[] args) {
        HotelManagementSystem hotelManagementSystem = new HotelManagementSystem();
        hotelManagementSystem.run();
    }
}
