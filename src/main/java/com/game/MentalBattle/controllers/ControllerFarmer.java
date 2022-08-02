package com.game.MentalBattle.controllers;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.Map;

@Controller
@Component("controllerFarmerBean")
public class ControllerFarmer {
    @Autowired
    Task task;
    AnnotationConfigApplicationContext context;
    Map<String, Integer> readyTask;
    Iterator<String> riddleIterator;
    Iterator<Integer> answerIterator;
    String riddle;
    int answer;
    int times = 0;
    int money;
    int correctAnswers;
    int wrongAnswers;

    public int getMoney() {
        return money;
    }

    @GetMapping("/")
    public String farmer1(){
        context = new AnnotationConfigApplicationContext(Config.class);
        return "/farmer/farmer1";
    }

    @GetMapping("/farmer2")
    public String farmer2(){
        return "/farmer/farmer2";
    }

    @GetMapping("/farmer3")
    public String farmer3(){
        return "/farmer/farmer3";
    }

    @GetMapping("/test1")
    public String test1(Model model){
        task.createExamples();
        times++;
        task.fixStart();
        readyTask = task.getExample();
        riddleIterator = readyTask.keySet().iterator();
        answerIterator = readyTask.values().iterator();
        riddle = riddleIterator.next();
        String timeString = "test".concat(String.valueOf(++times));
        model.addAttribute("riddle",riddle);
        model.addAttribute("times",timeString);
        return "/farmer/test";
    }

    @RequestMapping(value = {"/test2","/test3","/test4","/test5","/test6",
            "/test7","/test8","/test9","/test10"}, method = RequestMethod.POST)
    public String test(Model model, String answer){
        task.fixFinish();
        this.answer = answerIterator.next();
        try {
            task.calculateScore(this.answer,Integer.parseInt(answer));
        } catch (Exception e) {
            task.calculateScore(this.answer,0);
        }
        task.fixStart();
        readyTask = task.getExample();
        riddleIterator = readyTask.keySet().iterator();
        answerIterator = readyTask.values().iterator();
        riddle = riddleIterator.next();
        correctAnswers = task.getCorrectAnswers();
        wrongAnswers = task.getWrongAnswers();
        String timeString = "test".concat(String.valueOf(++times));
        model.addAttribute("riddle",riddle);
        model.addAttribute("correctAnswers",correctAnswers);
        model.addAttribute("wrongAnswers",wrongAnswers);
        model.addAttribute("times",timeString);
        return "/farmer/test";
    }

    @RequestMapping(value = "/test11", method = RequestMethod.POST)
    public String finishTest(Model model, String answer){
        task.fixFinish();
        this.answer = answerIterator.next();
        try {
            task.calculateScore(this.answer,Integer.parseInt(answer));
        } catch (Exception e) {
            task.calculateScore(this.answer,0);
        }
        money = task.getMoney();
        correctAnswers = task.getCorrectAnswers();
        wrongAnswers = task.getWrongAnswers();
        model.addAttribute("money",money);
        model.addAttribute("correctAnswers",correctAnswers);
        model.addAttribute("wrongAnswers",wrongAnswers);
        return "/farmer/finishTest";
    }
}
