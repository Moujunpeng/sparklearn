//package workspare.sparktesst.src.main.java.com.hikvision.mjp;
//
//public class SynThread implements Runnable {
//
//    private Sequence count;
//
//    public SynThread(Sequence count1) {
//        this.count = count1;
//    }
//
//    public static void main(String[] args) {
//
//        Sequence sequence = new Sequence(0);
//
//        for(int i = 0;i < 100;i++){
//            new Thread(new SynThread(sequence)).start();
//        }
//
//    }
//
//
//    @Override
//    public void run() {
//        System.out.println("thread name is " + Thread.currentThread().getName());
//        System.out.println( Thread.currentThread().getName() + " " + count);
//    }
//}
//
//class Sequence{
//
//    private int value;
//
//    public Sequence(int value) {
//        this.value = value;
//    }
//
//    public int getValue() {
//        return value++;
//    }
//}

