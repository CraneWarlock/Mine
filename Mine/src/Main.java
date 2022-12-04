public class Main {
    public static void main(String[] args) {

        Mine mine = new Mine("First mine");

        for(int i = 0; i<15; i++){
            mine.goMining();
        }

        mine.manageWarehouse();
        mine.passTime();

        for(int i = 0; i<15; i++){
            mine.goMining();
        }
        mine.manageWarehouse();

        

    }
}