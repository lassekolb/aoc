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

  // val result1 = Solution.solution1(rules, updates)
  // println(result1)

  val result2 =
    Solution.solution2(rules, updates) - Solution.solution1(rules, updates)
  println(result2)
}

object Solution {
  def checkRule(rule: List[Int], update: List[Int]): Boolean = {
    !(update.contains(rule(0)) && update.contains(rule(1))) || update.indexOf(
      rule(0)
    ) < update.indexOf(rule(1))
  }
  def checkRule1(rule: List[Int], update: List[Int]): Boolean = {
    (update.contains(rule(0)) && update.contains(rule(1))) && update.indexOf(
      rule(0)
    ) > update.indexOf(rule(1))
  }

  def calculateValue(rules: List[List[Int]], update: List[Int]): Int = {
    if (rules.forall(rule => checkRule(rule, update))) {
      update(update.length / 2)
    } else {
      0
    }
  }

  def calculateValue2(rules: List[List[Int]], update: List[Int]): Int = {
    if (rules.forall(rule => checkRule(rule, update))) {
      update(update.length / 2)
    } else {
      calculateValue2(rules, reorderUpdate(rules, update))
    }
  }

  def solution1(rules: List[List[Int]], updates: List[List[Int]]): Int = {
    updates.foldLeft(0) { (acc, update) =>
      acc + calculateValue(rules, update)
    }
  }

  def solution2(rules: List[List[Int]], updates: List[List[Int]]): Int = {
    updates.foldLeft(0) { (acc, update) =>
      acc + calculateValue2(rules, update)
    }
  }

  def reorderList(update: List[Int], rule: List[Int]): List[Int] = rule match {
    case List(i, j) =>
      update
        .updated(update.indexOf(i), j)
        .updated(update.indexOf(j), i)
    case _ =>
      update
  }

  def reorderUpdate(rules: List[List[Int]], update: List[Int]): List[Int] = {

    def reorder(
        currentUpdate: List[Int],
        remainingRules: List[List[Int]]
    ): List[Int] = remainingRules match {
      case Nil =>
        currentUpdate
      case rule :: rest =>
        if (checkRule1(rule, currentUpdate)) {
          val newUpdate = reorderList(currentUpdate, rule)
          reorder(newUpdate, rules)
        } else {
          reorder(currentUpdate, rest)
        }
    }

    reorder(update, rules)
  }

}
