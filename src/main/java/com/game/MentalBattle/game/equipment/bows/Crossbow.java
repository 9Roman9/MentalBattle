package com.game.MentalBattle.game.equipment.bows;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("crossbowBean")
public class Crossbow implements Weapon {
    @Value("${priceCrossbow}")
    private int price;
    @Value("${damageCrossbow}")
    private int damage;
    private final Impact impact = Impact.CRUCIAL;
    @Value("${impactVolumeCrossbow}")
    private int impactVolume;

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
    public int getImpactVolume() {
        return impactVolume;
    }
}
