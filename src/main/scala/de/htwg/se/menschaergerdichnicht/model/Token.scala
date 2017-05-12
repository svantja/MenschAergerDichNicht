package de.htwg.se.menschaergerdichnicht.model

case class Token(position: Int, area: Int) {
  def tokens: List[String] = List("token1", "token2", "token3", "token4")
}