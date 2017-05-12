package de.htwg.se.menschaergerdichnicht.model

case class Player(name: String, farbe:String, reihenfolge:Int) {
  def f(x: Int) : Int = x + 1
  def get_all_figures(farbe:String): Array[Token] ={
    var f1 = Token(farbe, 1, Field(0, 0, farbe))
    var f2 = Token(farbe, 2, Field(1, 0, farbe))
    var f3 = Token(farbe, 3, Field(2, 0, farbe))
    var f4 = Token(farbe, 4, Field(3, 0, farbe))
    Array(f1, f2, f3, f4)
  }
}

