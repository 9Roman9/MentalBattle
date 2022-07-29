package com.game.MentalBattle;

import com.game.MentalBattle.game.Task;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/taskTest.csv",delimiter = '|',lineSeparator = "&")
    public void findOutputExampleTest(String condition,int answer,int random){
        Task task = new Task();
        Map<String,Integer> expected = new LinkedHashMap<>();
        expected.put(condition,answer);
        task.createExamples();
        Map<String,Integer> actual = task.findOutputExample(random);
        assertEquals(expected,actual);
    }
}