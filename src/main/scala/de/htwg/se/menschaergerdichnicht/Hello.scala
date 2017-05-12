package de.htwg.se.menschaergerdichnicht

import de.htwg.se.menschaergerdichnicht.model.Player
import de.htwg.se.menschaergerdichnicht.model.Dice
import de.htwg.se.menschaergerdichnicht.model.Token

object Hello {
  def main(args: Array[String]): Unit = {
    //val fig = Figur("rot", 0)
    var play = true
    while(play == true){
      println("Wieviele Spieler? - Type 'q' to exit")
      val wuerfel = new Dice
      val anzahl = scala.io.StdIn.readLine()
      
      anzahl match {
        case "2" => {val p1 = Player("Spieler1", "rot", 1)
                     val p2 = Player("Spieler2", "gelb", 2)
                     var f1 = p1.get_all_figures(p1.farbe)
                     var f2 = p2.get_all_figures(p2.farbe)
                     for (i <- f1) println(i, i.position)
                          
                     while(play == true){
                       println("Welcher Spieler würfelt? - (p1, p2)")
                       val turn = scala.io.StdIn.readLine()
                       if(turn == "p1") {
                         var num = wuerfel.random_result 
                         println("Welcher Figur?")
                         var n = scala.io.StdIn.readLine().toInt
                         if(n.toString() != ""){
                           //move figure
                             f1(n-1).move(f1(n-1), num)
                             println(f1(n-1))
                          }
                       }else if(turn == "p2") {
                         var num = wuerfel.random_result 
                         println("Welcher Figur?")
                         var n = scala.io.StdIn.readLine().toInt
                         if(n.toString() != ""){
                           //move figure
                           f2(n-1).move(f2(n-1), num)
                           println(f2(n-1))
                          }
                         }else {play = false; println("ENDE")}
                     }
        }
        case "3" => {val p1 = Player("Spieler1", "rot", 1) 
                             val p2 = Player("Spieler2", "gelb", 2) 
                             val p3 = Player("Spieler3", "gruen", 3)
                             var f1 = p1.get_all_figures(p1.farbe)
                             var f2 = p2.get_all_figures(p2.farbe)
                             var f3 = p3.get_all_figures(p3.farbe)
                           
                             while(play == true){
                              println("Welcher Spieler würfelt? - (p1, p2, p3)")
                              val turn = scala.io.StdIn.readLine()
                              if(turn == "p1") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f1(n-1).move(f1(n-1), num)
                                  println(f1(n-1))
                                }
                              }
                              else if(turn == "p2") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f2(n-1).move(f2(n-1), num)
                                  println(f2(n-1))
                                }
                              }else if(turn == "p3") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f3(n-1).move(f3(n-1), num)
                                  println(f3(n-1))
                                }
                              }else {play = false; println("ENDE")}
                          
                            }
        }
        case "4" => {val p1 = Player("Spieler1", "rot", 1) 
                             val p2 = Player("Spieler2", "gelb", 2)
                             val p3 = Player("Spieler3", "gruen", 3)
                             val p4 = Player("Spieler4", "blau", 4);
                             var f1 = p1.get_all_figures(p1.farbe)
                             var f2 = p2.get_all_figures(p2.farbe)
                             var f3 = p3.get_all_figures(p3.farbe)
                             var f4 = p4.get_all_figures(p4.farbe)
                           
                             while(play == true){
                              println("Welcher Spieler würfelt? - (p1, p2, p3, p4)")
                              val turn = scala.io.StdIn.readLine()
                              if(turn == "p1") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f1(n-1).move(f1(n-1), num)
                                  println(f1(n-1))
                                }
                              }
                              else if(turn == "p2") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f2(n-1).move(f2(n-1), num)
                                  println(f2(n-1))
                                }
                              }else if(turn == "p3") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f3(n-1).move(f3(n-1), num)
                                  println(f3(n-1))
                                }
                              }else if(turn == "p4") {
                                var num = wuerfel.random_result 
                                println("Welcher Figur?")
                                var n = scala.io.StdIn.readLine().toInt
                                if(n.toString() != ""){
                                  //move figure
                                  f4(n-1).move(f4(n-1), num)
                                  println(f4(n-1))
                                }
                              }else {play = false; println("ENDE")}
                            }
        }
        case "q" => {
          play = false
          println("ENDE")
        }
        case _ => println("FEHLER!, es müssen mindestens 2 Spieler sein und hoechstens 4")
      } 
    }
  }
}
