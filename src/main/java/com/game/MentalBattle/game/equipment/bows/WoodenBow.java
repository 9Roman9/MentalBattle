package com.game.MentalBattle.game.equipment.bows;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("woodenBowBean")
public class WoodenBow implements Weapon {
    @Value("${priceWoodenBow}")
    private int price;
    @Value("${damageWoodenBow}")
    private int damage;
    private final Impact impact = Impact.CRUCIAL;
    @Value("${impactVolumeWoodenBow}")
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
