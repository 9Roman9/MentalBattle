package com.game.MentalBattle.game.lightSide.magic.earth;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("leafProtectionBean")
public class LeafProtection implements MagicSkill {
    @Value("${priceLeafProtection}")
    private int price;
    @Value("${descriptionLeafProtection}")
    private String description;
    private final Impact impact = Impact.DEFENCE_PLUS;
    @Value("${impactVolumeLeafProtection}")
    private int impactVolume;
    @Value("${durationLeafProtection}")
    private int duration;
    @Value("${renewPeriodLeafProtection}")
    private int renewPeriod;
    @Value("${renewPeriodLeafProtection}")
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
        return duration;
    }

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}
