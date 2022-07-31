package com.game.MentalBattle.game.lightSide.magic;

import com.game.MentalBattle.game.lightSide.Impact;

import java.io.UnsupportedEncodingException;

public interface MagicSkill {
    int getPrice();
    String getDescription();
    int getDamage();
    Impact getImpact();
    int getImpactVolume();
}
