package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "App",
        mixinStandardHelpOptions = true,
        description = "поиск автомобильных номеров из excel файла",
        version = "1.0"
)
final class App implements Runnable{

    @Parameters(index = "0", description = "путь к xlsx документу")
    private static String pathToFile;

    @Parameters(index = "1", description = "серия")
    private static String series;
    @Parameters(index = "2", description = "регистрационный номер")
    private static String regNumber;
    @Parameters(index = "3", description = "регион")
    private static String region;



    public static String getPathToFile() {
        return pathToFile;
    }

    public static String getSeries() {
        return series;
    }

    public static String getRegNumber() {
        return regNumber;
    }

    public static String getRegion() {
        return region;
    }
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }
    @Override
    public void run() {
        String searchResults = "";
        try {
            searchResults = Finder.searchNumber(pathToFile, series, regNumber, region);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(searchResults);
    }
}