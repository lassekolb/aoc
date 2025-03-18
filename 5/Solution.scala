import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val allLines = source.getLines.toList
  source.close()

  val emptyLineIndex = allLines.indexWhere(_.trim.isEmpty)

  val (firstPart, secondPart) = allLines.splitAt(emptyLineIndex)

  val cleanedSecondPart = secondPart.drop(1)

  val rules = firstPart.map(_.split("\\|").map(_.toInt).toList)
  val updates = cleanedSecondPart.map(_.split(",").map(_.toInt).toList)

  val result1 = Solution.solution1(rules, updates)
  println(result1)
}

object Solution {
  def checkRule(rule: List[Int], update: List[Int]): Boolean = {
    !(update.contains(rule(0)) && update.contains(rule(1))) || update.indexOf(
      rule(0)
    ) < update.indexOf(rule(1))
  }

  def checkRules(rules: List[List[Int]], update: List[Int]): Int = {
    if (rules.forall(rule => checkRule(rule, update))) {
      update(update.length / 2)
    } else {
      0
    }
  }
  def solution1(rules: List[List[Int]], updates: List[List[Int]]): Int = {
    updates.foldLeft(0) { (acc, update) =>
      acc + checkRules(rules, update)
    }
  }
}
