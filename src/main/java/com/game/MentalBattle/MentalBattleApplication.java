package com.game.MentalBattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MentalBattleApplication {
	static ConfigurableApplicationContext ctx;
	public static void main(String[] args) {
		ctx = SpringApplication.run(MentalBattleApplication.class, args);
	}

	public static void closeApp(){
		ctx.close();
	}
}
