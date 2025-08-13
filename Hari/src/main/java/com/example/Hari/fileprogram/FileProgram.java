package com.example.Hari.fileprogram;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileProgram {
    public static void main(String[] args) throws IOException {
        String filePath = "/home/hariharasudhan/Documents/GIT/HariharaSudhan-Git/Hari/src/main/resources/file/";

        /**
         * createFile(filePath, "newFile.sql");
         *         createTempFile(filePath);
         *         renameFile(filePath, "oooo.sql", "hari1.sql");
         *         writeFile("/home/hariharasudhan/Documents/GIT/HariharaSudhan-Git/Hari/src/main/resources/file/newFile.sql");
         *         comparePath(filePath, "newFile.xml");
         *         compyOneFileToAnother(filePath, "hari1.sql", "Hari.sql");
         *         matchThePattern(filePath,"test.mysql");
         *         String zipFile = "java.zip";
         *         readTheDataFromZipFile(filePath, zipFile);
         */
    }


    private static void createFile(String fpath, String fname) throws IOException {
        String fileName = fpath + fname;
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("File Already present");
        } else {
            file.createNewFile();
            file.setWritable(true);
            file.setReadable(true);
            System.out.println("File Created");
        }
    }

    private static void createTempFile(String path) throws IOException {
        File temFile = new File(path);
        File.createTempFile("temp", ".sql", temFile);
    }

    private static void writeFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.canWrite()) {

            String sql = "SELECT PEACE FROM FILE";

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(sql.getBytes());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = fileInputStream.readAllBytes();
            String content = new String(data);
            System.out.println(content);

/*

            Path filePath = Path.of(fileName);
            Files.writeString(filePath,sql);
            System.out.println("WRITTEN");
            String content =  Files.readString(filePath);
            System.out.println(content);

            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(sql);
            System.out.println(fileReader.read());


 */
        } else {
            System.out.println("you can not write");
        }


    }


    public static void renameFile(String path, String oldFileName, String newFileName) {
        File file = new File(path + oldFileName);
        if (file.exists()) {
            File newFile = new File(path, newFileName);
            file.renameTo(newFile);
        }

    }


    public void setReadOnly(String path, String fileName) {
        File file = new File(path + fileName);
        file.setReadable(true);
        file.setWritable(false);
    }


    public static void comparePath(String path, String fileName) {
        File f1 = new File(path + fileName);
        File f2 = new File(path + fileName);
        if (f1.compareTo(f2) == 0) {
            System.out.println("Both are Same file");
        } else {
            System.out.println("path are not same ");
        }
    }


    public static void compyOneFileToAnother(String path, String srcFile, String destFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path + srcFile);
        FileOutputStream fileOutputStream = new
                FileOutputStream(path + destFile);
        byte[] f = fileInputStream.readAllBytes();
        fileOutputStream.write(f);
    }


    public static void matchThePattern(String path, String fileName) throws IOException {

        File file = new File(path + "test.sql");
        file.createNewFile();

        Path pathName = Path.of(path + "test.sql");

        Files.writeString(pathName, "SELECT * FROM HOME , SELECT CR17 FROM TV");

        Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "test.sql"));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                int start = matcher.start(0);
                int end = matcher.end(0);
                System.out.println(
                        line.substring(start, end));
            }

        }
    }


    public static void readTheDataFromZipFile(String pathName, String fileName) throws IOException {

        try (FileInputStream fis = new FileInputStream(pathName + fileName);
             ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis))) {

            ZipEntry entry;
            byte[] buffer = new byte[1024]; // temp storage

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("📄 File: " + entry.getName());
                // Read and print the file content
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    String content = new String(buffer, 0, len);
                    System.out.print(content);
                }
                System.out.println("\n---------------------------");
            }
        }
    }


}














