package de.htwg.se.menschaergerdichnicht.model

import scala.util.Random;

class Wuerfel() {
  def wuerfeln() : Int = {
    var n = 0;
  	val r = scala.util.Random;
  	do {
  	  n = r.nextInt(7)
  	}
  	while(n == 0)
  		return n
  	}
}