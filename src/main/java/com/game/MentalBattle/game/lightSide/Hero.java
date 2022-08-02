package com.game.MentalBattle.game.lightSide;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("heroBean")
public class Hero {
    @Value("${heroLife}")
    private int life;
    @Value("${defaultDefence}")
    private int defaultDefence;

    public int getLife() {
        return life;
    }

    public int getDefaultDefence() {
        return defaultDefence;
    }
}
