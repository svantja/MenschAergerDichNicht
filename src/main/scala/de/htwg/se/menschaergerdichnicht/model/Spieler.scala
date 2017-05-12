package de.htwg.se.menschaergerdichnicht.model

case class Spieler(name: String, farbe:String, reihenfolge:Int) {
  def f(x: Int) : Int = x + 1
  def get_all_figures(farbe:String): Array[Figur] ={
    var f1 = Figur(farbe, 1, Feld(0, 0, farbe))
    var f2 = Figur(farbe, 2, Feld(1, 0, farbe))
    var f3 = Figur(farbe, 3, Feld(2, 0, farbe))
    var f4 = Figur(farbe, 4, Feld(3, 0, farbe))
    Array(f1, f2, f3, f4)
  }
}

