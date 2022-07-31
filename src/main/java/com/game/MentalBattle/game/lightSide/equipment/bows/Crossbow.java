package com.game.MentalBattle.game.lightSide.equipment.bows;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("crossbowBean")
public class Crossbow implements Weapon {
    @Value("${priceCrossbow}")
    private int price;
    @Value("${damageCrossbow}")
    private int damage;
    @Value("${descriptionCrossbow}")
    private String description;
    private final Impact impact = Impact.CRUCIAL;

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
}
