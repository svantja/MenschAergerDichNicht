import de.htwg.se.menschaergerdichnicht.model.{Field, Player, Team, Token}

import scala.collection.mutable.ArrayBuffer


print("hello")


case class Dice() {
  var dice: Int = 0
  def rollDice(player: Player) : Int = {
    val r = scala.util.Random;
    if (player.house.isFull(player)) {
      for (i <- 1 to 3) {
        do {
          dice = r.nextInt(7)
        } while(dice == 0)

        if (dice == 6) dice
      }
    }
    9
  }
}

val player = Player("ana", 0)


val dice = new Dice()

dice.rollDice(player)












