package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Anastasia on 29.04.17.
  */
case class Player(var name: String) {

  val playerId = Player.newIdNum

  val house = new House(this)

  var finished: Boolean = false

  var tokens = addTokens()

  def getTokens(): ArrayBuffer[Token] = tokens

  def setFinished(finished: Boolean) { this.finished = finished}

  def getFinished(): Boolean = finished

  this.setName(name)

  def setName(name: String) { this.name = name }

  def addTokens(): ArrayBuffer[Token] = {
    val tokens = new ArrayBuffer[Token]
    for (i <- 1 to 4) {
      tokens += new Token(this, (house.house(i-1), i-1), 0)
      house.house(i-1).setToken(tokens(i-1))
    }
    tokens
  }


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
    //players(currentPlayer)
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
