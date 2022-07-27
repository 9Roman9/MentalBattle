package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("shotBean")
public class Shot implements DarkSkill {
    @Value("${impactValueShot}")
    private int impactValue;
    @Value("${nameShot}")
    private String name;

    @Override
    public int getImpactValue() {
        return impactValue;
    }

    @Override
    public String getName() {
        return name;
    }
}
