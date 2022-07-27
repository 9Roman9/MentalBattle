package com.game.MentalBattle;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:element.properties")
@PropertySource("classpath:equipment.properties")
public class Config {

}
