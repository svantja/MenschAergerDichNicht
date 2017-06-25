package de.htwg.se.menschaergerdichnicht.model.fieldComponent.fieldBaseImpl

import de.htwg.se.menschaergerdichnicht.model.playerComponent.Player

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Anastasia on 01.05.17.
  */
case class House(player: Player) {
  val house = new ArrayBuffer[Field]

  for (i <- 1 to 4) {
    house += new Field()
  }

  def isFull(player: Player): Boolean = {
    for (field <- player.house.house) {
      if (field.tokenId == -1) {
        return false
      }
    }
    true
  }

}
