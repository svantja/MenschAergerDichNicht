package de.htwg.se.menschaergerdichnicht.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DiceSpec extends FlatSpec with Matchers{

  "A Dice" should "return values between 0 and 6" in{
    val player = Player("test", 0)
    val w = new Dice()
    w.rollDice(player)
    assert(!(w.rollDice(player)>6))
    //assert(!(w.rollDice(player)<0))
  }

}
