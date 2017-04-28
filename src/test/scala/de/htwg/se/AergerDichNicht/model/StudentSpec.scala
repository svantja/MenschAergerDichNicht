package de.htwg.se.AergerDichNicht.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import de.htwg.se.AergerDichNicht.model.Wuerfel;

@RunWith(classOf[JUnitRunner])
class StudentSpec extends FlatSpec with Matchers {
  "A Student" should "have a name" in {
    Spieler("Spieler1", "rot", 2).name should be("Spieler1")
  }
  "A input" should "be integer" in{
    println("How many players?")
    scala.io.StdIn.readLine()
  }
  
  "A Wuerfel" should "return values between 1 and 6" in{
    val w = new Wuerfel
    assert(!(w.random_result>6))
    assert(!(w.random_result<1))
  }
  "A Figur" should "have a farbe" in {
    Figur("rot", 1, Feld(0,0,"rot")).farbe should be("rot")
  }
  "A Figur" should "have a position.number" in {
    Figur("rot", 1, Feld(0,0,"rot")).position.number should be(0)
  }
  "A Figur" should "only start" in{
     var fig = Figur("rot", 1, Feld(0,0,"rot"))
     fig.move(fig, 6)
  
  }
  
  "A Figur" should "change position.typ" in{
    var fig = Figur("rot", 1, Feld(37,1,"rot"))
    val test = fig.position.typ
    fig.move(fig, 6)
    assert(fig.position.typ != test)
  }
  "A Figur" should "change position.number" in{
    var fig = Figur("rot", 1, Feld(37,1,"rot"))
    val test = fig.position.number
    fig.move(fig, 6)
    assert(fig.position.number != test)
  }
  "A Figur" should "not change farbe" in{
    var fig = Figur("rot", 1, Feld(37,1,"rot"))
    val test = fig.farbe
    fig.move(fig, 6)
    assert(fig.farbe == test)
  } 
}
