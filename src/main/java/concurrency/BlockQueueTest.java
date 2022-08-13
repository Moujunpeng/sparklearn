package workspare.sparktesst.src.main.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueueTest {


    public static ArrayBlockingQueue<String> stringArrayBlockingQueue = null;

    public static void main(String[] args) throws InterruptedException {


        stringArrayBlockingQueue = new ArrayBlockingQueue<String>(10);

        Thread thread1 = new Thread(){
            int i = 0;
            @Override
            public void run() {
                while(true){

                    try {
                        stringArrayBlockingQueue.put("input " + i);
                        System.out.println("input " + i);
                        i++;
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(){
            int i = 0;
            @Override
            public void run() {
                while(true){

                    try {
                        String take = stringArrayBlockingQueue.take();
                        System.out.println("take is " + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        thread2.start();

    }


}