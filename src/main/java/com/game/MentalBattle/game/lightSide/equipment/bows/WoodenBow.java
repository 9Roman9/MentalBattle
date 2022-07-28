package com.game.MentalBattle.game.lightSide.equipment.bows;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("woodenBowBean")
public class WoodenBow implements Weapon {
    @Value("${priceWoodenBow}")
    private int price;
    @Value("${damageWoodenBow}")
    private int damage;
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
}
