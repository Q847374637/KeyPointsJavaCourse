package core;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Data {
    static Path inputPath = Paths.get("C:\\fileInput");
    static Path outputPath = Paths.get("C:\\fileOutput");
    static String outputFileName = "\\output.txt";
    static List<Path> fileList = new ArrayList<Path>();
    public  Data () throws Exception {
        setInputPath(inputPath);
        setOutputPath(outputPath);
    }
    public static Path getInputPath() {
        return inputPath;
    }

    public static void setInputPath(Path inputPath) throws Exception {
        Data.inputPath = inputPath;
        checkPathExistence(inputPath);
    }
    public static Path getOutputPath() {
        return outputPath;
    }

    public static void setOutputPath(Path outputPath) throws Exception {
        Data.outputPath = outputPath;
        checkPathExistence(outputPath);
    }
    public static List<Path> getFileList() {
        return fileList;
    }

    public static void setFileList() throws Exception {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(getInputPath())) {
            for (Path file : stream) {
                fileList.add(file);
                System.out.println(file.getFileName());
            }
        }
        if(fileList.isEmpty())
            throw new Exception("There are no files in this directory!");
    }
    public static String getOutputFileName() {
        return outputFileName;
    }
    private static void checkPathExistence(Path path) throws Exception {
        if (!Files.exists(path))
        {
            Files.createDirectory(path);
            System.out.println(String.format("Directory %s was created because it was not found", path.toString()));
        }
    }
    @Override
    public String toString() {
        return String.format("--Input & output:--\n%s\n%s",inputPath, outputPath);
    }
}
