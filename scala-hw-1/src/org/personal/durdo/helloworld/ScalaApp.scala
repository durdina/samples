package org.personal.durdo.helloworld

import java.text.DateFormat._
import java.util.Date

/**
  * Created by michaldurdina on 19/09/14.
  */
object ScalaApp {

  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance
    println("Hi at " + (df format now))

    val timer = new Timer()
    timer.main()
  }


}
