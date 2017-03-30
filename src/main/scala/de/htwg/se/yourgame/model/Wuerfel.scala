package de.htwg.se.yourgame.model

import scala.util._

class Wuerfel {

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