package de.htwg.se.menschaergerdichnicht.aview.tui

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.{ControllerInterface, GameState}
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState._
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.menschaergerdichnicht.util.Observer
/**
  * Created by Anastasia on 01.05.17.
  */
class Tui(controller: ControllerInterface) extends Observer{

  controller.add(this)
  val DEFAULT_TOKEN = 17
  printTui()

  def processInputLine(input: String): Boolean = {
    var continue = true
    input match {
      case "q" => continue = false
      case "start" => controller.startGame()
      case "ready" => controller.startGame()
      case _ => processMoreParameters(input)
    }
    continue
  }

  private def processMoreParameters(input: String): Unit = {
    input.split(" ").toList match {
      case "add" :: player :: Nil => controller.addPlayer(player)
      case "move" :: tokenId :: Nil => controller.chooseToken(strToInt(tokenId, DEFAULT_TOKEN))
      case _ => controller.message = "False input"
    }
  }

  def strToInt(s: String, default: Int): Int = {
    try{
      s.toInt
    } catch {
      case _: Exception => default
    }
  }



  override def update: Unit =  printTui()
  def printTui(): Unit = {
//    if (controller.gameState.isInstanceOf[Prepare]) {
//      println("add: Add Player, start: Start Game, ready: next round, move: Move selected Token, q: Quit Game")
//    }
    println("add: Add Player, start: Start Game, ready: next round, move: Move selected Token, q: Quit Game")
    controller.gameState = ONGOING
  }
}
