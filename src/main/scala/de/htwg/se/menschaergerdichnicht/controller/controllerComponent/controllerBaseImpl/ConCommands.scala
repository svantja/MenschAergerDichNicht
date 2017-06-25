package de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.menschaergerdichnicht.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.menschaergerdichnicht.model.playerComponent.Player
import de.htwg.se.menschaergerdichnicht.util.Command
import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState._

import scala.util.{Success, Try}

/**
  * Created by Anastasia on 06.06.17.
  */
case class AddPlayer(name: String, c: Controller) extends Command {
  val player = Player(name, 0)

  override def action(): Try[_] = {
    c.players = c.players.addPlayer(player)
    println("Spieler " + name + " wurde hinzugefuegt")
    c.gameState = ONGOING
    Success()
  }

  override def undo(): Try[_] = {
    c.players = c.players.removePlayer()
    c.message = "Geloeschter Spieler: " + name
    c.gameState = ONGOING
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
        println(token.counter + "counter" + token.getPosition()._2)
        c.players = c.players.nextPlayer()
        //c.gui.repaint()
      } else {
        c.playingField.moveToken(token, player.getDiced(), c.players)
        println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
        player.setDiced(dice.rollDice(c.players.getCurrentPlayer))
        c.playingField.moveToken(token, player.getDiced(), c.players)
        println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
        println(token.counter + "counter" + token.getPosition()._2)
        c.players = c.players.nextPlayer()
        //c.gui.repaint()
      }

    } else {
      c.playingField.moveToken(token, player.getDiced(), c.players)
      println("Moved Token" + tokenId + " " + player.getDiced() + " fields")
      println(token.counter + "counter" + token.getPosition()._2)
      c.players = c.players.nextPlayer()
      //c.gui.repaint()
    }
    c.gameState = ONGOING
    Success()
  }

  override def undo(): Try[_] = ???

}

case class Play(c: Controller) extends Command {
  val dice = new Dice()
  //c.gui.players = c.players
  //c.gui.repaint()
  //println("alle payers..." + c.gui.players)

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
            //c.gui.repaint()
          } else {
            player.setDiced(num)
            if (num == 6) {
              println("Choose token to move")
              println(num + "diced" + player.getDiced())
              println("avaiable tokens: " + player.getAvailableTokens())
            } else {
              println("Choose token to move")
              println(num + "diced" + player.getDiced())
              println("avaiable tokens: " + player.getAvailableTokens())
            }
          }
        }
      }
    //}
    c.gameState = ONGOING
    Success()
  }

  override def undo(): Try[_] = {
    println("Undo")
    c.gameState = ONGOING
    Success()
  }
}
