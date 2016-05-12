package org.personal.durdo.stringinterpolation

/**
  * Created by mdurdina on 12/05/16.
  * Loosely based on http://docs.scala-lang.org/overviews/core/string-interpolation.html
  */
object StringInterpolationTechniques {

  implicit class HexHelper(val sc: StringContext) extends AnyVal {
    def hex(args: Int*): String = {
      val strings = sc.parts.iterator
      val expressions = args.iterator
      val buf = new StringBuffer()
      while (strings.hasNext) {
        buf append strings.next
        if (expressions.hasNext) {
          val next = expressions.next
          buf append Integer.toHexString(next)
        }
      }
      buf.toString
    }
  }

  def main(args: Array[String]) {
    println("--- Test for format interpolator ---")
    println(f"${"James"}%-10s is ${1.8d}%10.2f m tall")
    println(f"${"Luis"}%-10s is ${10d}%10.2f m tall")
    println("--- Test for raw interpolator ---")
    println(raw"Row1\nRow2")
    println("--- Test for custom hex interpolator ---")
    println(hex"before ${123456} after")
  }
}
