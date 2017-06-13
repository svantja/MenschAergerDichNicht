package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

class PlayingField() {

  val playingField = new ArrayBuffer[Field]

  for (i <- 1 to 40) {
    playingField += new Field()
  }

  def getField(id: Int): Field = playingField(id)

  def moveToken(token: Token, num: Int, players: Players): Unit = {
    if (token.counter + num >= 40) {
      val move = (token.counter + num) - 40
      moveToTarget(token, move)
      token.position._1.tokenId = -1
    } else {
      val oldPosition = token.getPosition()._2
      var newPosition = oldPosition + num
      if (newPosition > 39) {
        newPosition = newPosition - 39
      }
      token.position._1.tokenId = -1

      if (playingField(newPosition).tokenId == -1) {
        token.setPosition((playingField(newPosition), newPosition))
        playingField(newPosition).setToken(token)
      } else {
        val toBeKicked = playingField(newPosition).tokenId
        if (kickToken(toBeKicked, token.getPlayer(), players)) {
          token.setPosition((playingField(newPosition), newPosition))
          playingField(newPosition).setToken(token)
        }
      }
    }
    token.setCounter(token.getCounter() + num)
  }

  def kickToken(tokenId: Int, player: Player, players: Players): Boolean = {
    for (p <- players.getAllPlayer) {
      for (token <- p.getTokens()) {
        if (token.tokenId == tokenId) {
          if (token.getPlayer() != player) {
            val free = token.getPlayer().getFreeHouse()
            token.setPosition((free, 0))
            return true
          }
        }
      }
    }
    return false
  }

  def moveToTarget(token: Token, i: Int): Unit = {
    val player = token.getPlayer()
    // wenn Token noch außerhalb vom Target, dann setze Token auf Target(i)
    if (i <= 3) {
      if (token.getPosition()._2 + i <= 3) {
        val target = player.target.targetField(i)
        if (target.tokenId == -1) {
          target.setToken(token)
          token.setPosition(target, i)
          if (token.getPosition()._2 == 3) {
            token.setFinished(true)
          }
          if (player.target.isFull(player)) {
            player.setFinished(true)
          }
        }
      }
    }
  }

  def moveToStart(token: Token): Unit = {
    token.position._1.tokenId = -1
    token.getPlayer().playerId match {
      case 1 => token.setPosition(playingField(0),0); token.setCounter(1);
      case 2 => token.setPosition(playingField(10), 10); token.setCounter(1);
      case 3 => token.setPosition(playingField(20), 20); token.setCounter(1);
      case 4 => token.setPosition(playingField(30), 30); token.setCounter(1);
      case _ => token.position._1.tokenId = token.tokenId
    }
  }
}

