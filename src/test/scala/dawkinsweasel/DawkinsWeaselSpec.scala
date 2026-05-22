package dawkinsweasel

import org.scalatest.flatspec.AnyFlatSpec

class DawkinsWeaselSpec extends AnyFlatSpec {
  val randStr: String = DawkinsWeasel.randStr
  "randStr " should "have a string length of 28 characters" in {
    assert(randStr.length === 28)
  }
  "randStr" should "only contain the characters from alpha" in {
    assert(randStr.forall(char => DawkinsWeasel.alpha.contains(char)))
  }
  "randStr" should "only contain uppercase characters" in {
    assert(randStr === randStr.toUpperCase)
  }
//  "randStr" should "not create the same string" in {
//    assert(DawkinsWeasel.randStr != DawkinsWeasel.randStr)
//  } // realised this could be a little risky
  "mutate" should "still only have characters in alpha" in {
    assert(DawkinsWeasel.mutate(randStr).forall(char => DawkinsWeasel.alpha.contains(char)))
  }
  "mutate" should "still be 28 characters long" in {
    assert(DawkinsWeasel.mutate(randStr).length === 28)
  }
  "multipleRandStrs" should "take randStr and creates 99 more strings" in {
    assert(DawkinsWeasel.multipleRandStrs(randStr).size === 100)
  }
  val randStr100 = DawkinsWeasel.multipleRandStrs(randStr)
  "multipleRandStrs" should "all be 28 characters long" in {
    assert(randStr100.forall(word => word.length == 28))
  }
  val randomString = "METHINKS IT IS LIKE A WEASEL"
  "matches" should "return 28 if randStr === weasel" in {
    assert(DawkinsWeasel.matches(randomString) === 28)
  }
  val wrongString = "ABOUT LXFEFORZMNHPQYZCOLKM X"
  "matches" should "return 0 if randStr ha sno characters in the right place" in {
    assert(DawkinsWeasel.matches(wrongString) === 0)
  }
  val someRight = "MPTNQNKSSJZKTZ KULE XCROAPNL"
  "matches" should "if there are 10 characters in correct place return score of 10" in {
    assert(DawkinsWeasel.matches(someRight) === 10)
  }
}

// no idea how to tackle the tests for 5% chance of mutation
