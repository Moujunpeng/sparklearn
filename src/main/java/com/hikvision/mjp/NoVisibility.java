package workspare.sparktesst.src.main.java.com.hikvision.mjp;

public class NoVisibility {

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread{

        @Override
        public void run() {
            while(!ready){
                System.out.println("yield");
                Thread.yield();
            }
            System.out.println("number is " + number);
        }
    }

    public static void main(String[] args) {

        new ReaderThread().start();

        for(int i = 0;i < 100;i++){
            //System.out.println("000000000");
        }

        ready = true;

        for(int i = 0;i < 1000000000;i++){
            //System.out.println("000000000");
        }

        number = 42;
    }

}