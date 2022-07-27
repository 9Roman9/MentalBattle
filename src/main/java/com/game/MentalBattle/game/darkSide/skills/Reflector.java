package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("reflectorBean")
public class Reflector implements DarkSkill{
    @Value("${impactValueReflector}")
    private int impactValue;
    @Value("${nameReflector}")
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
