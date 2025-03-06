import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val lines = source.getLines.toList
  val list = lines.map(
    _.split("   ").map(_.toInt).toList
  )
}
