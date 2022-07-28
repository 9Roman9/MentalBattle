package com.game.MentalBattle.game.lightSide.magic.fire;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("meteorRainBean")
public class MeteorRain implements MagicSkill {
    @Value("${priceMeteorRain}")
    private int price;
    @Value("${descriptionMeteorRain}")
    private String description;
    @Value("${damageMeteorRain}")
    private int damage;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeMeteorRain}")
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
