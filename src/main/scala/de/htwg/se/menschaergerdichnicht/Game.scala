package de.htwg.se.menschaergerdichnicht

import de.htwg.se.menschaergerdichnicht.aview.gui.SwingGui
import de.htwg.se.menschaergerdichnicht.aview.tui.Tui
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.menschaergerdichnicht.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl.Player

import scala.collection.mutable.Map
import scala.io.StdIn.readLine

// should try idea ultimate for worksheets
// datenstrukturen sollten runterskalieren, auf skalierbarkeit achten
// fuer einfache testfaelle

object Game {

  val controller = new Controller()
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  gui.visible = true

  def main(args: Array[String]): Unit = {

    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
      gui.repaint()
    } while (input != "q")
    gui.dispose()
  }
}
