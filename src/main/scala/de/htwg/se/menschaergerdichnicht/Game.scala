package de.htwg.se.menschaergerdichnicht

import de.htwg.se.menschaergerdichnicht.aview.tui.Tui
import de.htwg.se.menschaergerdichnicht.controller.Controller
import de.htwg.se.menschaergerdichnicht.model.Dice
import de.htwg.se.menschaergerdichnicht.model.Player

import scala.collection.mutable.Map
import scala.io.StdIn.readLine

// should try idea ultimate for worksheets
// datenstrukturen sollten runterskalieren, auf skalierbarkeit achten
// fuer einfache testfaelle

object Game {

  val controller = new Controller()
  val tui = new Tui(controller)
  def main(args: Array[String]): Unit = {

    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")

  }
}
