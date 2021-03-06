package de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl

import de.htwg.se.menschaergerdichnicht.model.fieldComponent.FieldInterface
import de.htwg.se.menschaergerdichnicht.model.playerComponent.{PlayerInterface, TokenInterface}

case class Token(var player: PlayerInterface, var position: (FieldInterface, Int), var counter: Int) extends TokenInterface {

  // player token numbers 1 - 4
  val number = Token.setNumber

  // unique id
  val tokenId = Token.newIdNum

  val color = Token.setColor

  var finished: Boolean = false

  def getColor(): Any = color

  def setPlayer(player: PlayerInterface) { this.player = player}

  def getPlayer(): PlayerInterface = player

  def setPosition(position: (FieldInterface, Int)) { this.position = position}

  def getPosition(): (FieldInterface, Int) = position

  def setCounter(counter: Int) { this.counter = counter}

  def getCounter(): Int = counter

  def setFinished(finished: Boolean) { this.finished = finished}

  def getFinished(): Boolean = finished

}

object Token{

  private var idNumber = 0
  private var round = 0
  private var index = 0
  val colorList = List("red", "blue", "green", "yellow")

  private def newIdNum = {
    idNumber += 1;
    idNumber
  }
  private def setNumber = {
    idNumber % 4 + 1
  }

  private def setColor = {
    if(idNumber <= 4) {
      colorList(0)
    }
    else if(idNumber <= 8 && idNumber >= 5){
      colorList(1)
    }
    else if(idNumber <= 12 && idNumber >= 9){
      colorList(2)
    }
    else if(idNumber <= 16 && idNumber >= 13){
      colorList(3)
    }
  }
}