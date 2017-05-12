package de.htwg.se.menschaergerdichnicht.model

case class Field (var number: Int, var typ: Int, farbe:String){
  //Homefeld -> 0 
  //Lauffeld -> 1
  //Zielfelder -> 2
  def move(field:Field, num:Int){
    if((field.number + num) > 40 && (field.number + num) <= 44){
      change_type(field, 2)
      field.number = (field.number + num) - 40
      println(field, "gehe", num, "Felder")
    }else if((field.number + num) > 44){
      val n = 44 - field.number
      println("Es darf höchstens eine", n, "gewürfelt werden")
    }else{field.number += num; println(field, "gehe", num, "Felder")}
  }
  
  def change_type(field:Field, x:Int){
    field.typ = x
  }
  
  def create_home_fields(farbe: String): Array[Field] = {
    var f1 = Field(0, 0, farbe)
    var f2 = Field(1, 0, farbe)
    var f3 = Field(2, 0, farbe)
    var f4 = Field(3, 0, farbe)
    Array(f1, f2, f3, f4)
  }
}