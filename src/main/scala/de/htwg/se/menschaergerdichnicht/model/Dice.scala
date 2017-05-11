package de.htwg.se.menschaergerdichnicht.model;

case class Dice(var dice: Int) {
  def rollDice() : Int = {
    val r = scala.util.Random;
  	do {
  	  dice = r.nextInt(7)
  	}
  	while(dice == 0)
  		return dice
  	}
}