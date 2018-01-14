package de.htwg.se.menschaergerdichnicht.aview.gui

import scala.swing._
import event._
import java.awt.{ Color, Graphics2D }
import java.util
import java.awt.geom.Ellipse2D

import scala.collection.mutable.ArrayBuffer
import javax.swing.{ ImageIcon, JButton, JLabel, JPanel }
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage
import java.awt.Image

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.ControllerInterface
import de.htwg.se.menschaergerdichnicht.model.playerComponent.playerBaseImpl.{ Player, Players }
import java.awt.Rectangle

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.GameState.{ DICED, ONGOING, PREPARE, NONE }
import de.htwg.se.menschaergerdichnicht.model.playerComponent.PlayerInterface

/**
 * Created by svloeger on 14.06.2017.
 */

// klick auf Würfel == "ready" in Tui

// klick auf Token == "move" Token in Tui

case class SwingGui(var c: ControllerInterface) extends MainFrame {

  // (x, y)
  val PLAYING_FIELD = ArrayBuffer((30, 230), (80, 230), (130, 230), (180, 230), (230, 230), // 1.Reihe
    (230, 180), (230, 130), (230, 80), //2.Reihe
    (230, 30), (280, 30), (330, 30),
    (330, 80), (330, 130), (330, 180), (330, 230),
    (380, 230), (430, 230), (480, 230),
    (530, 230), (530, 280), (530, 330), //6.
    (480, 330), (430, 330), (380, 330), (330, 330), //7.
    (330, 380), (330, 430), (330, 480), (330, 530),
    (280, 530), (230, 530), // 9.Reihe
    (230, 480), (230, 430), (230, 380), (230, 330),
    (180, 330), (130, 330), (80, 330), (30, 330), (30, 280))

  var FINISH_ONE = ArrayBuffer((80, 280), (130, 280), (180, 280), (230, 280))
  var FINISH_TWO = ArrayBuffer((280, 80), (280, 130), (280, 180), (280, 230))
  var FINISH_THREE = ArrayBuffer((480, 280), (430, 280), (380, 280), (330, 280))
  var FINISH_FOUR = ArrayBuffer((280, 480), (280, 430), (280, 380), (280, 330))

  var HOMEFIELDPLAYERONE = ArrayBuffer((30, 30), (80, 30), (30, 80), (80, 80))
  var HOMEFIELDPLAYERTWO = ArrayBuffer((480, 30), (530, 30), (530, 80), (480, 80))
  var HOMEFIELDPLAYERTHREE = ArrayBuffer((480, 480), (530, 480), (530, 530), (480, 530))
  var HOMEFIELDPLAYERFOUR = ArrayBuffer((30, 480), (80, 480), (30, 530), (80, 530))

  import javax.swing.ImageIcon
  var start_imgPoint = (680, 0)
  val start_icon = new ImageIcon(getClass().getResource("/tokens/start.png"))
  var start_image = start_icon.getImage
  start_image = start_image.getScaledInstance(200, 100, Image.SCALE_SMOOTH)

  var dicing_imgPoint = (680, 0)
  val dicing_icon = new ImageIcon(getClass().getResource("/tokens/dice.png"))
  var dicing_image = dicing_icon.getImage
  dicing_image = dicing_image.getScaledInstance(200, 100, Image.SCALE_SMOOTH)

  var add_imgPoint = (680, 180)
  val add_icon = new ImageIcon(getClass().getResource("/tokens/add.png"))
  var add_image = add_icon.getImage
  add_image = add_image.getScaledInstance(200, 100, Image.SCALE_SMOOTH)

  val current = new ArrayBuffer[Image]
  for (i <- 1 to 4) {
    val img_1 = new ImageIcon(getClass().getResource("/tokens/p" + i + ".png"))
    var image_1 = img_1.getImage
    image_1 = image_1.getScaledInstance(200, 100, Image.SCALE_SMOOTH)
    current += image_1
  }

  val red_tokens = new ArrayBuffer[Image]
  for (i <- 1 to 4) {
    val red_token = new ImageIcon(getClass().getResource("/tokens/red" + i + ".png"))
    var red_image = red_token.getImage
    red_image = red_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)
    red_tokens += red_image
  }

  val blue_tokens = new ArrayBuffer[Image]
  for (i <- 5 to 8) {
    val blue_token = new ImageIcon(getClass().getResource("/tokens/blue" + i + ".png"))
    var blue_image = blue_token.getImage
    blue_image = blue_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)
    blue_tokens += blue_image
  }

  val green_tokens = new ArrayBuffer[Image]
  for (i <- 9 to 12) {
    val green_token = new ImageIcon(getClass().getResource("/tokens/green" + i + ".png"))
    var green_image = green_token.getImage
    green_image = green_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)
    green_tokens += green_image
  }

  val yellow_tokens = new ArrayBuffer[Image]
  for (i <- 13 to 16) {
    val yellow_token = new ImageIcon(getClass().getResource("/tokens/yellow" + i + ".png"))
    var yellow_image = yellow_token.getImage
    yellow_image = yellow_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)
    yellow_tokens += yellow_image
  }

  val dice_img = new ArrayBuffer[Image]
  for (i <- 0 to 6) {
    val dice_token = new ImageIcon(getClass().getResource("/tokens/dice" + i + ".png"))
    var dice_image = dice_token.getImage
    dice_image = dice_image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)
    dice_img += dice_image
  }
  val dice_imgPoint = (680, 480)

  trait HomeFieldOne extends Panel {

    listenTo(this.mouse.clicks)
    var count = 0

    reactions += {
      case MouseClicked(src, point, i1, i2, b) => {
        if (start_image != null && start_imgPoint != null) {
          val me = point
          val start_bounds = new Rectangle(start_imgPoint._1, start_imgPoint._2, start_icon.getIconWidth, start_icon.getIconHeight)
          if (start_bounds.contains(me) && c.gameState == PREPARE) {
            c.gameState = ONGOING;
          } else if (start_bounds.contains(me) && c.gameState == ONGOING) {
            c.startGame()
          }
          this.repaint;
        }
        if (add_image != null && add_imgPoint != null) {
          val me = point
          val bounds = new Rectangle(add_imgPoint._1, add_imgPoint._2, add_icon.getIconWidth, add_icon.getIconHeight)
          if (bounds.contains(me) && i2 == 1 && (c.gameState == PREPARE || c.gameState == NONE)) c.addPlayer("a" + count.toString); count += 1; repaint;
          this.repaint;
        }
        if (red_tokens != null) {
          val me = point
          moveToken(me)
        }
        if (blue_tokens != null) {
          val me = point
          moveToken(me)
        }
        if (green_tokens != null) {
          val me = point
          moveToken(me)
        }
        if (yellow_tokens != null) {
          val me = point
          moveToken(me)
        }
      }
    }

    def moveToken(p: Point): Unit = {

      val current_player = c.players.getCurrentPlayer
      for (t <- current_player.getTokens()) {
        val me = p
        if (current_player.getDiced() != 0) {
          if (t.getCounter() != 0) {
            val bounds = new Rectangle(PLAYING_FIELD(t.getPosition()._2)._1 + 5, PLAYING_FIELD(t.getPosition()._2)._2 + 5, 30, 30)
            if (bounds.contains(me)) {
              c.chooseToken(t.tokenId)
            }
          }
          if (t.getCounter() == 0 && current_player.getDiced() == 6) {
            t.getColor() match {
              case "red" => {
                val bounds = new Rectangle(HOMEFIELDPLAYERONE(t.getPosition()._2)._1 + 5, HOMEFIELDPLAYERONE(t.getPosition()._2)._2 + 5, 30, 30)
                if (bounds.contains(me)) {
                  c.chooseToken(t.tokenId)
                }
              }
              case "blue" => {
                val bounds = new Rectangle(HOMEFIELDPLAYERTWO(t.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTWO(t.getPosition()._2)._2 + 5, 30, 30)
                if (bounds.contains(me)) {
                  c.chooseToken(t.tokenId)
                }
              }
              case "green" => {
                val bounds = new Rectangle(HOMEFIELDPLAYERTHREE(t.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTHREE(t.getPosition()._2)._2 + 5, 30, 30)
                if (bounds.contains(me)) {
                  c.chooseToken(t.tokenId)
                }
              }
              case "yellow" => {
                val bounds = new Rectangle(HOMEFIELDPLAYERFOUR(t.getPosition()._2)._1 + 5, HOMEFIELDPLAYERFOUR(t.getPosition()._2)._2 + 5, 30, 30)
                if (bounds.contains(me)) {
                  c.chooseToken(t.tokenId)
                }
              }
            }

          }
        }
        this.repaint;
      }
    }

    override protected def paintComponent(g: Graphics2D): Unit = {
      paintBackground(g)
      setPosition(g, c.players)
      drawDice(g)
    }

    def drawDice(g: Graphics2D): Unit = {
      this.repaint;
      if (c.players.players.length != 0) {
        c.players.getCurrentPlayer.getDiced() match {
          case 0 => g.drawImage(dice_img(0), dice_imgPoint._1, dice_imgPoint._2, null)
          case 1 => g.drawImage(dice_img(1), dice_imgPoint._1, dice_imgPoint._2, null)
          case 2 => g.drawImage(dice_img(2), dice_imgPoint._1, dice_imgPoint._2, null)
          case 3 => g.drawImage(dice_img(3), dice_imgPoint._1, dice_imgPoint._2, null)
          case 4 => g.drawImage(dice_img(4), dice_imgPoint._1, dice_imgPoint._2, null)
          case 5 => g.drawImage(dice_img(5), dice_imgPoint._1, dice_imgPoint._2, null)
          case 6 => g.drawImage(dice_img(6), dice_imgPoint._1, dice_imgPoint._2, null)
        }
      }

    }

    def paintBackground(g: Graphics2D): Unit = {
      if (c.gameState == PREPARE || c.gameState == NONE) {
        g.drawImage(start_image, start_imgPoint._1, start_imgPoint._2, null)
        g.drawImage(add_image, add_imgPoint._1, add_imgPoint._2, null)
      } else if (c.gameState == ONGOING || c.gameState == DICED) {
        g.drawImage(dicing_image, start_imgPoint._1, start_imgPoint._2, null)
        g.drawImage(current(c.players.getCurrentPlayer.playerId - 1), add_imgPoint._1, add_imgPoint._2, null)
      }
      g.setBackground(Color.LIGHT_GRAY)

      g.setColor(Color.RED)
      for (field <- HOMEFIELDPLAYERONE) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }
      for (field <- FINISH_ONE) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }

      g.setColor(Color.BLUE)
      for (field <- HOMEFIELDPLAYERTWO) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }
      for (field <- FINISH_TWO) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }

      g.setColor(Color.GREEN)
      for (field <- HOMEFIELDPLAYERTHREE) { //müsste p3 sein
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }
      for (field <- FINISH_THREE) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }

      g.setColor(Color.YELLOW)
      for (field <- HOMEFIELDPLAYERFOUR) { //müsste p4 sein
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }
      for (field <- FINISH_FOUR) {
        g.fill(new Ellipse2D.Double(field._1, field._2, 40.0, 40.0))
      }

      for (i <- 0 to PLAYING_FIELD.length - 1) {
        if (i == 0) {
          g.setColor(Color.pink)
        } else if (i == 10) {
          g.setColor(Color.cyan)
        } else if (i == 20) {
          g.setColor(Color.darkGray)
        } else if (i == 30) {
          g.setColor(Color.orange)
        } else {
          g.setColor(Color.white)
        }
        g.fill(new Ellipse2D.Double(PLAYING_FIELD(i)._1, PLAYING_FIELD(i)._2, 40.0, 40.0))
      }
    }

    def setPosition(g: Graphics2D, players: Players): Unit = {
      for (p <- players.getAllPlayer) {
        if (p.house.isFull(p)) { setFirstPosition(g, p) }
        else { setPositionOne(g, p) }
      }

      def setPositionOne(g: Graphics2D, player: PlayerInterface): Unit = {
        val tokens = player.getTokens()
        val color = tokens(0).getColor()

        for (token <- tokens) {
          val pos = token.getPosition()
          if (token.counter != 0 && token.counter < 41) {
            color match {
              case "red" => g.drawImage(red_tokens(token.tokenId - 1), PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)
              case "blue" => g.drawImage(blue_tokens(token.tokenId - 5), PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)
              case "yellow" => g.drawImage(yellow_tokens(token.tokenId - 13), PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)
              case "green" => g.drawImage(green_tokens(token.tokenId - 9), PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)
            }
          } else if (token.counter == 0) {
            color match {
              case "red" => g.drawImage(red_tokens(token.tokenId - 1), HOMEFIELDPLAYERONE(pos._2)._1 + 5, HOMEFIELDPLAYERONE(pos._2)._2 + 5, null)
              case "blue" => g.drawImage(blue_tokens(token.tokenId - 5), HOMEFIELDPLAYERTWO(pos._2)._1 + 5, HOMEFIELDPLAYERTWO(pos._2)._2 + 5, null)
              case "yellow" => g.drawImage(yellow_tokens(token.tokenId - 13), HOMEFIELDPLAYERFOUR(pos._2)._1 + 5, HOMEFIELDPLAYERFOUR(pos._2)._2 + 5, null)
              case "green" => g.drawImage(green_tokens(token.tokenId - 9), HOMEFIELDPLAYERTHREE(pos._2)._1 + 5, HOMEFIELDPLAYERTHREE(pos._2)._2 + 5, null)
            }
          } else if (token.counter >= 41) {
            color match {
              case "red" => g.drawImage(red_tokens(token.tokenId - 1), FINISH_ONE(pos._2)._1 + 5, FINISH_ONE(pos._2)._2 + 5, null)
              case "blue" => g.drawImage(blue_tokens(token.tokenId - 5), FINISH_TWO(pos._2)._1 + 5, FINISH_TWO(pos._2)._2 + 5, null)
              case "yellow" => g.drawImage(yellow_tokens(token.tokenId - 13), FINISH_FOUR(pos._2)._1 + 5, FINISH_FOUR(pos._2)._2 + 5, null)
              case "green" => g.drawImage(green_tokens(token.tokenId - 9), FINISH_THREE(pos._2)._1 + 5, FINISH_THREE(pos._2)._2 + 5, null)
            }
          }
        }

      }

      def setFirstPosition(d: Graphics2D, player: PlayerInterface): Unit = {
        val tokens = player.getTokens()
        val color = tokens(0).getColor()

        color match {
          case "red" => {
            for (token <- tokens) {
              d.drawImage(red_tokens(token.tokenId - 1), HOMEFIELDPLAYERONE(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERONE(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "yellow" => {
            for (token <- tokens) {
              d.drawImage(yellow_tokens(token.tokenId - 13), HOMEFIELDPLAYERFOUR(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERFOUR(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "green" => {
            for (token <- tokens) {
              d.drawImage(green_tokens(token.tokenId - 9), HOMEFIELDPLAYERTHREE(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTHREE(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "blue" => {
            for (token <- tokens) {
              d.drawImage(blue_tokens(token.tokenId - 5), HOMEFIELDPLAYERTWO(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTWO(token.getPosition()._2)._2 + 5, null)
            }
          }
        }
      }
    }

  }
  title = "Mensch Ärger Dich nicht"
  preferredSize = new Dimension(960, 890)

  contents = new GridPanel(1, 2) {
    var panel = new Panel with HomeFieldOne
    contents ++= panel :: Nil
  }

}

object GuiProgramOne {
  println("End of main function")
}