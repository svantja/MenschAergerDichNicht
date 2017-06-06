package de.htwg.se.menschaergerdichnicht.controller

import de.htwg.se.menschaergerdichnicht.model._
import de.htwg.se.menschaergerdichnicht.util.{Command, Observable}
import de.htwg.se.menschaergerdichnicht.controller._

import scala.collection.mutable
import scala.util._

/**
  * Created by Anastasia on 01.05.17.
  */
case class Controller(/*var players: Players = Players(), var message: String = ("test")*/) extends Observable {

  var players = new Players()
  var playingField = new PlayingField()
  var message = ""
  var gameState: GameState = Prepare(this)
  var undoStack: mutable.Stack[Command] = mutable.Stack()
  var redoStack: mutable.Stack[Command] = mutable.Stack()

  //def newGame(): Unit = {}
def getCurPlayer: Player = {
  players.getCurrentPlayer
}

  def addPlayer(name: String): Try[_] = action(AddPlayer(name, this))

  def startGame(): Try[_] = action(Play(this))

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


//abstract case class NextPlayer(c: Controller) extends Command {
//  override def action(): Try[_] = {
//    c.player = c.players.nextPlayer()
//
//    Success()
//  }
//}



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

