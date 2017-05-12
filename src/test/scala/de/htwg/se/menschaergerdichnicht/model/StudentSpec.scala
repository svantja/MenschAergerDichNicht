package de.htwg.se.menschaergerdichnicht.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import de.htwg.se.menschaergerdichnicht.model.Dice;

//import de.htwg.se.AergerDichNicht.model.Wuerfel;

@RunWith(classOf[JUnitRunner])
class StudentSpec extends FlatSpec with Matchers {
  "A Student" should "have a name" in {
    Player("Spieler1", "rot", 2).name should be("Spieler1")
  }
  "A input" should "be integer" in{
    println("How many players?")
    scala.io.StdIn.readLine()
  }
  
  "A Wuerfel" should "return values between 1 and 6" in{
    val w = new Dice
    assert(!(w.random_result>6))
    assert(!(w.random_result<1))
  }
  "A Token" should "have a farbe" in {
    Token("rot", 1, Field(0,0,"rot")).farbe should be("rot")
  }
  "A Token" should "have a position.number" in {
    Token("rot", 1, Field(0,0,"rot")).position.number should be(0)
  }
  "A Token" should "only start" in{
     var fig = Token("rot", 1, Field(0,0,"rot"))
     fig.move(fig, 6)
  
  }
  
  "A Token" should "change position.typ" in{
    var fig = Token("rot", 1, Field(37,1,"rot"))
    val test = fig.position.typ
    fig.move(fig, 6)
    assert(fig.position.typ != test)
  }
  "A Token" should "change position.number" in{
    var fig = Token("rot", 1, Field(37,1,"rot"))
    val test = fig.position.number
    fig.move(fig, 6)
    assert(fig.position.number != test)
  }
  "A Token" should "not change farbe" in{
    var fig = Token("rot", 1, Field(37,1,"rot"))
    val test = fig.farbe
    fig.move(fig, 6)
    assert(fig.farbe == test)
  } 
}
