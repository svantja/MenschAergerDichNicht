package de.htwg.se.menschaergerdichnicht.aview

import de.htwg.se.menschaergerdichnicht.controller.{Controller, Play, Prepare}
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
      //case "s" => controller.startGame()
      case _ => processMoreParameters(input)
    }
    continue
  }

  private def processMoreParameters(input: String): Unit = {
    input.split(" ").toList match {
      case "add" :: player :: Nil => controller.addPlayer(player)
      case "team" :: team :: Nil => controller.addTeam()
      case _ => controller.message = "False input"
    }
  }



  override def update: Unit =  printTui()
  def printTui(): Unit = {
    if (controller.gameState.isInstanceOf[Prepare]) {
      println("               add-AddPlayer [Name], team-AddTeam [team], s-StartGame, q-QuitGame")
    }
    if (controller.gameState.isInstanceOf[Play]) {
      println("               SetStone [row] [col], n-NextPlayer")
    }
  }
}
