import scala.io.Source
import scala.collection.mutable

// chat gpt...

object Main extends App {
  val filename = "input.txt"
  val lines = Source.fromFile(filename).getLines.toList
  // Create a mutable grid as an Array of Array of Char
  val grid = lines.map(_.toArray).toArray
  val numRows = grid.length
  val numCols = if (grid.isEmpty) 0 else grid(0).length

  // Define direction indices: 0 = up, 1 = right, 2 = down, 3 = left
  val dirOffsets = Array((-1, 0), (0, 1), (1, 0), (0, -1))

  // Helper to check if a coordinate is within bounds
  def inBounds(row: Int, col: Int): Boolean =
    row >= 0 && row < numRows && col >= 0 && col < numCols

  // Find the initial guard position and starting direction from the grid
  var startRow = -1
  var startCol = -1
  var dir = -1

  for (i <- 0 until numRows; j <- 0 until numCols) {
    grid(i)(j) match {
      case '^' => startRow = i; startCol = j; dir = 0
      case '>' => startRow = i; startCol = j; dir = 1
      case 'v' => startRow = i; startCol = j; dir = 2
      case '<' => startRow = i; startCol = j; dir = 3
      case _   => // ignore other characters
    }
  }

  if (startRow == -1) {
    println("Guard not found!")
    sys.exit(1)
  }

  // Set to track distinct visited positions (row, col)
  val visited = mutable.Set[(Int, Int)]()
  visited.add((startRow, startCol))

  // Simulation: current guard position
  var curRow = startRow
  var curCol = startCol

  var keepGoing = true
  while (keepGoing) {
    // Determine next cell based on current direction
    val (dRow, dCol) = dirOffsets(dir)
    val nextRow = curRow + dRow
    val nextCol = curCol + dCol

    if (!inBounds(nextRow, nextCol)) {
      // Guard leaves the mapped area
      keepGoing = false
    } else if (grid(nextRow)(nextCol) == '#') {
      // Obstacle directly ahead; turn right 90 degrees
      dir = (dir + 1) % 4
    } else {
      // Move forward and mark the position as visited
      curRow = nextRow
      curCol = nextCol
      visited.add((curRow, curCol))
    }
  }

  println(s"Distinct positions visited: ${visited.size}")

  // Build and display the final map with visited positions marked as 'X'
  val finalGrid = grid.map(_.clone())
  for ((row, col) <- visited) {
    if (finalGrid(row)(col) != '#') {
      finalGrid(row)(col) = 'X'
    }
  }

  println("Final map:")
  finalGrid.foreach(row => println(row.mkString))
}
