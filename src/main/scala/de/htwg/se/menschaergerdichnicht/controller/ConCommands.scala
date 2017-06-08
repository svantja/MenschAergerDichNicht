package de.htwg.se.menschaergerdichnicht.controller

import de.htwg.se.menschaergerdichnicht.model.{Dice, Player}
import de.htwg.se.menschaergerdichnicht.util.Command
import scala.util.{Success, Try}

/**
  * Created by Anastasia on 06.06.17.
  */
case class AddPlayer(name: String, c: Controller) extends Command {
  val player = Player(name)

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

case class Play(c: Controller) extends Command {
  val dice = new Dice()
  override def action(): Try[_] = {
    while (true) {

      val player = c.players.getCurrentPlayer
      val num = dice.rollDice(c.players.getCurrentPlayer)
      if (num == 9) {
        println("Cannot move, next player.")
        c.players = c.players.nextPlayer()
        println(c.players.getCurrentPlayer)
        //c.players.updateCurrentPlayer(c.players.nextPlayer())
      } else {
        if (player.house.isFull(player)) {
          println("move to startfield")
          c.playingField.moveToStart(player.tokens(0))
          println(player.tokens)
        } else {
          //println("move")
          c.players = c.players.nextPlayer()
          //c.players.updateCurrentPlayer(c.players.nextPlayer())
        }
      }
    }
    Success()
  }

  override def undo(): Try[_] = {
    println("Undo")
    Success()
  }
}
