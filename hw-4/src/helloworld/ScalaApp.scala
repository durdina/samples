package helloworld

import java.text.DateFormat._
import java.util.Date

/**
 * Created by michaldurdina on 19/09/14.
 */
object ScalaApp extends App {

  override def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance
    println("Hi at " + (df format now))

    val timer = new Timer()
    timer.main()

//    val tree = Var(1);
  }


}
