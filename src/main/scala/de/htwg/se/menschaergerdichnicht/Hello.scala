package de.htwg.se.menschaergerdichnicht

import scala.io.StdIn.{readLine, readInt}
import de.htwg.se.yourgame.model.Student
import de.htwg.se.menschaergerdichnicht.model.Spieler
import de.htwg.se.menschaergerdichnicht.model.Spielfigur

object Hello {
  def main(args: Array[String]): Unit = {
    val student = Student("Your Name")
    //println("Hello, " + student.name)
    
    val nameSpieler = readLine("What's your name?\n")
    val team = readLine("Choose your team:\n")
    val player = Spielfigur(1, team)
    var position = 0;
    println("Hello " + nameSpieler + "! Welcome to team " + team)
    val spieler = Spieler(nameSpieler, player, position)
    println(spieler)
  }
}
