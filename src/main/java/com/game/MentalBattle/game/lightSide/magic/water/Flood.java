package com.game.MentalBattle.game.lightSide.magic.water;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("floodBean")
public class Flood implements MagicSkill {
    @Value("${priceFlood}")
    private int price;
    @Value("${descriptionFlood}")
    private String description;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeFlood}")
    private int impactVolume;
    @Value("${renewPeriodFlood}")
    private int renewPeriod;
    @Value("${additionalStrikeFlood}")
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
