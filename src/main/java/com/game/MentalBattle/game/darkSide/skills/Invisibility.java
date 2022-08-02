package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("invisibilityBean")
public class Invisibility implements DarkSkill{
    @Value("${nameInvisibility}")
    private String name;

    @Override
    public int getImpactValue() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int calculateRealDamage(){
        return 0;
    }
}
