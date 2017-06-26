package de.htwg.se.menschaergerdichnicht.model.fileIoComponent

import de.htwg.se.menschaergerdichnicht.model.fieldComponent.PlayingInterface

/**
  * Created by Anastasia on 26.06.17.
  */
trait FileIoInterface {
  def load: PlayingInterface
  def save(c: PlayingInterface): Unit
}
