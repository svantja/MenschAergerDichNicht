package de.htwg.se.menschaergerdichnicht.aview.gui

import scala.swing._
import event._
import java.awt.{Color, Graphics2D}
import java.util
import java.awt.geom.Ellipse2D

import scala.collection.mutable.ArrayBuffer
import javax.swing.{ImageIcon, JLabel, JPanel}
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage
import java.awt.Image

import de.htwg.se.menschaergerdichnicht.model.{Players, Token, Player}

/**
  * Created by svloeger on 14.06.2017.
  */

// klick auf Würfel == "ready" in Tui

// klick auf Token == "move" Token in Tui




case class SwingGui(var players: Players) extends MainFrame{

  // (x, y)
  val PLAYING_FIELD = ArrayBuffer((30, 230), (80, 230), (130, 230),(180, 230), (230, 230), // 1.Reihe
    (230, 180), (230, 130), (230, 80), //2.Reihe
    (230, 30), (280, 30), (330, 30),
    (330, 80), (330, 130), (330, 180), (330, 230),
    (380, 230), (430, 230), (480, 230),
    (530, 230), (530, 280), (530, 330),
    (480, 330), (430, 330), (380, 330), (330, 330),
    (330, 380), (330, 430), (330, 480), (330, 530),
    (280, 530), (230, 530), // 9.Reihe
    (230, 480), (230, 430), (230, 380), (230, 330),
    (180, 330), (130, 330), (80, 330), (30, 330), (30, 280))

  var HOMEFIELDPLAYERONE = ArrayBuffer((30, 30, true), (30, 80, true), (80, 80, true), (80, 30, true))

  var HOMEFIELDPLAYERTWO = ArrayBuffer((480, 30, true), (530, 30, true), (530, 80, true), (480, 80, true))

  var HOMEFIELDPLAYERTHREE = ArrayBuffer((30, 480, true), (30, 530, true), (80, 530, true), (80, 480, true))
  var HOMEFIELDPLAYERFOUR = ArrayBuffer((480, 480, true), (530, 480, true), (530, 530, true), (480, 530, true))



  trait HomeFieldOne extends Panel{
    override protected def paintComponent(g: Graphics2D): Unit ={
      paintBackground(g)
      setPosition(g, players)
      //setPositionOne(g, players)
    }
    def paintBackground(g: Graphics2D): Unit = {
      g.setBackground(Color.LIGHT_GRAY)
      g.setColor(Color.RED)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(0)._1,HOMEFIELDPLAYERONE(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(1)._1,HOMEFIELDPLAYERONE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(2)._1,HOMEFIELDPLAYERONE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(3)._1,HOMEFIELDPLAYERONE(3)._2, 40.0, 40.0))

      g.setColor(Color.BLUE)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(0)._1,HOMEFIELDPLAYERTWO(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(1)._1,HOMEFIELDPLAYERTWO(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(2)._1,HOMEFIELDPLAYERTWO(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTWO(3)._1,HOMEFIELDPLAYERTWO(3)._2, 40.0, 40.0))

      g.setColor(Color.YELLOW)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTHREE(0)._1,HOMEFIELDPLAYERTHREE(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTHREE(1)._1,HOMEFIELDPLAYERTHREE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTHREE(2)._1,HOMEFIELDPLAYERTHREE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERTHREE(3)._1,HOMEFIELDPLAYERTHREE(3)._2, 40.0, 40.0))

      g.setColor(Color.GREEN)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERFOUR(0)._1,HOMEFIELDPLAYERFOUR(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERFOUR(1)._1,HOMEFIELDPLAYERFOUR(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERFOUR(2)._1,HOMEFIELDPLAYERFOUR(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERFOUR(3)._1,HOMEFIELDPLAYERFOUR(3)._2, 40.0, 40.0))

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
          // Field, Int
          color match {
            case "red" => {
              for (token <- tokens) {
                val pos = token.getPosition()
                if (token.counter != 0) {g.drawImage(bi, PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)}
                else if(token.counter == 0) {g.drawImage(bi, HOMEFIELDPLAYERONE(pos._2)._1 + 5, HOMEFIELDPLAYERONE(pos._2)._2 + 5, null)}
              }
            }
            case "blue" => {
              for (token <- tokens) {
                val pos = token.getPosition()
                if (token.counter != 0) {g.drawImage(bi, PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)}
                else if(token.counter == 0) {g.drawImage(bi, HOMEFIELDPLAYERTWO(pos._2)._1 + 5, HOMEFIELDPLAYERTWO(pos._2)._2 + 5, null)}
              }
            }
            case "yellow" => {
              for (token <- tokens) {
                val pos = token.getPosition()
                if (token.counter != 0) {g.drawImage(bi, PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)}
                else {g.drawImage(bi, HOMEFIELDPLAYERTHREE(pos._2)._1 + 5, HOMEFIELDPLAYERTHREE(pos._2)._2 + 5, null)}
              }
            }
            case "green" => {
              for (token <- tokens) {
                val pos = token.getPosition()
                if (token.counter != 0) g.drawImage(bi, PLAYING_FIELD(pos._2)._1 + 5, PLAYING_FIELD(pos._2)._2 + 5, null)
                else {g.drawImage(bi, HOMEFIELDPLAYERFOUR(pos._2)._1 + 5, HOMEFIELDPLAYERFOUR(pos._2)._2 + 5, null)}
              }
            }
          }
        }
    }

    def setFirstPosition(d: Graphics2D, player: Player): Unit ={
      val tokens = player.getTokens()
      val color = tokens(0).getColor()
      val bufferedImage = ImageIO.read(new File("..\\MenschAergerDichNicht\\tokens\\" + color + ".png"))

      val bi = bufferedImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH)

      if (null != bufferedImage) {
        color match {
          case "red" => {
            for (token <- tokens) {
              d.drawImage(bi, HOMEFIELDPLAYERONE(token.getPosition()._2)._1, HOMEFIELDPLAYERONE(token.getPosition()._2)._2, null)
            }
          }
          case "yellow" => {
            for (token <- tokens) {
              d.drawImage(bi, HOMEFIELDPLAYERFOUR(token.getPosition()._2)._1, HOMEFIELDPLAYERFOUR(token.getPosition()._2)._2, null)
            }
          }
          case "green" => {
              for (token <- tokens) {
                d.drawImage(bi, HOMEFIELDPLAYERTHREE(token.getPosition()._2)._1, HOMEFIELDPLAYERTHREE(token.getPosition()._2)._2, null)
            }
          }
          case "blue" => {
              for (token <- tokens) {
                d.drawImage(bi, HOMEFIELDPLAYERTWO(token.getPosition()._2)._1, HOMEFIELDPLAYERTWO(token.getPosition()._2)._2, null)
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

    var panel = new Panel with HomeFieldOne//with Image//with WhiteBackground with HorizontalLines with VerticalLines
      //g.drawImage(icon, 0, 0, this)
    contents ++= panel :: Nil
  }
  //var c = new HomeFieldOne {}
  //c.repaint()

}

object GuiProgramOne {
    println("End of main function")
}