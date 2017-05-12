package de.htwg.se.menschaergerdichnicht

import de.htwg.se.menschaergerdichnicht.model.{Field, Token, Player, Dice}
import de.htwg.se.menschaergerdichnicht.view.Tui

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