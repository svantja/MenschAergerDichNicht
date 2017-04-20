package de.htwg.se.menschaergerdichnicht.model

import scala.util.Random

class Wuerfel() {
  def wuerfeln () : Int = {
    val r = new scala.util.Random
    r.nextInt(6)
  }
}