package com.game.MentalBattle.game.equipment.swords;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("katanaBean")
public class Katana implements Weapon {
    @Value("${priceKatana}")
    private int price;
    @Value("${damageKatana}")
    private int damage;
    private final Impact impact = Impact.ATTACK_PLUS;
    @Value("${impactVolumeKatana}")
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
