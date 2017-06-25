package de.htwg.se.menschaergerdichnicht.aview.gui

import scala.swing._
import event._
import java.awt.{Color, Graphics2D}
import java.util
import java.awt.geom.Ellipse2D

import scala.collection.mutable.ArrayBuffer
import javax.swing.{ImageIcon, JButton, JLabel, JPanel}
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage
import java.awt.Image

import de.htwg.se.menschaergerdichnicht.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.menschaergerdichnicht.model.playerComponent.{Player, Players, Token}

/**
  * Created by svloeger on 14.06.2017.
  */

// klick auf Würfel == "ready" in Tui

// klick auf Token == "move" Token in Tui




case class SwingGui(var c: Controller) extends MainFrame{

  // (x, y)
  val PLAYING_FIELD = ArrayBuffer((30, 230), (80, 230), (130, 230),(180, 230), (230, 230), // 1.Reihe
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
  var FINISH_TWO = ArrayBuffer((280, 80), (280, 130), (280,180), (280,230))
  var FINISH_THREE = ArrayBuffer((480, 280), (430, 280), (380, 280), (330, 280))
  var FINISH_FOUR = ArrayBuffer((280, 480), (280, 430), (280, 380), (280, 330))

  var HOMEFIELDPLAYERONE = ArrayBuffer((30, 30), (30, 80), (80, 80), (80, 30))
  var HOMEFIELDPLAYERTWO = ArrayBuffer((480, 30), (530, 30), (530, 80), (480, 80))
  var HOMEFIELDPLAYERTHREE = ArrayBuffer((480, 480), (530, 480), (530, 530), (480, 530))
  var HOMEFIELDPLAYERFOUR = ArrayBuffer((30, 480), (30, 530), (80, 530), (80, 480))



  trait HomeFieldOne extends Panel{
    override protected def paintComponent(g: Graphics2D): Unit ={
      paintBackground(g)
      setPosition(g, c.players)
      //setPositionOne(g, players)
    }
    def paintBackground(g: Graphics2D): Unit = {
      g.setBackground(Color.LIGHT_GRAY)
      g.setColor(Color.RED)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(0)._1,HOMEFIELDPLAYERONE(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(1)._1,HOMEFIELDPLAYERONE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(2)._1,HOMEFIELDPLAYERONE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(3)._1,HOMEFIELDPLAYERONE(3)._2, 40.0, 40.0))

      g.fill(new Ellipse2D.Double(FINISH_ONE(0)._1, FINISH_ONE(0)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_ONE(1)._1, FINISH_ONE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_ONE(2)._1, FINISH_ONE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_ONE(3)._1, FINISH_ONE(3)._2, 40.0, 40.0))

      g.setColor(Color.BLUE)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(0)._1,HOMEFIELDPLAYERTWO(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(1)._1,HOMEFIELDPLAYERTWO(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(2)._1,HOMEFIELDPLAYERTWO(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(3)._1,HOMEFIELDPLAYERTWO(3)._2, 40.0, 40.0))

      g.fill(new Ellipse2D.Double(FINISH_TWO(0)._1, FINISH_TWO(0)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_TWO(1)._1, FINISH_TWO(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_TWO(2)._1, FINISH_TWO(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_TWO(3)._1, FINISH_TWO(3)._2, 40.0, 40.0))

      g.setColor(Color.GREEN)
      for (field <- HOMEFIELDPLAYERTHREE) { //müsste p3 sein
        g.fill(new Ellipse2D.Double(field._1,field._2 , 40.0, 40.0))
      }
      g.fill(new Ellipse2D.Double(FINISH_THREE(0)._1, FINISH_THREE(0)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_THREE(1)._1, FINISH_THREE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_THREE(2)._1, FINISH_THREE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_THREE(3)._1, FINISH_THREE(3)._2, 40.0, 40.0))

      g.setColor(Color.YELLOW)
      for (field <- HOMEFIELDPLAYERFOUR) { //müsste p4 sein
        g.fill(new Ellipse2D.Double(field._1,field._2 , 40.0, 40.0))
      }
      g.fill(new Ellipse2D.Double(FINISH_FOUR(0)._1, FINISH_FOUR(0)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_FOUR(1)._1, FINISH_FOUR(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_FOUR(2)._1, FINISH_FOUR(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(FINISH_FOUR(3)._1, FINISH_FOUR(3)._2, 40.0, 40.0))

      for (i <- 0 to PLAYING_FIELD.length -1) {
        if (i == 0) {
          g.setColor(Color.pink)
        } else if (i == 10) {
          g.setColor(Color.cyan)
        } else if (i == 20) {
          g.setColor(Color.darkGray)
        } else if ( i == 30) {
          g.setColor(Color.orange)
        } else {
          g.setColor(Color.white)
        }
        g.fill(new Ellipse2D.Double(PLAYING_FIELD(i)._1,PLAYING_FIELD(i)._2 , 40.0, 40.0))
      }
    }
    //TODO: set position checks whether the house of each player is full -> setFirstPosition() -> paints all token into house
    //TODO: or paints tokens onto specified field (index) or homefield
    def setPosition(g: Graphics2D, players: Players): Unit = {
      for (p <- players.getAllPlayer) {
        if (p.house.isFull(p)) {setFirstPosition(g, p)}
        else {setPositionOne(g, p)}
      }

    def setPositionOne(g: Graphics2D, player: Player): Unit = {
        val tokens = player.getTokens()
        val color = tokens(0).getColor()
        val bufferedImage = ImageIO.read(new File("..\\MenschAergerDichNicht\\tokens\\" + color + ".png"))
        val bi = bufferedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH)

        if (null != bufferedImage) {
          for (token <- tokens) {
            val pos = token.getPosition()
            if (token.counter != 0 && token.counter < 41) {g.drawImage(bi, PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)}
            else if(token.counter == 0) {
              color match {
                case "red" => g.drawImage(bi, HOMEFIELDPLAYERONE(pos._2)._1 + 5, HOMEFIELDPLAYERONE(pos._2)._2 + 5, null)
                case "blue" => g.drawImage(bi, HOMEFIELDPLAYERTWO(pos._2)._1 + 5, HOMEFIELDPLAYERTWO(pos._2)._2 + 5, null)
                case "yellow" => g.drawImage(bi, HOMEFIELDPLAYERFOUR(pos._2)._1 + 5, HOMEFIELDPLAYERFOUR(pos._2)._2 + 5, null)
                case "green" => g.drawImage(bi, HOMEFIELDPLAYERTHREE(pos._2)._1 + 5, HOMEFIELDPLAYERTHREE(pos._2)._2 + 5, null)
              }
            }
            else if(token.counter >= 41) {
              color match {
                case "red" => g.drawImage(bi, FINISH_ONE(pos._2)._1 + 5, FINISH_ONE(pos._2)._2 + 5, null)
                case "blue" => g.drawImage(bi, FINISH_TWO(pos._2)._1 + 5, FINISH_TWO(pos._2)._2 + 5, null)
                case "yellow" => g.drawImage(bi, FINISH_FOUR(pos._2)._1 + 5, FINISH_FOUR(pos._2)._2 + 5, null)
                case "green" => g.drawImage(bi, FINISH_THREE(pos._2)._1 + 5, FINISH_THREE(pos._2)._2 + 5, null)
              }
            }
          }
        }
    }

    def setFirstPosition(d: Graphics2D, player: Player): Unit ={
      val tokens = player.getTokens()
      val color = tokens(0).getColor()
      val bufferedImage = ImageIO.read(new File("..\\MenschAergerDichNicht\\tokens\\" + color + ".png"))
      val bi = bufferedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH)

      if (null != bufferedImage) {
        color match {
          case "red" => {
            for (token <- tokens) {
              d.drawImage(bi, HOMEFIELDPLAYERONE(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERONE(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "yellow" => {
            for (token <- tokens) {
              d.drawImage(bi, HOMEFIELDPLAYERFOUR(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERFOUR(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "green" => {
              for (token <- tokens) {
                d.drawImage(bi, HOMEFIELDPLAYERTHREE(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTHREE(token.getPosition()._2)._2 + 5, null)
            }
          }
          case "blue" => {
              for (token <- tokens) {
                d.drawImage(bi, HOMEFIELDPLAYERTWO(token.getPosition()._2)._1 + 5, HOMEFIELDPLAYERTWO(token.getPosition()._2)._2 + 5, null)
              }
            }
          }
        }
      }
    }
  }
  title = "Mensch Ärger Dich nicht"
  preferredSize = new Dimension(960, 890)

  contents = new GridPanel(1,2) {

    var panel = new Panel with HomeFieldOne

    contents ++= panel :: Nil
  }
}

object GuiProgramOne {
    println("End of main function")
}