package bowling
import scala.util.Random

object Bowling {
  def main(args: Array[String]): Unit = {}
// creates a list of the first 9 turns
  var gameScore = (1 to 9).map { _ =>
    val shotOne = Random.nextInt(11)
    if (shotOne == 10) {
      List(shotOne)
    } else {
      val shotTwo = Random.nextInt(11 - shotOne)
      List(shotOne, shotTwo)
    }
  }.toList
// creates the final shot with separate rules
  val shot = Random.nextInt(11)
  var finalShot = List(shot)
  if (shot != 10) {
    val secondShot = Random.nextInt(11 - shot)
    finalShot = finalShot :+ secondShot
    if (secondShot + shot == 10) {
      val thirdShot = Random.nextInt(11)
      finalShot = finalShot :+ thirdShot
    }
  } else if (shot == 10) {
    val secondShot = Random.nextInt(11)
    finalShot = finalShot :+ secondShot
    if (secondShot == 10) {
      val thirdShot = Random.nextInt(11)
      finalShot = finalShot :+ thirdShot
    } else {
      val thirdShot = Random.nextInt(11 - secondShot)
      finalShot = finalShot :+ thirdShot
    }
  }
  gameScore = gameScore :+ finalShot
//  don't need to loop to get the numbers like in python
//  flatten joins a list of lists in 1 list
  val rolls = gameScore.flatten
// goes through the turn scores and adds them up
  var rollIndex = 0
  var frameScore = (1 to 9).map { _ =>
    val score = if(rolls(rollIndex) == 10) {
      10 + rolls(rollIndex + 1) + rolls(rollIndex + 2)
    } else if(rolls(rollIndex) + rolls(rollIndex + 1) == 10) {
      10 + rolls(rollIndex + 2)
    } else {
      rolls(rollIndex) + rolls(rollIndex + 1)
    }
    rollIndex += (if(rolls(rollIndex) == 10) 1 else 2)
    score
  }.toList
// gets the score fore each turn sequentially
  var runningTotal = 0
  val turnScores = frameScore.map { frameScore =>
    runningTotal += frameScore
    runningTotal
  }

  println(gameScore)
  print(turnScores)

}