package de.htwg.se.menschaergerdichnicht.model

case class Spieler(name: String, farbe: String, var position: Int) {
  def laufe(wuerfelzahl: Int) : Int = position + wuerfelzahl
}