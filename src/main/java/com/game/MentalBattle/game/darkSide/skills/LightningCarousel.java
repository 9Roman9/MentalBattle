package com.game.MentalBattle.game.darkSide.skills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("lightningCarouselBean")
public class LightningCarousel implements DarkSkill{
    @Value("${impactValueLightningCarousel}")
    private int impactValue;
    @Value("${nameLightningCarousel}")
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
