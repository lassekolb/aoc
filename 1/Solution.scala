gjqjq qqqqqq/ import scala.io.Source
//
// object Main extends App {
//   val filename = "input.txt"
//   val source = Source.fromFile(filename)
//   val lines = source.getLines.toList
//   val (list1, list2) = lines
//     .map(_.split("   ").map(_.toInt).toList) // Convert each line into List[Int]
//     .map {
//       case List(a, b) => (a, b)
//       case _          => throw new IllegalArgumentException
//     } // Extract two numbers
//     .unzip // Separate into two lists
//   val distances =
//     for ((a, b) <- list1.sorted zip list2.sorted) yield math.abs(a - b)
//   val result = distances.sum
//   println(result)
// }
