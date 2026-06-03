package hangman
import scala.io.StdIn.readLine

object HangMan {
  def main(args: Array[String]): Unit  = {
  val word = readLine("Please pick a word for the player: ").toLowerCase()
  var penaltyPoints = 0
  var lettersGuessed = ""
  var hiddenWord = word.map(_ => "_")

  while(hiddenWord.mkString != word && penaltyPoints != 6) {
    if(penaltyPoints == 0)
      println(
        """
          |  +---+
          |  |   |
          |      |
          |      |
          |      |
          |      |
          |=========
          """.stripMargin)
    else if(penaltyPoints == 1)
      println(
        """
          |  +---+
          |  |   |
          |  O   |
          |      |
          |      |
          |      |
          |=========
          """.stripMargin)
    else if(penaltyPoints == 2)
      println(
        """
          |  +---+
          |  |   |
          |  O   |
          |  |   |
          |      |
          |      |
          |=========
            """.stripMargin)
    else if(penaltyPoints == 3)
      println(
        """
          |  +---+
          |  |   |
          |  O   |
          |  |\  |
          |      |
          |      |
          |=========
            """.stripMargin)
    else if(penaltyPoints == 4)
      println(
        """
          |  +---+
          |  |   |
          |  O   |
          | /|\  |
          |      |
          |      |
          |=========
            """.stripMargin)
    else if(penaltyPoints == 5)
      println(
        """
          |  +---+
          |  |   |
          |  O   |
          | /|\  |
          | /    |
          |      |
          |=========
            """.stripMargin)

    println(hiddenWord.mkString)
    println()

    val guess = readLine("Enter a Letter: ").toLowerCase()
    if(guess.length != 1)
      println("guess must be 1 character")
    else if(lettersGuessed.contains(guess))
      println("already guessed that")
    else {
      val guessChar = guess.charAt(0)
      println()
      lettersGuessed = lettersGuessed :+ guessChar
      println(s"Guessed letters: ${lettersGuessed}")
      println()
      if (word.contains(guessChar)) {
        for (i <- word.indices)
          if (word(i) == guessChar)
            hiddenWord = hiddenWord.updated(i, guess)
      } else {
        penaltyPoints += 1
        println(s"Wrong! Penalty: $penaltyPoints/6")
      }

    }
  }
    if(penaltyPoints == 6) {
      println()
      println(s"The word was: ${word} ")
      println()
      println("GAME OVER")
      print(
        """
          |  +---+
          |  |   |
          |  O   |
          | /|\  |
          | / \  |
          |      |
          |=========
          """.stripMargin)
    } else if(hiddenWord.mkString == word)
      print("You Win")
}
}
// 1. random word generator
// 2. show underscores for each letter in word
// 2. guess a letter - need to limit to only 1 letter. cannot be one already chosen
// 4. if correct show the letter in the underscores
// 5. if incorrect add hangman part -
//          totals - 6 for body, 1/2 for gallows and rope? so maybe 8
// 6. if guess all in time win else lose