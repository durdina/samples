package helloworld

/**
 * Created by michaldurdina on 25/09/14.
 */
abstract class Tree {

  case class Sum(l: Tree, r: Tree) extends Tree

  case class Var(n: String) extends Tree

  case class Const(v: Int) extends Tree

}
