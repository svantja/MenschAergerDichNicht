package de.htwg.se.menschaergerdichnicht.model

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Anastasia on 01.05.17.
  */
case class House(tokens: Token, fields: Int) {
  val house = new ArrayBuffer[Field]

  for (i <- 1 to 4) {
    house += new Field(false)
  }
}
