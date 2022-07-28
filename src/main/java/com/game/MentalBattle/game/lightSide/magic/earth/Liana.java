package com.game.MentalBattle.game.lightSide.magic.earth;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("lianaBean")
public class Liana implements MagicSkill {
    @Value("${priceLiana}")
    private int price;
    @Value("${descriptionLiana}")
    private String description;
    @Value("${damageLiana}")
    private int damage;
    private final Impact impact = Impact.CRUCIAL;
    @Value("${impactVolumeLiana}")
    private int impactVolume;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public int getImpactVolume() { return impactVolume; }

    @Override
    public int getDamage() {
        return damage;
    }
}
