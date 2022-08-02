package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("tornadoBean")
public class Tornado implements MagicSkill {
    @Value("${priceTornado}")
    private int price;
    @Value("${descriptionTornado}")
    private String description;
    @Value("${descriptionEngTornado}")
    private String descriptionEng;
    @Value("${damageTornado}")
    private int damage;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeTornado}")
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
        int deviation = new Random().nextInt(damage/10);
        return damage - deviation;
    }

    @Override
    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }
}
