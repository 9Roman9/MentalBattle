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
    private final Impact impact = Impact.CRUCIAL;
    @Value("${impactVolumeLiana}")
    private int impactVolume;
    @Value("${durationLiana}")
    private int duration;
    @Value("${renewPeriodLiana}")
    private int renewPeriod;
    @Value("${renewPeriodLiana}")
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
    public int getDuration() { return duration; }

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}
