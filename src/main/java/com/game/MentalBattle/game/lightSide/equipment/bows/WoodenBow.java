package com.game.MentalBattle.game.lightSide.equipment.bows;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("woodenBowBean")
public class WoodenBow implements Weapon {
    @Value("${priceWoodenBow}")
    private int price;
    @Value("${damageWoodenBow}")
    private int damage;
    @Value("${descriptionWoodenBow}")
    private String description;
    @Value("${descriptionEngWoodenBow}")
    private String descriptionEng;
    private final Impact impact = Impact.CRUCIAL;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRealDamage() {
        int deviation = new Random().nextInt(damage/10+1);
        return damage - deviation;
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
}
