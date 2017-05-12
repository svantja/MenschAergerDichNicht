package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

class PlayingField() {

  val playingField = new ArrayBuffer[Field]

  for (i <- 1 to 40) {
    playingField += new Field(false)
  }


}

