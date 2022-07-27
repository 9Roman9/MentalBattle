package com.game.MentalBattle.game.magic;

import com.game.MentalBattle.game.Impact;

public interface MagicSkill {
    int getPrice();
    String getDescription();
    Impact getImpact();
    int getRenewPeriod();
    int getImpactVolume();
    int getPercentage();
    int getDuration();
    boolean isAdditionalStrike();
}
