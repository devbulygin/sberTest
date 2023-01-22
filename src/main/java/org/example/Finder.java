package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;


import static java.util.stream.Collectors.counting;

public class Finder {
    final static int FIRST_NUMBER_ROW = 1;


    public static String searchNumber(String pathToFile, String requiredSeries, String requiredRegNumber, String requiredRegion)
            throws Exception {

        List<Map<String, String>> numbers = readXlsx(getXlsx(pathToFile));

        Long result = numbers.stream()
                .filter(series -> Objects.equals(series.get("series"), requiredSeries))
                .filter(regNumber -> Objects.equals(regNumber.get("regNumber"), requiredRegNumber))
                .filter(region -> Objects.equals(region.get("region"), requiredRegion))
                .collect(counting());

        return (result != 0) ? "номер найден" : "номер не найден";
    }

    public static File getXlsx (String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + pathToFile + "' does not exist");
        }

        return path.toFile();
    }
    public static List<Map<String, String>> readXlsx(File fileXlsx) throws IOException, InvalidFormatException {
        List<Map<String, String>> result = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(fileXlsx);

        XSSFSheet sheet = wb.getSheetAt(0);

        int rowStart = FIRST_NUMBER_ROW;
        int rowEnd = sheet.getLastRowNum();

        for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String number = cell.getStringCellValue();
                result.add(numberToMap(number));
            }
        }
        return result;
    }

    public static Map<String, String> numberToMap(String number) {
        String region = number.substring(6);
        String series = number.substring(0, 1) + number.substring(4, 6);
        String regNumber = number.substring(1, 4);

        return Map.of("series", series, "regNumber", regNumber, "region", region);

    }
}
