package com.game.MentalBattle.game.lightSide.equipment.hammers;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("thunderHammerBean")
public class ThunderHammer implements Weapon {
    @Value("${priceThunderHammer}")
    private int price;
    @Value("${damageThunderHammer}")
    private int damage;
    @Value("${descriptionThunderHammer}")
    private String description;
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
}
