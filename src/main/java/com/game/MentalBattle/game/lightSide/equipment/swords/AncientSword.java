package com.game.MentalBattle.game.lightSide.equipment.swords;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.equipment.Weapon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("ancientSwordBean")
public class AncientSword implements Weapon {
    @Value("${priceAncientSword}")
    private int price;
    @Value("${damageAncientSword}")
    private int damage;
    @Value("${descriptionAncientSword}")
    private String description;
    @Value("${descriptionEngAncientSword}")
    private String descriptionEng;
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

    @Override
    public String getDescriptionEng() {
        return descriptionEng;
    }

    @Override
    public int getRealDamage() {
        int deviation = new Random().nextInt(damage/10+1);
        return damage - deviation;
    }
}
