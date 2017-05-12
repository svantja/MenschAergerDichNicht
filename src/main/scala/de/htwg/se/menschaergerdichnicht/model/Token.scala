package de.htwg.se.menschaergerdichnicht.model


case class Token (farbe:String, number: Int, position:Field){
  def f(x: Int) : Int = x + 2
  
  def move(figur:Token, num:Int){
    if (figur.position.typ == 0 && num == 6){
      figur.position.change_type(figur.position, 1)
      figur.position.move(figur.position, num)
    }else if(figur.position.typ == 0 && num != 6){
      println("Es muss mindestens eine 6 gewürfelt werden um starten zu können")
    }else{figur.position.move(figur.position, num)} 
  }
  