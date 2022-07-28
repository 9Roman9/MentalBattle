package com.game.MentalBattle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerGame {
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
