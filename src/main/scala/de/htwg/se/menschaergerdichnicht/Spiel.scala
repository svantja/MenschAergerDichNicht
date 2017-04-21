package de.htwg.se.menschaergerdichnicht

import scala.io.StdIn.{readLine, readInt}
import de.htwg.se.menschaergerdichnicht.model.Spieler
import de.htwg.se.menschaergerdichnicht.model.Spielfigur
import de.htwg.se.menschaergerdichnicht.model.Wuerfel

// should try idea ultimate for worksheets

object Hello {
  def main(args: Array[String]): Unit = {
    //val student = Student("Your Name")
    //println("Hello, " + student.name)
    var gewuerfelteZahl = 0;
    var anzahlSpieler = 0;
    var anzahlWurf = 0;
    val nameSpieler1 = readLine("Hallo Spieler1, wie heisst du?\n")
    val team1 = readLine("Wähle dein Team:\n")
    
    var position = 0;
    println("Hallo " + nameSpieler1 + "! Willkommen im Spiel " + team1)
    
    val spieler1 = Spieler(nameSpieler1, team1, position)
    println(spieler1)
    anzahlSpieler += 1
    
    if (anzahlSpieler < 2){
      val nameSpieler2 = readLine("Hallo Spieler2, gebe deinen Namen ein.\n")
      val team2 = readLine("Wähle dein Team:\n")
      val spieler2 = Spieler(nameSpieler2, team2, position)
      println(spieler2)
      anzahlSpieler += 1
      println("Anzahl der Spieler: " + anzahlSpieler)
    }
    
    val weitereSpieler = readLine("Weitere Spieler?\n")
    if (weitereSpieler == "nein") {
      val wuerfeln = readLine("Bitte würfeln mit der Eingabe 'w' \n")
      
      if (wuerfeln == "w") {
        val wuerfel = new Wuerfel;
        gewuerfelteZahl = wuerfel.wuerfeln();
        println("Gewürfelte Zahl: " + gewuerfelteZahl)
        anzahlWurf += 1
        do {
          gewuerfelteZahl = wuerfel.wuerfeln();
          println("Gewürfelte Zahl: " + gewuerfelteZahl)
          anzahlWurf += 1
        }
        while(anzahlWurf < 3)
      }
    }      
  }
}
