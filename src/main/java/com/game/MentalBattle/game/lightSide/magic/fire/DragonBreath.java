package com.game.MentalBattle.game.lightSide.magic.fire;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("dragonBreathBean")
public class DragonBreath implements MagicSkill {
    @Value("${priceDragonBreath}")
    private int price;
    @Value("${descriptionDragonBreath}")
    private String description;
    @Value("${damageDragonBreath}")
    private int damage;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeDragonBreath}")
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
