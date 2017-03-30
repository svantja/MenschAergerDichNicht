package de.htwg.se.yourgame

import de.htwg.se.yourgame.model.Student
import de.htwg.se.yourgame.model.Wuerfel

object Hello {
  def main(args: Array[String]): Unit = {
    println("Wieviele Spieler?")
    val wuerfel = new Wuerfel
    var play = true
    val anzahl = scala.io.StdIn.readLine().toInt
    if (anzahl <= 1 || anzahl > 4) println("FEHLER!, es müssen mindestens 2 Spieler sein und hoechstens 4")
    else if(anzahl == 2) {val p1 = Student("Spieler1", "rot", 1)
                           val p2 = Student("Spieler2", "gelb", 2)
                           
                           while(play == true)
                           if(scala.io.StdIn.readLine() == "w") {val num = wuerfel.random_result; println(num)} 
                           else {play = false; println("ENDE")}}
    else if (anzahl == 3) {val p1 = Student("Spieler1", "rot", 1) 
                           val p2 = Student("Spieler2", "gelb", 2) 
                           val p3 = Student("Spieler3", "gruen", 3)
                           while(play == true)
                           if(scala.io.StdIn.readLine() == "w") {val num = wuerfel.random_result; println(num)} 
                           else {play = false; println("ENDE")}}
    else if (anzahl == 4) {val p1 = Student("Spieler1", "rot", 1) 
                           val p2 = Student("Spieler2", "gelb", 2)
                           val p3 = Student("Spieler3", "gruen", 3)
                           val p4 = Student("Spieler4", "blau", 4);
                           while(play == true)
                           if(scala.io.StdIn.readLine() == "w") {val num = wuerfel.random_result; println(num)} 
                           else {play = false; println("ENDE")}}
  }
}
