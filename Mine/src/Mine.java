import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mine implements IMine{


    private ArrayList<Resources> mineWarehouse = new ArrayList<Resources>();
    public Stack<Resources> mineStockpile = new Stack<Resources>();
    private Chain chain = new Chain("Chain");
    private String name;
    private int workingHours = 0;
    private int passedDays = 0;


    public Mine(String name) {
        this.name = name;
    }

    public int getPassedDays() {
        return passedDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void goMining() {
        workingHours++;
        if(workingHours>=8){
            System.out.println("You are too tired to mine.");
            return;
        }
        System.out.println("Mining begins");
        Resources result;
        int randomNum = ThreadLocalRandom.current().nextInt(1,10);
        if(randomNum == 1){
            System.out.println("You found tin");
            result = Resources.Tin;
        }else if(randomNum == 2){
            System.out.println("It's copper");
            result = Resources.Copper;
        }else if(randomNum == 3){
            System.out.println("You mined Iron");
            result = Resources.Iron;
        }else if(randomNum > 3 && randomNum <= 5){
            System.out.println("Coal...");
            result = Resources.Coal;
        }else{
            System.out.println("You mined only some rubbish");
            result = Resources.Rubbish;
        }

        if(mineWarehouse.size() == 10){
            System.out.println("There is no more space in the warehouse!");
            return;
        }else{
            mineWarehouse.add(result);
        }
    }

    public void passTime(){
        System.out.println("Time has passed...");
        this.workingHours=0;
        passedDays++;
    }

    @Override
    public void manageWarehouse() {
        ListIterator<Resources> listIterator = mineWarehouse.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        System.out.println(warehouseMenu());
        System.out.println("Choose action to perform in warehouse: ");

        while(!quit){
            if(!scanner.hasNextInt()){
                System.out.println("Type in number!");
                scanner.next();
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 0:
                    System.out.println("Leaving the warehouse...");
                    quit = true;
                    break;
                case 1:
                    System.out.println("Warehouse contains: ");
                    for(Resources res : mineWarehouse){
                        System.out.println(res.name());
                    }
                    break;
                case 2:
                    if(listIterator.hasNext()){
                        System.out.println("Selected: " + listIterator.next());
                    }else{
                        System.out.println("You've reached end of the warehouse.");
                    }
                    break;
                case 3:
                    if(listIterator.hasPrevious()){
                        System.out.println("Selected: "+ listIterator.previous());
                    }else{
                        System.out.println("You are now at the front of the warehouse.");
                    }
                    break;
                case 4:
                    mineStockpile.push(listIterator.previous());
                    listIterator.remove();
                    System.out.println("Moved to the stockpile");
                    break;
                case 5:
                    manageStockpile();
                    break;
                case 6:
                    System.out.println(warehouseMenu());
                default:
                    System.out.println("Wrong request!");
                    break;
            }
        }
    }

    @Override
    public void manageStockpile() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        stockpileMenu();
        System.out.println("Choose action to perform in stockpile: ");
        while(!quit){
            if(!scanner.hasNextInt()){
                System.out.println("Type in number!");
                scanner.next();
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 0:
                    System.out.println("Leaving the stockpile...");
                    quit = true;
                    break;
                case 1:
                    System.out.println("Things in the stockpile: "+ mineStockpile.toString());
                    // mineStockpile.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println(mineStockpile.peek()+" is on top of the mining stockpile");
                    break;
                case 3:
                    System.out.println(mineStockpile.peek()+" removed from the stockpile");
                    mineStockpile.pop();
                    break;
                case 4:
                    System.out.println("Transfer to put "+mineStockpile.peek()+" into warehouse begins");
                    Resources resourceToTransfer = mineStockpile.peek();
                    if(mineWarehouse.size() == 10){
                        System.out.println("There is no more space in the warehouse!");
                        break;
                    }else{
                        mineWarehouse.add(resourceToTransfer);
                        mineStockpile.pop();
                        System.out.println("Transfer completed");
                    }
                    break;
                case 5:
                    manageWarehouse();
                    break;
                case 6:
                    stockpileMenu();
                    break;
                default:
                    System.out.println("Wrong request!");
                    break;
            }
        }



    }

    @Override
    public boolean chainCrafting() {
        return false;
    }

    public String warehouseMenu(){
        return "Possible actions: \n" +
                "0 - exit the management \n" +
                "1 - display contents of the warehouse \n" +
                "2 - select next resource \n" +
                "3 - select previous resource \n" +
                "4 - move resource to the stockpile \n" +
                "5 - manage stockpile \n" +
                "6 - display possible actions";
    }

    public void stockpileMenu(){
        System.out.println("Possible actions: \n" +
                "0 - leave stockpile \n" +
                "1 - display contents of the stockpile \n" +
                "2 - show what's on top \n" +
                "3 - discard the resource from top \n" +
                "4 - move resource on top to warehouse \n" +
                "5 - manage warehouse \n" +
                "6 - display possible actions");
    }

}
