package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Anastasia on 29.04.17.
  */
case class Player(name: String) {

  def tokens: Unit = {
    val tokens = new ArrayBuffer[Token]
    var position = 0;
    for (i <- 1 to 4) {
      tokens += Token(position, area = 0)
      position += 1
    }
  }
  
  def get_all_figures(farbe:String): Array[Token] ={
    var f1 = Token(farbe, 1, Field(0, 0, farbe))
    var f2 = Token(farbe, 2, Field(1, 0, farbe))
    var f3 = Token(farbe, 3, Field(2, 0, farbe))
    var f4 = Token(farbe, 4, Field(3, 0, farbe))
    Array(f1, f2, f3, f4)
  }

  override def toString: String = name
}

case class Players(curPlayer: Int = 0, players: Vector[Player] = Vector()) {
  def addPlayer(player: Player): Players = {
    copy(players = players :+ player)
  }
  def removePlayer(): Players = {
    copy(players = players.init)
  }
  def updateCurlPlayer(player: Player): Players = {
    copy(players = players.updated(curPlayer, player))
  }
  def nextPlayer(): Players = {
    copy(curPlayer = (curPlayer + 1) % players.length)
  }
  def getCurPlayer: Player = {
    players(curPlayer)
  }

  def apply(i: Int): Player = players(i)
  override def toString: String = {
    var text = ""
    for (player <- players) {
      if (player == players(curPlayer)) {
        text += "-> " + player.toString() + "\n"
      }
      else {
        text += "   " + player.toString() + "\n"
      }
    }
    text
  }
}

