package com.game.MentalBattle.game.magic.water;

import com.game.MentalBattle.game.magic.Impact;
import com.game.MentalBattle.game.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("iceSpikeBean")
public class IceSpike implements MagicSkill {
    @Value("${priceIceSpike}")
    private int price;
    @Value("${descriptionIceSpike}")
    private String description;
    private Impact impact = Impact.DEFENCE_MINUS;
    @Value("${impactVolumeIceSpike}")
    private int impactVolume;
    @Value("${renewPeriodIceSpike}")
    private int renewPeriod;
    @Value("${additionalStrikeIceSpike}")
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
