package com.game.MentalBattle.game.equipment.hammers;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("thunderHammerBean")
public class ThunderHammer implements Weapon {
    @Value("${priceThunderHammer}")
    private int price;
    @Value("${damageThunderHammer}")
    private int damage;
    private final Impact impact = Impact.ATTACK_PLUS;
    @Value("${impactVolumeThunderHammer}")
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
