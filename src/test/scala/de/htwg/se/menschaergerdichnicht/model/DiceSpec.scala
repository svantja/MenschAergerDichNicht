package de.htwg.se.menschaergerdichnicht.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DiceSpec extends FlatSpec with Matchers{

  "A Dice" should "return values between 0 and 6" in{
    val player = Player("test", 0)
    val w = new Dice()
    assert(!(w.rollDice(player) > 6))
    assert(!(w.rollDice(player) < 0))
  }

//  "A Dice" should "return 0 or 6" in{
//    val player = Player("test", 0)
//    val w = new Dice()
//    w.rollDice(player)
//    assert((w.rollDice(player)==6 || w.rollDice(player) == 0))
//  }

  "A Dice.dice" should "be integer" in{
    val w = new Dice()
    w.dice
  }

}
