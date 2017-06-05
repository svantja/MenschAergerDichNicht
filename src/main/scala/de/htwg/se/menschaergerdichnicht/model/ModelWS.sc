import de.htwg.se.menschaergerdichnicht.model.{Field, Player, Team, Token}

import scala.collection.mutable.ArrayBuffer


print("hello")




case class Token() {

  // player token numbers 1 - 4
  val number = Token.setNumber

  // unique id
  val tokenId = Token.newIdNum

  val color = Token.setColor


  def getColor(): Any = color

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

val token = new Token
val tokenf = new Token
val token1 = new Token()

token1.tokenId
token1.number
token1.color

val token2 = new Token()

token2.tokenId
token2.number
token2.color

val token3 = new Token()

token3.tokenId
token3.number
token3.color
val token4 = new Token()

token4.tokenId
token4.number
token4.color












