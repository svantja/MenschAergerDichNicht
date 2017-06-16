package de.htwg.se.menschaergerdichnicht.aview.gui

import scala.swing._
import event._
import java.awt.{Color, Graphics2D}
import java.util
import java.awt.geom.Ellipse2D

import scala.collection.mutable.ArrayBuffer
import javax.swing.{ImageIcon, JPanel, JLabel}
import javax.imageio.ImageIO;
import java.io.File
import java.awt.image.BufferedImage;

/**
  * Created by svloeger on 14.06.2017.
  */

// klick auf Würfel == "ready" in Tui

// klick auf Token == "move" Token in Tui


trait WhiteBackground { this: Panel => background = Color.magenta}
  trait BlackBackground {this: Panel => background = Color.black}

trait VerticalLines extends Panel {
  override protected def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    g setColor Color.red
    for (x <- 0 until size.width by 10) g.drawLine(x, 0, x, size.height)
  }
}

trait HorizontalLines extends Panel {
  override protected def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    g setColor Color.blue
    for (y <- 0 until size.height by 10) g.drawLine(0, y, size.width, y)
  }
}

//trait Circle extends Panel {
//  override protected def paintComponent(g: Graphics2D): Unit ={
//    g.setColor(Color.RED)
//    g.fill(new Ellipse2D.Double(30.0, 30.0, 40.0, 40.0))
//    g.fill(new Ellipse2D.Double(80.0, 30.0, 40.0, 40.0))
//    g.fill(new Ellipse2D.Double(80.0, 80.0, 40.0, 40.0))
//    g.fill(new Ellipse2D.Double(30.0, 80.0, 40.0, 40.0))
//    g.fill(new Ellipse2D.Double(230.0, 380.0, 40.0, 40.0))
//  }
//}

class SwingGui extends MainFrame{
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
  val HOMEFIELDPLAYERONE = ArrayBuffer((30, 30), (30, 80), (80, 80), (80, 30))
  val START_FIELD_PONE = (30, 230)

  val HOMEFIELDPLAYERTWO = ArrayBuffer((480, 30), (530, 30), (530, 80), (480, 80))
  val START_FIELD_PTWO= (330, 30)

  val HOMEFIELDPLAYERTHREE = ArrayBuffer((30, 480), (30, 530), (80, 530), (80, 480))
  val HOMEFIELDPLAYERFOUR = ArrayBuffer((480, 480), (530, 480), (530, 530), (480, 530))

  trait HomeFieldOne extends Panel{
    override protected def paintComponent(g: Graphics2D): Unit ={
      g.setBackground(Color.LIGHT_GRAY)
      g.setColor(Color.RED)
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(0)._1,HOMEFIELDPLAYERONE(0)._2 , 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(1)._1,HOMEFIELDPLAYERONE(1)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(2)._1,HOMEFIELDPLAYERONE(2)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(HOMEFIELDPLAYERONE(3)._1,HOMEFIELDPLAYERONE(3)._2, 40.0, 40.0))
      g.fill(new Ellipse2D.Double(START_FIELD_PONE._1,START_FIELD_PONE._2, 40.0, 40.0))

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
  }
  trait Image extends Panel {
    override protected def paintComponent(g: Graphics2D): Unit = {
      val bufferedImage = ImageIO.read(new File("Z:\\5.Semester\\SE\\13751080581705229399spielfigur.png"))
      val w = bufferedImage.getWidth(null)
      val h = bufferedImage.getHeight(null)
      var bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)




      if (null != bufferedImage) g.drawImage(bufferedImage, 460, 460, null)
    }
  }
  title = "Mensch Ärger Dich nicht"
  preferredSize = new Dimension(960, 890)

  contents = new GridPanel(1,2) {
    var panel = new Panel with HomeFieldOne //with Image//with WhiteBackground with HorizontalLines with VerticalLines
      //g.drawImage(icon, 0, 0, this)
    contents ++= panel :: Nil
  }
}

object GuiProgramOne {
  def main(args: Array[String]) {
    val ui = new SwingGui
    ui.visible = true
    println("End of main function")
  }
}