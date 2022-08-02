package com.game.MentalBattle.game.lightSide.magic;

import com.game.MentalBattle.game.lightSide.Impact;

import java.io.UnsupportedEncodingException;

public interface MagicSkill {
    int getPrice();
    String getDescription();
    String getDescriptionEng();
    int getDamage();
    Impact getImpact();
    int getImpactVolume();
    int getRecharge();
    void setRecharge(int recharge);
    int calculateRealDamage();
}
