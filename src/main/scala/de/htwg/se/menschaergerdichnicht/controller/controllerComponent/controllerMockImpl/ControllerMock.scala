package de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerMockImpl

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.ControllerInterface
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState._
import de.htwg.se.menschaergerdichnicht.model.fieldComponent.fieldBaseImpl.PlayingField
import de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl.Players

import scala.util.{Success, Try}

/**
  * Created by Anastasia on 25.06.17.
  */
case class ControllerMock() extends ControllerInterface{
  var players = Players()
  var playingField = PlayingField()
  var message = ""
  var gameState = FINISHED
  def addPlayer(name: String): Try[_] = Success()
  def startGame(): Try[_] = Success()
  def chooseToken(tokenId: Int): Try[_] = Success()
  def gameStatus: GameState = FINISHED
}
