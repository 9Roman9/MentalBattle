package com.game.MentalBattle.game.equipment.hammers;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("maceBean")
public class Mace implements Weapon {
    @Value("${priceMace}")
    private int price;
    @Value("${damageMace}")
    private int damage;
    private final Impact impact = Impact.ATTACK_PLUS;
    @Value("${impactVolumeMace}")
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
