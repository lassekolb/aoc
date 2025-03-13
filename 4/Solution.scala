import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  val lines = source.getLines.toList
  source.close()
  val matrix = lines.map(_.toArray).toArray
  val result = Solution.countXMAS(matrix)
  println(result)
}

object Solution {
  def countXMAS(matrix: Array[Array[Char]]): Int = {
    val directions = List(
      (0, 1), // Right
      (1, 0), // Down
      (0, -1), // Left
      (-1, 0), // Up
      (1, 1), // Down-Right
      (-1, -1), // Up-Left
      (1, -1), // Down-Left
      (-1, 1) // Up-Right
    )
    val target = "XMAS".toList
    val rows = matrix.length
    val cols = if (rows > 0) matrix(0).length else 0
    var count = 0

    def inBounds(x: Int, y: Int): Boolean =
      x >= 0 && x < rows && y >= 0 && y < cols

    def checkDirection(x: Int, y: Int, dx: Int, dy: Int): Boolean = {
      (1 until target.length).forall { i =>
        val newX = x + i * dx
        val newY = y + i * dy
        inBounds(newX, newY) && matrix(newX)(newY) == target(i)
      }
    }

    for {
      x <- 0 until rows
      y <- 0 until cols
      if matrix(x)(y) == 'X'
      (dx, dy) <- directions
      if checkDirection(x, y, dx, dy)
    } count += 1

    count
  }
}
