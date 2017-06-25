package de.htwg.se.menschaergerdichnicht.controller.controllerComponent

/**
  * Created by Anastasia on 25.06.17.
  */
object GameState extends Enumeration{
  type GameState = Value
  val ONGOING, FINISHED = Value

  val map = Map[GameState, String](
    ONGOING -> "",
    FINISHED -> "Game is finished!"
  )

  def message(gameState: GameState): Unit = {
    map(gameState)
  }
}
