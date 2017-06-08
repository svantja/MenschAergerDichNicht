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
    //c.message = "Spieler " + name + " wurde hinzugefuegt"
    println("Spieler " + name + " wurde hinzugefuegt")
    println(player.tokens)
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

  override def action(): Try[_] = {
    println(c.players.getCurrentPlayer)
    c.playingField.moveToken(token, player.getDiced())
    println(player.tokens)
    println(player.getDiced())
    c.players = c.players.nextPlayer()
    Success()
  }

  override def undo(): Try[_] = ???

}

case class Play(c: Controller) extends Command {
  val dice = new Dice()
  override def action(): Try[_] = {
    //while (true) {

      val player = c.players.getCurrentPlayer
      val num = dice.rollDice(c.players.getCurrentPlayer)
      if (num == 9) {
        println("Cannot move, next player.")
        c.players = c.players.nextPlayer()
        println(c.players.getCurrentPlayer)

      } else {
        if (player.house.isFull(player)) {
          println("move to startfield")
          c.playingField.moveToStart(player.tokens(0))
          println("moved start", player.tokens)
          c.playingField.moveToken(player.tokens(0), dice.rollDice(c.players.getCurrentPlayer))
          println("moved after start", player.tokens)
          c.players = c.players.nextPlayer()
        } else {
          player.setDiced(num)
          if (num == 6) {
            println("Choose token to move")
            println(num, "diced", player.getDiced())
            println("avaiable tokens: ", player.getAvaiableTokens())
          } else {
            println("Choose token to move")
            println(num, "diced", player.getDiced())
            println("avaiable tokens: ", player.getAvaiableTokens())
          }

//          c.playingField.moveToken(player.tokens(0), num)
//          println(player.tokens)
//          println(num)
//          c.players = c.players.nextPlayer()
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
