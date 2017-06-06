package de.htwg.se.menschaergerdichnicht.aview

import de.htwg.se.menschaergerdichnicht.controller.{Controller, Prepare}
import de.htwg.se.menschaergerdichnicht.util.Observer

import scala.collection.mutable.Map
/**
  * Created by Anastasia on 01.05.17.
  */
class Tui(controller: Controller) extends Observer{

  controller.add(this)

  printTui()

  def processInputLine(input: String): Boolean = {
    var continue = true
    input match {
      case "q" => continue = false
      case "start" => controller.startGame()
      case _ => processMoreParameters(input)
    }
    continue
  }

  private def processMoreParameters(input: String): Unit = {
    input.split(" ").toList match {
      case "add" :: player :: Nil => controller.addPlayer(player)

      case _ => controller.message = "False input"
    }
  }



  override def update: Unit =  printTui()
  def printTui(): Unit = {
    if (controller.gameState.isInstanceOf[Prepare]) {
      println("add: Add Player, q-QuitGame")
    }
  }
}
