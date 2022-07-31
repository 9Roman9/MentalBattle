package com.game.MentalBattle.game.lightSide.equipment.swords;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ancientSwordBean")
public class AncientSword implements Weapon {
    @Value("${priceAncientSword}")
    private int price;
    @Value("${damageAncientSword}")
    private int damage;
    @Value("${descriptionAncientSword}")
    private String description;
    private final Impact impact = Impact.DAMAGE;

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
