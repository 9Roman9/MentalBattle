package com.game.MentalBattle.game.lightSide.magic.water;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("healingSpringBean")
public class HealingSpring implements MagicSkill {
    @Value("${priceMeteorRain}")
    private int price;
    @Value("${descriptionMeteorRain}")
    private String description;
    private final Impact impact = Impact.HEAL;
    @Value("${impactVolumeMeteorRain}")
    private int impactVolume;
    @Value("${renewPeriodMeteorRain}")
    private int renewPeriod;
    @Value("${additionalStrikeHealingSpring}")
    private boolean additionalStrike;

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
    public int getRenewPeriod() { return renewPeriod; }

    @Override
    public int getImpactVolume() { return impactVolume; }

    @Override
    public int getPercentage() {
        return 0;
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}
