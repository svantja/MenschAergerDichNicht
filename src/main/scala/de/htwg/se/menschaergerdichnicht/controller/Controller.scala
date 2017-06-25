package de.htwg.se.menschaergerdichnicht.controller

import de.htwg.se.menschaergerdichnicht.model._
import de.htwg.se.menschaergerdichnicht.util.{Command, Observable, UndoManager}
import de.htwg.se.menschaergerdichnicht.controller._

import scala.collection.mutable
import scala.util._
import de.htwg.se.menschaergerdichnicht.aview.gui.SwingGui
import de.htwg.se.menschaergerdichnicht.controller.GameState._
/**
  * Created by Anastasia on 01.05.17.
  */
case class Controller() extends Observable {

  var players = new Players()
  var playingField = new PlayingField()
  var message = ""
  var gameState: GameState = ONGOING
  var undoManager = new UndoManager

  def addPlayer(name: String): Try[_] = undoManager.action(AddPlayer(name, this))

  def startGame(): Try[_] = undoManager.action(Play(this))

  def chooseToken(tokenId: Int): Try[_] = undoManager.action(ChooseToken(tokenId, this))

}