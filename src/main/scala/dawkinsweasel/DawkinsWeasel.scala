package dawkinsweasel

import scala.util.Random

object DawkinsWeasel {
  def main(args: Array[String]): Unit  = {}
    val alpha = ". ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val weasel = "METHINKS IT IS LIKE A WEASEL."

  def randStr = (1 to weasel.length)
      .map(_ => alpha(Random.nextInt(alpha.length)))
      .mkString

  val singleString = randStr

  def mutate(randStr: String): String = randStr.map(char =>
    if(Random.nextInt(100) < 5)
      alpha(Random.nextInt(alpha.length))
    else
      char
  )

  def multipleRandStrs(singleString: String) = (1 to 100)
    .map(_ => mutate(singleString))

  val randomStrings = multipleRandStrs(singleString)

  def matches(randomStrings: String) = weasel.zip(randomStrings).count { case (c1, c2) => c1 == c2 }

  var currentBest = randomStrings.maxBy(matches)

  while (currentBest != weasel) {
    var newStrings = multipleRandStrs(currentBest)
    currentBest = newStrings.maxBy(matches)
    println("Winner: " + currentBest + ": " + matches(currentBest))

    }

}
