package de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerMockImpl

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.ControllerInterface
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState.GameState

import scala.util.Try

/**
  * Created by Anastasia on 25.06.17.
  */
abstract class Controller() extends ControllerInterface{

  override def addPlayer(name: String): Try[_] = ???

  override def startGame(): Try[_] = ???

  override def chooseToken(tokenId: Int): Try[_] = ???

  override def gameStatus: GameState = ???

  override var message: String = _
  override var gameState: GameState = _
}
