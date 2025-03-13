import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val allLines = source.getLines.toList
  source.close()

  val emptyLineIndex = allLines.indexWhere(_.trim.isEmpty)

  // Split the list into two parts at the empty line index
  val (firstPart, secondPart) = allLines.splitAt(emptyLineIndex)

  // Remove the empty line from the second part
  val cleanedSecondPart = secondPart.drop(1)

  // Convert the lines in each part into lists of integers
  val list1 = firstPart.map(_.split("\\|").map(_.toInt).toList)
  val list2 = cleanedSecondPart.map(_.split(",").map(_.toInt).toList)

  // Example printing to verify the output
  println("List 1:")
  list1.foreach(println)

  println("List 2:")
  list2.foreach(println)
}

object Solution {
  def solution1(rules: List[Int], updates: List[Int]): Int = {
    for {
      update <- updates
      rule <- update

    }
  }
}
