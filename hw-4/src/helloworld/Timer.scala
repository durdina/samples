package helloworld

/**
  * Created by michaldurdina on 24/09/14.
  */
class Timer {
  def oncePerSecond(callback: () => Unit) {
    var i = 0
    while (i < 5) {
      callback();
      Thread sleep 1000;
      i = i + 1
    }
  }

  def main() {
    oncePerSecond(() => println("time flies so fast..."))
  }

}
