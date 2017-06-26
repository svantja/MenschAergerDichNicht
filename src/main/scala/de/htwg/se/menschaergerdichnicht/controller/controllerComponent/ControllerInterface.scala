package de.htwg.se.menschaergerdichnicht.controller.controllerComponent

import scala.util._
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState._
import de.htwg.se.menschaergerdichnicht.model.fieldComponent.fieldBaseImpl.PlayingField
import de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl.Players
import de.htwg.se.menschaergerdichnicht.util.Observable

/**
  * Created by Anastasia on 25.06.17.
  */
trait ControllerInterface extends Observable{

  var players: Players
  var playingField: PlayingField
  var message: String
  var gameState: GameState
  def addPlayer(name: String): Try[_]
  def startGame(): Try[_]
  def chooseToken(tokenId: Int): Try[_]
  def gameStatus: GameState

}