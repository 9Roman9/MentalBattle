package com.game.MentalBattle.game.lightSide.equipment.hammers;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("blacksmithHammerBean")
public class BlacksmithHammer implements Weapon {
    @Value("${priceBlacksmithHammer}")
    private int price;
    @Value("${damageBlacksmithHammer}")
    private int damage;
    @Value("${descriptionBlacksmithHammer}")
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
