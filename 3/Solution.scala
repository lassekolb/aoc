import scala.io.Source

// Exercise 1

// object Main extends App {
//   val filename = "input.txt"
//   val source = Source.fromFile(filename)
//   val text: String = source.mkString // Read the entire file as a string
//   source.close() // Close
//   val re = """mul\((\d{1,3}),(\d{1,3})\)""".r
//
//   val results = for (m <-re.findAllMatchIn(text)) yield {
//    m.group(1).toInt * m.group(2).toInt
//   }
//   println(results.sum)
// }

// Exercise 2

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val text: String = source.mkString
  source.close()

  val pattern = """(mul\((\d{1,3}),(\d{1,3})\))|(do\(\)|don't\(\))""".r

  def sumValidMultiplications(input: String): Int = {
    var enabled = true
    val matches = pattern.findAllMatchIn(input).toList

    matches.foldLeft(0) { (sum, m) =>
      m.matched match {
        case s if s.startsWith("do()")    => enabled = true; sum
        case s if s.startsWith("don't()") => enabled = false; sum
        case s if s.startsWith("mul") && enabled =>
          sum + m.group(2).toInt * m.group(3).toInt
        case _ => sum
      }
    }
  }

  val result = sumValidMultiplications(text)
  println(result) // Output the computed sum
}
