package com.game.MentalBattle.game.lightSide.equipment.bows;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;

public class LegendaryBow implements Weapon {
    @Value("${priceLegendaryBow}")
    private int price;
    @Value("${damageLegendaryBow}")
    private int damage;
    private final Impact impact = Impact.CRUCIAL;
    @Value("${impactVolumeLegendaryBow}")
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
