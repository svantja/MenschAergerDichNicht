package de.htwg.se.menschaergerdichnicht.model

case class Token(var player: Player, var position: Field) {

  // player token numbers 1 - 4
  val number = Token.setNumber

  // unique id
  val tokenId = Token.newIdNum

  val color = Token.setColor


  def getColor(): Any = color

  def setPlayer(player: Player) { this.player = player}

  def getPlayer(): Player = player

  def setPosition(position: Field) { this.position = position}

  def getPosition(): Field = position

  //def getTokenById()

}

object Token{

  private var idNumber = 0
  private var round = 0
  val colorList = List("red", "yellow", "blue", "green")

  private def newIdNum = {
    idNumber += 1;
    idNumber
  }
  private def setNumber = {
    idNumber % 4 + 1
  }

  private def setColor = {

    if (idNumber % 4 == 0) round += 1

    colorList(round)
  }
}