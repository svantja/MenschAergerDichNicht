import de.htwg.se.menschaergerdichnicht.model.fieldComponent.{FieldInterface, HouseInterface}
import de.htwg.se.menschaergerdichnicht.model.playerComponent.{PlayerInterface, PlayersInterface}
import de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl.Player

import scala.collection.mutable.ArrayBuffer


print("hello")

case class Players(currentPlayer: Int = 0, players: Vector[Player] = Vector()) extends PlayersInterface {

  def addPlayer(player: Player): Players = {
    copy(players = players :+ player)
  }

  def removePlayer(): Players = {
    copy(players = players.init)
  }
  def updateCurrentPlayer(player: PlayerInterface): Players = {
    copy(players = players.updated(currentPlayer, Player(player.getName(), player.getDiced())))
  }
  def nextPlayer(): Players = {
    copy(currentPlayer = (currentPlayer + 1) % players.length)
    //players(currentPlayer)
  }
  def getCurrentPlayer: Player = {
    players(currentPlayer)
  }
  def getAllPlayer: Vector[Player] = {
    players
  }

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

val player = Player("hans", 1)
val player2 = Player("brigitte", 2)
val players: Players = Players(0, players = Vector(player, player2))

players.addPlayer(player)
players.addPlayer(player2)

println(players)

println(players.getAllPlayer)














