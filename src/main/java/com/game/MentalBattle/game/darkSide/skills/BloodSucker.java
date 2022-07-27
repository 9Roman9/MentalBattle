package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("bloodSuckerBean")
public class BloodSucker implements DarkSkill{
    @Value("${impactValueBloodSucker}")
    private int impactValue;
    @Value("${nameBloodSucker}")
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
