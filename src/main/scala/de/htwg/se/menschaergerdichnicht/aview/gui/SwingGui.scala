package de.htwg.se.menschaergerdichnicht.aview.gui

import scala.swing._
import event._
import java.awt.{Color, Graphics2D}
import java.util

/**
  * Created by svloeger on 14.06.2017.
  */

// klick auf Würfel == "ready" in Tui

// klick auf Token == "move" Token in Tui

trait WhiteBackground { this: Panel => background = Color.white}
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

trait Circle extends Panel {
  override protected def paintComponent(g: Graphics2D): Unit ={
    g.setColor(Color.RED)
    g.fill(new Ellipse2D.Double(30.0, 30.0, 40.0, 40.0))
    g.fill(new Ellipse2D.Double(230.0, 380.0, 40.0, 40.0))
  }
}

class SwingGui extends MainFrame{
  title = "Mensch Ärger Dich nicht"
  preferredSize = new Dimension(320, 240)
  contents = new GridPanel(1,2) {
    val panel = new Panel with WhiteBackground with HorizontalLines with VerticalLines with Circle

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