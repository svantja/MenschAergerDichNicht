package de.htwg.se.menschaergerdichnicht.controller

import de.htwg.se.menschaergerdichnicht.model._
import de.htwg.se.menschaergerdichnicht.util.Observable

import scala.util._

/**
  * Created by Anastasia on 01.05.17.
  */
case class Controller(var players: Players = Players(), var message: String = ("test")) extends Observable {

  //var gameState: GameState = Prepare(this)

  //def newGame(): Unit = {}
def getCurPlayer: Player = {
  players.getCurPlayer
}

  def addPlayer(name: String): Try[Controller] = doIt(AddPlayer(name, this))

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
  def doIt(): Try[_]
  def undo(): Try[_]
}

case class AddPlayer(name: String, c: Controller) extends Command {
  val player = Player(name)

  override def doIt(): Try[_] = {
    c.players = c.players.addPlayer(player)
    c.message = "Spieler " + name + " wurde hinzugefuegt"
    Success()
  }

  override def undo(): Try[_] = {
    c.players = c.players.removePlayer()
    c.message = "Geloeschter Spieler: " + name
    Success()
  }

}

//abstract case class NextPlayer(c: Controller) extends Command {
//  override def doIt(c: Controller): Try[_] = {
//    c.players = c.players.nextPlayer()
//
//    Success()
//  }
//}

//abstract case class StartGame() extends Command {
//  override def doIt(c: Controller): Unit = {
//    println(c.players)
//  }
//}
//
//trait GameState {
//  def exploreCommand(com: Command): Try[_]
//}
//
//case class Prepare(c: Controller) extends GameState {
//  override def exploreCommand(com: Command): Try[_] = {
//    com match {
//      case command: AddPlayer => com.doIt(c)
//      case _ => Failure(new Exception("ILLEGAL COMMAND"))
//    }
//  }
//}
//
//case class Play(c: Controller) extends GameState {
//  override def exploreCommand(com: Command): Try[_] = {
//    com match {
//      case command: NextPlayer => command.doIt(c)
//      //case command: SetStone => command.doIt(c)
//      case _ => Failure(new Exception("ILLEGAL COMMAND"))
//    }
//  }
//}

