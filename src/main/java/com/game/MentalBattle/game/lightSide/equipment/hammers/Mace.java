package com.game.MentalBattle.game.lightSide.equipment.hammers;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("maceBean")
public class Mace implements Weapon {
    @Value("${priceMace}")
    private int price;
    @Value("${damageMace}")
    private int damage;
    @Value("${descriptionMace}")
    private String description;
    @Value("${descriptionEngMace}")
    private String descriptionEng;
    private final Impact impact = Impact.STUN;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDescriptionEng() {
        return descriptionEng;
    }

    @Override
    public int getRealDamage() {
        int deviation = new Random().nextInt(damage/10+1);
        return damage - deviation;
    }
}
