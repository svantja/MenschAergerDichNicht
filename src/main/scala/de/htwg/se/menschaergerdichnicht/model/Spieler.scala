package de.htwg.se.menschaergerdichnicht.model

case class Spieler(name: String, team: Spielfigur, position: Int) {
  def laufe(wuerfelzahl: Int) : Int = position + wuerfelzahl
}