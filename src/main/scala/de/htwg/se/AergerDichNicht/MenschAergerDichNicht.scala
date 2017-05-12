package de.htwg.se.AergerDichNicht

import de.htwg.se.AergerDichNicht.model.{Feld, Figur, Spieler, Wuerfel}
import de.htwg.se.AergerDichNicht.view.Tui

import scala.io.StdIn.readLine

object Mensch {
  val tui = new Tui()
  def main (args: Array[String]): Unit = { 
    var input: String = ""
    println("Wieviele Spieler? - Type 'q' to exit")
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}