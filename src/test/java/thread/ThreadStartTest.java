package thread;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadStartTest extends Thread{

    @Test
    public void testProcessors(){

        System.out.println("process is "  + Runtime.getRuntime().availableProcessors());

    }

    @Test
    public void testConcurrentHashMap(){

        ConcurrentHashMap<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();

        System.out.println("process is "  + Runtime.getRuntime().availableProcessors());

    }

}
