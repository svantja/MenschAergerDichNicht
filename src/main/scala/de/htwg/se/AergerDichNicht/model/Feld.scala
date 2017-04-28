package de.htwg.se.AergerDichNicht.model

case class Feld (var number: Int, var typ: Int, farbe:String){
  //Homefeld -> 0 
  //Lauffeld -> 1
  //Zielfelder -> 2
  def move(field:Feld, num:Int){
    if((field.number + num) > 40 && (field.number + num) <= 44){
      change_type(field, 2)
      field.number = (field.number + num) - 40
      println(field, "gehe", num, "Felder")
    }else if((field.number + num) > 44){
      val n = 44 - field.number
      println("Es darf höchstens eine", n, "gewürfelt werden")
    }else{field.number += num; println(field, "gehe", num, "Felder")}
  }
  
  def change_type(field:Feld, x:Int){
    field.typ = x
  }
  
  def create_home_fields(farbe: String): Array[Feld] = {
    var f1 = Feld(0, 0, farbe)
    var f2 = Feld(1, 0, farbe)
    var f3 = Feld(2, 0, farbe)
    var f4 = Feld(3, 0, farbe)
    Array(f1, f2, f3, f4)
  }
}