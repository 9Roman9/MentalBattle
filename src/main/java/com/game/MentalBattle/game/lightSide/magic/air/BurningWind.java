package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("burningWindBean")
public class BurningWind implements MagicSkill {
    @Value("${priceBurningWind}")
    private int price;
    @Value("${descriptionBurningWind}")
    private String description;
    @Value("${descriptionEngBurningWind}")
    private String descriptionEng;
    private final Impact impact = Impact.ATTACK_MINUS;
    @Value("${damageBurningWind}")
    private int damage;
    @Value("${impactVolumeBurningWind}")
    private int impactVolume;
    @Value("${recharge}")
    private int recharge;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public int getImpactVolume() { return impactVolume; }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getDescriptionEng() {
        return descriptionEng;
    }

    @Override
    public int getRecharge() {
        return recharge;
    }

    @Override
    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    @Override
    public int calculateRealDamage(){
        int deviation = new Random().nextInt(damage/10);
        return damage - deviation;
    }
}
