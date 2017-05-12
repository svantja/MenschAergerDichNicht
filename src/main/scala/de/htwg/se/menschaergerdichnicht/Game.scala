package de.htwg.se.menschaergerdichnicht

import de.htwg.se.menschaergerdichnicht.controller.Controller
import de.htwg.se.menschaergerdichnicht.aview.Tui
import de.htwg.se.menschaergerdichnicht.model.Dice
import de.htwg.se.menschaergerdichnicht.model.Player

import scala.collection.mutable.Map

import scala.io.StdIn.readLine

// should try idea ultimate for worksheets
// datenstrukturen sollten runterskalieren, auf skalierbarkeit achten
// fuer einfache testfaelle

object Game {

  def main(args: Array[String]): Unit = {


    val controller = new Controller()
    val tui = new Tui(controller)
    while (tui.processInputLine(readLine())) {}


  }
}
