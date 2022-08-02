package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("mysticFogBean")
public class MysticFog implements MagicSkill {
    @Value("${priceMysticFog}")
    private int price;
    @Value("${descriptionMysticFog}")
    private String description;
    @Value("${descriptionEngMysticFog}")
    private String descriptionEng;
    @Value("${damageMysticFog}")
    private int damage;
    private final Impact impact = Impact.HEAL;
    @Value("${impactVolumeMysticFog}")
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
    public int calculateRealDamage(){
        int deviation;
        if (damage>0) deviation = new Random().nextInt(damage/10);
        else deviation = 0;
        return damage - deviation;
    }

    @Override
    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }
}
