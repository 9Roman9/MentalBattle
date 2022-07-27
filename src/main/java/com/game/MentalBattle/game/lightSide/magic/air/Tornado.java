package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;

public class Tornado implements MagicSkill {
    @Value("${priceTornado}")
    private int price;
    @Value("${descriptionTornado}")
    private String description;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeTornado}")
    private int impactVolume;
    @Value("${renewPeriodTornado}")
    private int renewPeriod;
    @Value("${renewPeriodTornado}")
    boolean additionalStrike;

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
    public int getDuration() { return 0; }

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}
