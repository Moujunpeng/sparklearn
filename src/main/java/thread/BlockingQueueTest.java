package workspare.sparktesst.src.main.java.thread;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import workspare.sparktesst.src.main.java.thread.*;

public class BlockingQueueTest {

    public static void main(String[] args) {

        LinkedBlockingQueue<File> filesQueue = new LinkedBlockingQueue<File>(1000);

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        File input = new File("D:\\mjp1\\programworkspace\\executor_test");

        new Thread(new FileCrawler(filesQueue,fileFilter,input)).start();

        for(int i = 0;i < 1;i++){
            new Thread(new Indexer(filesQueue)).start();
        }

    }

}

class FileCrawler implements Runnable{

    private BlockingQueue<File> filesQueue;

    private FileFilter fileFilter;

    private File root;

    private final HashMap<String,String> fileindexflag = new HashMap<>();

    public FileCrawler(BlockingQueue<File> filesQueue, FileFilter fileFilter, File root) {
        this.filesQueue = filesQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] files = root.listFiles(fileFilter);

        if(files != null){
            Thread.sleep(5000);
            while(true){
                for(File file: files){
                    if(file.isDirectory()){
                        crawl(file);
                    }else if(fileindexflag.get(file.getName()) == null){
                        System.out.println("file name is " + file.getName());
                        fileindexflag.put(file.getName(),file.getAbsolutePath());
                        filesQueue.put(file);
                    }
                }
            }

        }

    }

}

class Indexer implements Runnable{

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {

        try{
            while (true){
                Thread.sleep(10000);
                // 打印文件的名称
                System.out.println("input queue size is " + queue.size());
                // 队列都是先进先出，因此take方法是取出队列中index为0的元素，也就是头部第一个元素
                System.out.println("index fileName is " + queue.take().getName());
                System.out.println("new queue size is " + queue.size());
            }

        }catch (InterruptedException e){

            Thread.currentThread().interrupt();
        }

    }
}