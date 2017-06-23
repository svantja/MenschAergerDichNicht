package de.htwg.se.menschaergerdichnicht.model

case class Token(var player: Player, var position: (Field, Int), var counter: Int) {

  // player token numbers 1 - 4
  val number = Token.setNumber

  // unique id
  val tokenId = Token.newIdNum

  val color = Token.setColor

  var finished: Boolean = false

  def getColor(): Any = color

  def setPlayer(player: Player) { this.player = player}

  def getPlayer(): Player = player

  def setPosition(position: (Field, Int)) { this.position = position}

  def getPosition(): (Field, Int) = position

  def setCounter(counter: Int) { this.counter = counter}

  def getCounter(): Int = counter

  def setFinished(finished: Boolean) { this.finished = finished}

  def getFinished(): Boolean = finished

  //def getTokenById()

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
// 1,2,3,4 -> red
// 5 % 5 = 0  5,6,7,8 -> blue
// 9 % 5 = 4