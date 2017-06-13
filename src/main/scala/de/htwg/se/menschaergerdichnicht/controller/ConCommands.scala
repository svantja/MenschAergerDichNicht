package de.htwg.se.menschaergerdichnicht.controller

import com.sun.net.httpserver.Authenticator.Failure
import de.htwg.se.menschaergerdichnicht.model.{Dice, Player, Token}
import de.htwg.se.menschaergerdichnicht.util.Command

import scala.util.{Success, Try, Failure}

/**
  * Created by Anastasia on 06.06.17.
  */
case class AddPlayer(name: String, c: Controller) extends Command {
  val player = Player(name, 0)

  override def action(): Try[_] = {
    c.players = c.players.addPlayer(player)

    println("Spieler " + name + " wurde hinzugefuegt")
    Success()
  }

  override def undo(): Try[_] = {
    c.players = c.players.removePlayer()
    c.message = "Geloeschter Spieler: " + name
    Success()
  }

}

case class ChooseToken(tokenId: Int, c: Controller) extends Command {
  val player = c.players.getCurrentPlayer
  val token = player.getTokenById(tokenId)
  val dice = new Dice()

  override def action(): Try[_] = {
    println(c.players.getCurrentPlayer)
    if (player.getDiced() == 6) {
      if (token.counter == 0) {
        c.playingField.moveToStart(token)
        println("Moved Token" + tokenId + " to start")
        player.setDiced(dice.rollDice(c.players.getCurrentPlayer))
        c.playingField.moveToken(token, player.getDiced(), c.players)
        println("Moved Token" + tokenId + " " + player.getDiced() + " fields")

        c.players = c.players.nextPlayer()
      } else {
        c.playingField.moveToken(token, player.getDiced(), c.players)
        println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
        player.setDiced(dice.rollDice(c.players.getCurrentPlayer))
        c.playingField.moveToken(token, player.getDiced(), c.players)
        println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
      }

    } else {
      c.playingField.moveToken(token, player.getDiced(), c.players)
      println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
      c.players = c.players.nextPlayer()
    }
    Success()
  }

  override def undo(): Try[_] = ???

}

case class Play(c: Controller) extends Command {
  val dice = new Dice()
  override def action(): Try[_] = {
    //while (true)
      val player = c.players.getCurrentPlayer
      if (!player.getFinished()) {
        val num = dice.rollDice(c.players.getCurrentPlayer)
        if (num == 0) {
          println("Cannot move, next player.")
          c.players = c.players.nextPlayer()
          println(c.players.getCurrentPlayer)

        } else {
          if (player.house.isFull(player)) {
            c.playingField.moveToStart(player.tokens(0))
            println("Moved Token" + player.tokens(0).tokenId + " to start")
            player.setDiced(dice.rollDice(c.players.getCurrentPlayer))
            c.playingField.moveToken(player.tokens(0), player.getDiced(), c.players)
            println("Moved Token" + player.tokens(0).tokenId + " " + player.getDiced() + " fields")

            c.players = c.players.nextPlayer()
          } else {
            player.setDiced(num)
            if (num == 6) {
              println("Choose token to move")
              println(num + "diced" + player.getDiced())
              println("avaiable tokens: " + player.getAvaiableTokens())
            } else {
              println("Choose token to move")
              println(num + "diced" + player.getDiced())
              println("avaiable tokens: " + player.getAvaiableTokens())
            }
          }
        }
      }
    //}
    Success()
  }

  override def undo(): Try[_] = {
    println("Undo")
    Success()
  }
}
