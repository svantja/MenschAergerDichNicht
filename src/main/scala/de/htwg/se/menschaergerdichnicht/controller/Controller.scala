package de.htwg.se.menschaergerdichnicht.controller
import de.htwg.se.menschaergerdichnicht.model._
import de.htwg.se.menschaergerdichnicht.util.Observable
import scala.collection.mutable

import scala.util._

/**
  * Created by Anastasia on 01.05.17.
  */
case class Controller(/*var players: Players = Players(), var message: String = ("test")*/) extends Observable {

  var players = new Players()
  var message = ""
  var gameState: GameState = Prepare(this)
  var undoStack: mutable.Stack[Command] = mutable.Stack()
  var redoStack: mutable.Stack[Command] = mutable.Stack()

  //def newGame(): Unit = {}
def getCurPlayer: Player = {
  players.getCurrentPlayer
}

  def addPlayer(name: String): Try[_] = action(AddPlayer(name, this))

  def action(com: Command): Try[_] = {
    //val explored = gameState.exploreCommand(com)
    val result = com.action()
    if (result.isSuccess) {
      undoStack.push(com)
      redoStack.clear()
    }
    result
  }

}

// ##################### Controller Commands #######################
trait Command {
  def action(): Try[_]
  def undo(): Try[_]
}

case class AddPlayer(name: String, c: Controller) extends Command {
  val player = Player(name)

  override def action(): Try[_] = {
    c.players = c.players.addPlayer(player)
    //c.message = "Spieler " + name + " wurde hinzugefuegt"
    println("Spieler " + name + " wurde hinzugefuegt")
    Success()
  }

  override def undo(): Try[_] = {
    c.players = c.players.removePlayer()
    c.message = "Geloeschter Spieler: " + name
    Success()
  }

}

abstract case class NextPlayer(c: Controller) extends Command {
  override def action(): Try[_] = {
    c.players = c.players.nextPlayer()

    Success()
  }
}

abstract case class StartGame(c: Controller) extends Command {
  override def action(): Try[_] = {
    println(c.players)
    Success()
  }
}

trait GameState {
  def exploreCommand(com: Command): Try[_]
}

case class Prepare(c: Controller) extends GameState {
  override def exploreCommand(com: Command): Try[_] = {
    com match {
      case command: AddPlayer => com.action()
      case _ => Failure(new Exception("ILLEGAL COMMAND"))
    }
  }
}
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

