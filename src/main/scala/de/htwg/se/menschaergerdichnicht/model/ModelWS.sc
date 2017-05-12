import de.htwg.se.menschaergerdichnicht.model.{Field, Team}

import scala.collection.mutable.ArrayBuffer;



print("hello")

val playingField = new ArrayBuffer[Field]

for (i <- 1 to 40) {
  playingField += new Field(false)
}

val x = playingField.length


for (x <- playingField) {
    print(x)
}

class TargetField() {

  val targetField = new ArrayBuffer[Field]

  for (i <- 1 to 4) {
    targetField += new Field(false)
  }

}

val targetField = new TargetField()

targetField.targetField(1)

case class Player(name: String, team: Team) {


  def selectTeam(color: String): Team = {}


  override def toString: String = name
}

val player1 = new Player("ana", new Team())







