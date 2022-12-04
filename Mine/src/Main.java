import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Mine mine1 = new Mine("First mine");
        consoleUI(mine1);
    }

    private static void consoleUI(Mine mine){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        uiMenu();
        while(!quit){
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action){
                case 0:
                    System.out.println("Quitting the app...");
                    quit = true;
                    break;
                case 1:
                    mine.goMining();
                    break;
                case 2:
                    mine.passTime();
                    break;
                case 3:
                    mine.manageWarehouse();
                    uiMenu();
                    break;
                case 4:
                    System.out.println("Rename to: ");
                    String newName = scanner.nextLine();
                    mine.setName(newName);
                    System.out.println("Mine has been renamed to " + mine.getName());
                    break;
                case 5:
                    System.out.println(mine.getPassedDays() + " days have passed");
                    break;
                case 6:
                    System.out.println(mine.getName());
                    break;
                case 7:
                    uiMenu();
            }
        }
    }

    private static void uiMenu(){
        System.out.println("0 - quit the app \n" +
                "1 - go mining \n" +
                "2 - pass time \n" +
                "3 - manage the warehouse \n" +
                "4 - rename the mine  \n" +
                "5 - show passed days \n" +
                "6 - display mine details \n"+
                "7 - display possible actions");
    }
}