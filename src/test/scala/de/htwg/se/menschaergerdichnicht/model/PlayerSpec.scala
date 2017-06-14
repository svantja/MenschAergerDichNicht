package de.htwg.se.menschaergerdichnicht.model

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by svloeger on 13.06.2017.
  */
class PlayerSpec extends FlatSpec with Matchers{

  val player = new Player("test", 0)

  "A Player" should "have an ID" in{
    player.playerId
  }
  it should "be between 1 and 4" in{
    assert(player.playerId >= 1 && player.playerId <= 4)
  }

  "A Player" should "have a House" in{
    player.house
  }

  "A Player" should "have a target" in{
    player.target
  }

  "A Player" should "have finished" in{
    player.finished
  }

  "A Player" should "have tokens" in{
    player.tokens
  }



}
