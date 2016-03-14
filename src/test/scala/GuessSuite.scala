import org.scalatest.FunSuite

/**
  * Created by geoff_000 on 11/03/2016.
  */
class GuessSuite extends FunSuite{

  val g = new GameImpl

  test("A valid guess contains only chars within the colour set"){
    val colourMap = Map("O"-> "Orange","B"-> "Black","R"->"Red","W"->"White","P"->"Purple")
    val secretCode = List("O","B","R","R")
    val guess = "OWBB"

    assert(g.validGuess(guess,colourMap,secretCode) == true)

  }

  test("A winning guess consists of n Black, where n is the length of the secret code"){
    val secretCode = List("B","B","R","W")
    val guess = "BBRW"
    assert(g.getFeedBack(guess,secretCode).equals(List("Black","Black","Black","Black")))
  }

  test("A guess containing only one correct colour in the correct place produces Black"){
    val secretCode = List("B","B","R","W")
    val guess = "TTRT"
    assert(g.getFeedBack(guess,secretCode).equals(List("Black")))
  }

  test("A guess containing only one correct colour out of place produces White"){
    val secretCode = List("B","B","R","W")
    val guess = "TRTT"
    assert(g.getFeedBack(guess,secretCode).equals(List("White")))
  }

  test("A guess containing one correct colour in the correct place and one correct colour out of place produces Black White"){
    val secretCode = List("B","B","R","W")
    val guess = "TBTR"
    assert(g.getFeedBack(guess,secretCode).equals(List("Black","White")))
  }

  test("A guess containing no correct colours produces No Pegs!"){
    val secretCode = List("B","B","R","W")
    val guess = "TTTT"
    assert(g.getFeedBack(guess,secretCode).equals(List("No Pegs!")))
  }

  test("A feedback should not double count colours"){
    val secretCode = List("G","O","R","G")
    val guess = "RORG"
    assert(g.getFeedBack(guess,secretCode) == List("Black","Black","Black"))
  }


}
