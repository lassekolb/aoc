import scala.io.Source

object Main extends App {
  val filename = "input.txt"
  val source = Source.fromFile(filename)
  source.close()
  val lines = source.getLines.toList
  val matrix = lines.map(_.toArray).toArray
  val result = Solution.countXMAS(matrix)
  println(result)
}

object Solution {
  def countXMAS(matrix: Array[Array[Char]]): Int = {
    val rows = matrix.length
    val cols = if (rows > 0) matrix(0).length else 0
    var count = 0

    def inBounds(x: Int, y: Int): Boolean =
      x >= 0 && x < rows && y >= 0 && y < cols

    for {
      x <- 0 until rows
      y <- 0 until cols
      if matrix(x)(y) == 'A'
    } {
      val isValidX = (
        // Check first diagonal (Up-Left to Down-Right)
        inBounds(x - 1, y - 1) && inBounds(x + 1, y + 1) &&
          (
            (matrix(x - 1)(y - 1) == 'M' && matrix(x + 1)(y + 1) == 'S') ||
              (matrix(x - 1)(y - 1) == 'S' && matrix(x + 1)(y + 1) == 'M')
          ) &&
          // Check second diagonal (Up-Right to Down-Left)
          inBounds(x - 1, y + 1) && inBounds(x + 1, y - 1) &&
          (
            (matrix(x - 1)(y + 1) == 'M' && matrix(x + 1)(y - 1) == 'S') ||
              (matrix(x - 1)(y + 1) == 'S' && matrix(x + 1)(y - 1) == 'M')
          )
      )

      // If both diagonals are valid, count this as one X-MAS
      if (isValidX) count += 1
    }

    count
  }
}
