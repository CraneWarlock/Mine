import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
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
        this.workingHours=0;
        passedDays++;
    }

    @Override
    public void manageWarehouse() {
        System.out.println("Warehouse contains: ");
        for(Resources res : mineWarehouse){
            System.out.println(res.name());
        }
    }

    @Override
    public void manageStockpile() {

    }

    @Override
    public boolean chainCrafting() {
        return false;
    }

}
