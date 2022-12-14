package com.game.MentalBattle.game.lightSide.magic.fire;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.lightSide.magic.Element;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("fireBean")
public class Fire implements Element {
    AnnotationConfigApplicationContext context;
    private MagicSkill skill1;
    private MagicSkill skill2;
    private MagicSkill skill3;

    public void setFireMagic(){
        context = new AnnotationConfigApplicationContext(Config.class);
        skill1 = context.getBean("meteorRainBean",MeteorRain.class);
        skill2 = context.getBean("dragonBreathBean",DragonBreath.class);
        skill3 = context.getBean("flamingWhipBean",FlamingWhip.class);
    }

    @Override
    public MagicSkill getSkill1() {
        return skill1;
    }

    @Override
    public MagicSkill getSkill2() {
        return skill2;
    }

    @Override
    public MagicSkill getSkill3() {
        return skill3;
    }
}
