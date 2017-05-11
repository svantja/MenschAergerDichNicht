package de.htwg.se.menschaergerdichnicht.controller

import de.htwg.se.menschaergerdichnicht.model._
import de.htwg.se.menschaergerdichnicht.util.Observable

import scala.util._

/**
  * Created by Anastasia on 01.05.17.
  */
case class Controller() extends Observable {

  var players = Players()
  var gameState: GameState = Prepare(this)
  var message = ("test")

  //def newGame(): Unit = {}
def getCurPlayer: Player = {
  players.getCurPlayer
}

  def addPlayer(name: String): Try[String] = doIt(AddPlayer(name))

  def addTeam(): Unit = {}

  def doIt(com: Command): Try[String] = {
    val explored = gameState.exploreCommand(com)
    explored match {
      case Success(s) => {
        //undoStack.push(com)
        //redoStack.clear()
        message = s
      }
      case Failure(e) => message = e.getMessage
    }
    //notifyObservers()
    explored
  }

}

// ##################### Controller Commands #######################
trait Command {
  def doIt(c: Controller): Try[String]
  def undo(c: Controller): Try[String]
}

case class AddPlayer(name: String) extends Command {
  val player = Player(name)

  override def doIt(c: Controller): Success[String] = {
    c.players = c.players.addPlayer(player)
    Success("Spieler" + name + "wurde hinzugefuegt!")
  }

  override def undo(c: Controller): Try[String] = {
    c.players = c.players.removePlayer()
    Success("Spieler geloescht")
  }

}

abstract case class NextPlayer() extends Command {
  override def doIt(c: Controller): Try[String] = {
    c.players = c.players.nextPlayer()
    //c.prepareNewPlayer()
    Success("Player " + c.getCurPlayer.name + " it is your turn!")
  }
}

trait GameState {
  def exploreCommand(com: Command): Try[String]
}

case class Prepare(c: Controller) extends GameState {
  override def exploreCommand(com: Command): Try[String] = {
    com match {
      case command: AddPlayer => command.doIt(c)
      case _ => Failure(new Exception("ILLEGAL COMMAND"))
    }
  }
}

case class Play(c: Controller) extends GameState {
  override def exploreCommand(com: Command): Try[String] = {
    com match {
      case command: NextPlayer => command.doIt(c)
      //case command: SetStone => command.doIt(c)
      case _ => Failure(new Exception("ILLEGAL COMMAND"))
    }
  }
}