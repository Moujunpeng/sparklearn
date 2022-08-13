package workspare.sparktesst.src.main.java;

class ThreadExtend extends Thread{

    private String threadName;

    @Override
    public void run() {
        int i = 0;
        while (i < 1000){
            System.out.println(threadName + ": " + i);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ThreadExtend(String name, String threadName) {
        super(name);
        this.threadName = threadName;
    }
}


public class ThreadTest{

    public static void main(String[] args){
        ThreadExtend th1 = new ThreadExtend("num1", "num1");
        ThreadExtend th2 = new ThreadExtend("num2", "num2");
        th1.start();
        th2.start();
    }
}
