package cheatinghangman

import org.scalatest.flatspec.AnyFlatSpec


class CheatingHangman2Spec extends AnyFlatSpec{
  "guess" should "reject empty input with string" in {
    val (valid, msg) = CheatingHangman2.isValidGuess("", "")
    assert(!valid)
    assert(msg === "Guess must be 1 character")
  }
  "guess" should "reject inputs larger than 1 with string" in {
    val (valid, msg) = CheatingHangman2.isValidGuess("wert", "")
    assert(!valid)
    assert(msg === "Guess must be 1 character")
  }
  "guess " should " reject identical inputs twice with string" in {
    val (valid, msg) = CheatingHangman2.isValidGuess("w", "gtwe")
    assert(!valid)
    assert((msg === "You have already guessed that letter"))
  }
  "guess " should "reject inputs that are not letters" in {
    val (valid, msg) = CheatingHangman2.isValidGuess("1", "abc")
    assert(!valid)
    assert(msg === "Guess must be a letter!")
  }
    "word" should "be the same length as hidden word" in {
      val word = "cat"
      val hiddenWord = CheatingHangman2.underscores(word)
      assert(word.length === hiddenWord.length)
    }

}
/*
 0. guess cannot be empty - done
 1. hidden word has same amount of characters as word
 2. guess must only be 1 letter long - done
 3. letters guessed are added to lettersGuessed
 4. cant guess same letter twice - done
 5. guess must be letter - done
 6. if guess wrong remove words with letterv from list + 1 point
 7. if letter guess in word but not in list - change word + 1 point
 8. if letter in all words add letter to hiddenWord
 9. word in list removed if not have letter in the position of accepted word
 10. if 6 points gained - game over
 11. if word guessed game win
 */


