import scala.io.Source

// First part
//
// object Main extends App {
//   val filename = "input.txt"
//   val source = Source.fromFile(filename)
//   val lines = source.getLines.toList
//   val reports = lines.map(
//     _.split(" ").map(_.toInt).toList
//   )
// val checkedReports1 = reports.map(report =>
//   (report == report.sorted || report.reverse == report.sorted)
// )
// // val checkReports2 =
// //   reports.zip(reports.tail).map((a, b) => 0 < math.abs(a - b) < 4)
// val checkReports2 = reports.map { report =>
//   val tupledReport = report.zip(report.tail).map { case (a, b) =>
//     math.abs(a - b) > 0 && math.abs(a - b) < 4
//   }
//   !tupledReport.contains(false)
// }
// val checkedReport = checkedReports1.zipAll(checkReports2, false, false).map {
//   case (a, b) => a && b
// }
// val checkedReport = reports.map { report =>
//   val isSorted = report == report.sorted || report.reverse == report.sorted
//
//   val allPairsValid = !report.zip(report.tail).exists { case (a, b) =>
//     math.abs(a - b) <= 0 || math.abs(a - b) >= 4
//   }
//
//   isSorted && allPairsValid
// }
// }
// println(checkedReport.map { b => if (b) 1 else 0 }.sum)

// }
// Second part of the exercise

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val lines = source.getLines.toList
  val reports = lines.map(
    _.split(" ").map(_.toInt).toList
  )
  val checkedReport = reports.map { report =>
    def isValid(r: List[Int]): Boolean = {
      val isSorted = r == r.sorted || r.reverse == r.sorted

      val allPairsValid = !r.zip(r.tail).exists { case (a, b) =>
        math.abs(a - b) <= 0 || math.abs(a - b) >= 4
      }

      isSorted && allPairsValid
    }

    if (isValid(report)) true
    else {
      report.indices.exists { i =>
        val modifiedReport =
          report.patch(i, Nil, 1)
        isValid(modifiedReport)
      }
    }
  }
  println(checkedReport.map { b => if (b) 1 else 0 }.sum)

}
