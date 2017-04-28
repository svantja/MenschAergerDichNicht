package de.htwg.se.menschaergerdichnicht.model

object ModelWorksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
    class wuerfel() {
  	def wuerfeln() : Int = {
  		var n = 0;
  		val r = scala.util.Random;
  		do {
  			n = r.nextInt(7)
  		}
  		while(n == 0)
  		return n
  	}
  }
  
}