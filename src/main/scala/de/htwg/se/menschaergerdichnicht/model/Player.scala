package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Anastasia on 29.04.17.
  */
case class Player(var name: String) {

  var finished: Boolean = false

  def setFinished(finished: Boolean) { this.finished = finished}

  def getFinished(): Boolean = finished

 /* def tokens: Unit = {
    val tokens = new ArrayBuffer[Token]
    var position = 0;
    for (i <- 1 to 4) {
      tokens += Token(position, area = 0)
      position += 1
    }
  }*/

  val playerId = Player.newIdNum

  this.setName(name)

  def setName(name: String) { this.name = name }


  override def toString: String = name

}

object Player{
  private var idNumber = 0
  private def newIdNum = {
    idNumber += 1;
    idNumber
  }
}

case class Players(currentPlayer: Int = 0, players: Vector[Player] = Vector()) {

  def addPlayer(player: Player): Players = {
    copy(players = players :+ player)
  }

  def removePlayer(): Players = {
    copy(players = players.init)
  }
  def updateCurrentPlayer(player: Player): Players = {
    copy(players = players.updated(currentPlayer, player))
  }
  def nextPlayer(): Players = {
    copy(currentPlayer = (currentPlayer + 1) % players.length)
  }
  def getCurrentPlayer: Player = {
    players(currentPlayer)
  }


  //def apply(i: Int): Player = players(i)

  override def toString: String = {
    var nameList = ""
    for (player <- players) {
      if (player == players(currentPlayer)) {
        nameList += "Current > " + player.toString() + "\n"
      }
      else {
        nameList += "  " + player.toString() + "\n"
      }
    }
    nameList
  }
}
