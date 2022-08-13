package workspare.sparktesst.src.main.java.executor;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileThread extends Thread{

    private String fileName;

    private String filePath;

    public CreateFileThread(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public void run() {

        File file = new File(fileName + filePath);
        try {
            boolean newFile = file.createNewFile();
            FileWriter fileWriter = new FileWriter(fileName + filePath);
            final BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("hello\n");
            bw.write("world\n");
            bw.close();
            System.out.println("create file " + fileName + filePath);
        } catch (IOException e) {
            System.out.println("create file error ");
        }
    }
}