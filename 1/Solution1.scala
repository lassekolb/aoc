import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val lines = source.getLines.toList
  val (list1, list2) = lines
    .map(_.split("   ").map(_.toInt).toList) 
    .map {
      case List(a, b) => (a, b)
      case _          => throw new IllegalArgumentException
    } 
    .unzip 
  val frequencyMap: Map[Int, Int] =
    list2.groupBy(identity).view.mapValues(_.size).toMap

  val list = list1.map { num =>
    val freq = frequencyMap.getOrElse(num, 0)
    num * freq
  }
  println(list.sum)
}
