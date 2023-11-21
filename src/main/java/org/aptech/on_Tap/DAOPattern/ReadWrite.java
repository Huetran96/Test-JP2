package org.aptech.on_Tap.DAOPattern;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReadWrite {
    private static final DateFormat dateFormat =new SimpleDateFormat("yyyy-mm-dd");
    private static final String header ="ID|Name|Address|Birthday";

    public static void main(String[] args) {
        readerDemo();
    }
    public static void lab1(){
        File file = new File("DemoInput.txt"); //
        if (file.exists()){ // xem file co ton tai khong
            System.out.println("File exists.");
        }else {
            try {
                file.createNewFile(); // tao file moi
                System.out.println("File: "+file.getAbsoluteFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void lab2(){
        File file2 = new File("DemoInput.txt");
        file2.delete(); // xoa file
    }
    static void writerDemo(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("DemoInput.txt",true);
            bw = new BufferedWriter(fw);
            bw.write("Hello, welcome to Read/Writer lession");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    static void readerDemo(){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("DemoInput.txt");
            br = new BufferedReader(fr);
            int ch;
            while ((ch= br.read()) != -1){
                System.out.print((char) ch);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
