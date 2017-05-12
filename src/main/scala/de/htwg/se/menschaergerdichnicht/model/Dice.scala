package de.htwg.se.menschaergerdichnicht.model;

import scala.util._

class Dice {

  def random_result : Int = {
    
    val r = Random
    var n = 0
    do{
      n = r.nextInt(7)
    }
    while (n == 0);
    n
  }
}

