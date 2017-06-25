import de.htwg.se.menschaergerdichnicht.model.fieldComponent.{FieldInterface, HouseInterface}
import de.htwg.se.menschaergerdichnicht.model.playerComponent.PlayerInterface

import scala.collection.mutable.ArrayBuffer


print("hello")


case class House(player: PlayerInterface) extends HouseInterface {
  val house = new ArrayBuffer[FieldInterface]

  for (i <- 1 to 4) {
    house += FieldInterface()
  }

  def isFull(player: PlayerInterface): Boolean = {
    for (field <- player.house.house) {
      if (field.tokenId == -1) {
        return false
      }
    }
    true
  }

}












