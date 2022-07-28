package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mysticFogBean")
public class MysticFog implements MagicSkill {
    @Value("${priceMysticFog}")
    private int price;
    @Value("${descriptionMysticFog}")
    private String description;
    @Value("${damageMysticFog}")
    private int damage;
    private final Impact impact = Impact.HEAL;
    @Value("${impactVolumeMysticFog}")
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
