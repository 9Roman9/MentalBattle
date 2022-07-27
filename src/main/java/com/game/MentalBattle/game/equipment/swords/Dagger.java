package com.game.MentalBattle.game.equipment.swords;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("daggerBean")
public class Dagger implements Weapon {
    @Value("${priceDagger}")
    private int price;
    @Value("${damageDagger}")
    private int damage;
    private final Impact impact = Impact.ATTACK_PLUS;
    @Value("${impactVolumeDagger}")
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
