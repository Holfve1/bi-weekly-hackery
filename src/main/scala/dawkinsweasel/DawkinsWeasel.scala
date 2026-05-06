package dawkinsweasel

import scala.util.Random

object DawkinsWeasel {
  def main(args: Array[String]): Unit  = {}
    val alpha = " ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val weasel = "METHINKS IT IS LIKE A WEASEL"
  // creates a random 28 char string using only chars in alpha
  def randStr = (1 to 28)
      .map(_ => alpha(Random.nextInt(alpha.length)))
      .mkString  // makes it into a string rather than array

  val singleString = randStr

  // mutates each character in the string with a 5% chance
  def mutate(randStr: String): String = randStr.map(char =>
    if(Random.nextInt(100) < 5)
      alpha(Random.nextInt(alpha.length))
    else
      char
  )

  // takes the random string method and does it 100 times
  def multipleRandStrs(singleString: String) = (1 to 100)
    .map(_ => mutate(singleString))

  // saves the 100 random strings into a variable
  val randomStrings = multipleRandStrs(singleString)

  // checks the randomString with weasel by matching the positions of each string
  def matches(randomStrings: String) = weasel.zip(randomStrings).count { case (c1, c2) => c1 == c2 }
  // loops through the 100 random strings calls matches method on each one and prints its similarity
  // takes the random string with the highest score and compares to weasel

  //takes the String with highest score
  var currentBest = randomStrings.maxBy(matches)

  // loops until the best string is equal to weasel
  while (currentBest != weasel) {
    var newStrings = multipleRandStrs(currentBest)
    currentBest = newStrings.maxBy(matches)
    println("Winner: " + currentBest + ": " + matches(currentBest))

    }

}
