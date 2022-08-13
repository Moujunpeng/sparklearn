package workspare.sparktesst.src.main.java.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {

    private static final int threadNums = 100;

    private static final ExecutorService executor = Executors.newFixedThreadPool(threadNums);

    private static int threadnum = 0;

    public static void main(String[] args) {

       for(int i = 0;i < 1000;i++){
           threadnum = i;
           Runnable task = new Runnable() {
               @Override
               public void run() {
                   System.out.println("thread num is " + 0);
               }
           };
           System.out.println("thread is " + i);
           executor.execute(task);

       }

        System.out.println("over");

        if(threadnum == 999){
            System.out.println("shut down executor thread pool");
            executor.shutdown();
        }

    }

}
