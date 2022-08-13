package workspare.sparktesst.src.main.java.executor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 这个executor的目的是通过多线程创建多个文件
 * 每个线程启动一个java -cp的进程，然后在文件夹中创建txt文件
 */
public class ExecutorCreateMutiFile {


    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        String path = "D:\\mjp1\\programworkspace\\executor_test";

        for(int i = 0;i < 100;i++){
            //new CreateFileThread(path,"file_" + i).run();
            //executorService.submit(new CreateFileThread(path,"file_" + i));

            executorService.execute(new CreateFileByCreateProcess(path,"file_" + i));

        }
        executorService.shutdown();
    }

    static class CreateFileByCreateProcess extends Thread{

        private String fileName;

        private String filePath;

        public CreateFileByCreateProcess(String filePath, String fileName) {
            this.fileName = fileName;
            this.filePath = filePath;
        }

        @Override
        public void run() {

            // java -cp D:\program\processcreate\target\processcreate-1.0-SNAPSHOT.jar com.mjp.FileCreate D:\\mjp1\\programworkspace\\executor\\ 000001111
            ProcessBuilder pb = new ProcessBuilder();
            try {
                ArrayList<String> command = new ArrayList<>();
//                command.add("cmd");
                command.add("D:\\program\\java8\\bin\\java.exe");
                command.add("-cp");
                command.add("D:\\program\\processcreate\\target\\processcreate-1.0-SNAPSHOT.jar");
                command.add("com.mjp.FileCreate");
                command.add(filePath);
                command.add(fileName);
                pb.command(command);
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class CreateFileThread extends Thread{

        private String fileName;

        private String filePath;

        public CreateFileThread(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }

        @Override
        public void run() {

            //File file = new File(fileName + File.separator + filePath);
            try {
                FileWriter fileWriter = new FileWriter(fileName + File.separator + filePath);
                final BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write("hello\n");
                bw.write("world\n");
                bw.close();
            } catch (IOException e) {
                System.out.println("create file error ");
            }
        }
    }

}