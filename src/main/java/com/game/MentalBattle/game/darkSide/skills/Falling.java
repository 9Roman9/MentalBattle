package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("fallingBean")
public class Falling implements DarkSkill{
    @Value("${impactValueFalling}")
    private int impactValue;
    @Value("${nameFalling}")
    private String name;

    @Override
    public int getImpactValue() {
        return impactValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int calculateRealDamage(){
        int deviation = new Random().nextInt(impactValue/10);
        return impactValue - deviation;
    }
}
