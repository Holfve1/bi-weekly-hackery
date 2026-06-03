package cheatinghangman

import scala.io.StdIn.readLine
import scala.util.Random

object CheatingHangman {
  def main(args: Array[String]): Unit  = {
    var words = List("cat", "car", "cow", "dog", "pig")
    var word: String = Random.shuffle(words).head
    var penaltyPoints = 0
    var lettersGuessed = ""
    var hiddenWord = word.map(_ => "_")

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

      val guess: String = readLine("Enter a Letter: ").toLowerCase()
      if (guess.length != 1)
        println("guess must be 1 character")
      else if (lettersGuessed.contains(guess))
        println("already guessed that")
      else {
        val guessChar = guess.charAt(0)
        println()
        lettersGuessed = lettersGuessed :+ guessChar
        println(s"Guessed letters: ${lettersGuessed}")
        println()


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





      //    if (word.contains(guessChar)) {
//      for (i <- word.indices)
//        if (word(i) == guessChar)
//          hiddenWord = hiddenWord.updated(i, guess)
//    } else {
//      penaltyPoints += 1
//      println(s"Wrong! Penalty: $penaltyPoints/6")
//    }


// random pick word from list
// if guess not in word
  //hangman point and remove words with that letter
// if guess in word, and words > 2
  // remove word and random. pick another if guess not in another word

// if guess in word and all other words
  // keep word and add letter
  // remove all words that dont have that letter there
