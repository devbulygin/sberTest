package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void testFinderTrue() throws Exception {
        String pathToFile = "./src/main/resources/name_java.xlsx";
        String series = "ОЕК";
        String regNumber = "052";
        String region = "797";
        String recievedResponce1 = Finder.searchNumber(pathToFile, series, regNumber, region);
        String expected1 = "номер найден";

        assertThat(recievedResponce1).isEqualTo(expected1);
    }

    @Test
    void testFinderFalse() throws Exception {
        String pathToFile = "./src/main/resources/name_java.xlsx";
        String series = "AAA";
        String regNumber = "777";
        String region = "77";
        String recievedResponce2 = Finder.searchNumber(pathToFile, series, regNumber, region);
        String expected2 = "номер не найден";
        assertThat(recievedResponce2).isEqualTo(expected2);
    }

}