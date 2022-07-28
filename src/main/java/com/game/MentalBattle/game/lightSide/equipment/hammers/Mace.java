package com.game.MentalBattle.game.lightSide.equipment.hammers;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("maceBean")
public class Mace implements Weapon {
    @Value("${priceMace}")
    private int price;
    @Value("${damageMace}")
    private int damage;
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
}
