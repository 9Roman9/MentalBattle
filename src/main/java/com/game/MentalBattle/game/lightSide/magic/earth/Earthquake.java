package com.game.MentalBattle.game.lightSide.magic.earth;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("earthQuake")
public class Earthquake implements MagicSkill {
    @Value("${priceEarthquake}")
    private int price;
    @Value("${descriptionEarthquake}")
    private String description;
    private final Impact impact = Impact.STUN;
    @Value("${impactVolumeEarthquake}")
    private int impactVolume;
    @Value("$percentageEarthquake")
    private int percentage;
    @Value("${renewPeriodEarthquake}")
    private int renewPeriod;
    @Value("${additionalStrikeEarthquake}")
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
        return percentage;
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
