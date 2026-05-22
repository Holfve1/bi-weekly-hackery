package bowling
import scala.util.Random

object Bowling {
  def main(args: Array[String]): Unit = {}

  var gameScore = (1 to 9).map {
    val shotOne = Random.nextInt(11)
    if (shotOne == 10) {
      List(shotOne)
    } else {
      val shotTwo = Random.nextInt(11 - shotOne)
      List(shotOne, shotTwo)
    }
  }.toList

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
    if(secondShot == 10) {
      val thirdShot = Random.nextInt(11)
      finalShot = finalShot :+ thirdShot
    } else {
      val thirdShot = Random.nextInt(11 - secondShot)
      finalShot = finalShot :+ thirdShot
    }
  }
  gameScore = gameScore :+ finalShot
}
