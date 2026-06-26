package cheatinghangman

import scala.io.StdIn.readLine
import scala.util.Random

object CheatingHangman2 {
  def isValidGuess(guess: String, lettersGuessed: String): (Boolean, String) = {
    if (guess.length != 1)
      (false, "Guess must be 1 character")
    else if (lettersGuessed.contains(guess))
      (false,  "You have already guessed that letter")
    else if (!"qwertyuiopasdfghjklzxcvbnm".contains(guess)) {
      (false, "Guess must be a letter!")
    } else {
      (true, "")
    }
  }
  def underscores(word: String) = {
    word.map(_ => "_").toVector
  }

  def filterWords(words: List[String], word: String, guess:String): List[String] = {
    val guessChar = guess.charAt(0)

    if (!word.contains(guessChar)) {
      words.filterNot(_.contains(guessChar))
    } else if (word.contains(guessChar) && words.filterNot(_.contains(guessChar)).nonEmpty) {
      words.filterNot(_.contains(guessChar))
    } else (words.forall(_.contains(guessChar)))
      words.filter(w => word.indices.forall(i => word(i) != guessChar || w(i) == guessChar))
  }
  for (i <- word.indices)
    if (word(i) == guessChar)
      hiddenWord = hiddenWord.updated(i, guess)

  def main(args: Array[String]): Unit  = {
    var words = List("cat", "car", "cow", "dog", "pig")
    var word: String = Random.shuffle(words).head
    var penaltyPoints = 0
    var lettersGuessed = ""
    var hiddenWord = underscores(word)



    while (hiddenWord.mkString != word && penaltyPoints != 6) {
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

      val guess = readLine("Guess a letter: ").toLowerCase()
      val (valid, msg) = isValidGuess(guess, lettersGuessed)
      val guessChar = guess.charAt(0)
      println()
      lettersGuessed = lettersGuessed :+ guessChar
      println(s"Guessed letters: ${lettersGuessed}")
      println()

      if (!valid) println(msg)
      else {
        if (!word.contains(guessChar)) {
          words = words.filterNot(_.contains(guessChar))
          penaltyPoints += 1
          println(s"Wrong! Penalty: $penaltyPoints/6")
        } else if (word.contains(guessChar) && words.filterNot(_.contains(guessChar)).nonEmpty) {
          words = words.filterNot(_.contains(guessChar))
          word = Random.shuffle(words).head
          penaltyPoints += 1
          println(s"Wrong! Penalty: $penaltyPoints/6")
        } else if (words.forall(_.contains(guessChar)))
          for (i <- word.indices)
            if (word(i) == guessChar) {
              hiddenWord = hiddenWord.updated(i, guess)
              words = words.filter(w => w(i) == guessChar)

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
}
