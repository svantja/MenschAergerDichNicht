package de.htwg.se.menschaergerdichnicht.model

/**
  * Created by Anastasia on 01.05.17.
  */


case class Field(occupied: Boolean) {

  val houseField = Array.ofDim[Int](2, 4);

  val targetField = new Array[Int](4);

  val playingField = new Array[Int](40);




}
