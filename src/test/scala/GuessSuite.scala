import org.scalatest.FunSuite

/**
  * Created by geoff_000 on 11/03/2016.
  */
class GuessSuite extends FunSuite{

  test("A valid guess contains only chars within the colour set"){
    val colourMap = Map("O"-> "Orange","B"-> "Black","R"->"Red","W"->"White","P"->"Purple")
    val guess = "OWBR"
    assert(Factory.validGuess(guess,colourMap) == true)

  }

  test("A winning guess contains only Black"){
    val secretCode = List("B","B","R","W")
    val guess = "BBRW"
    assert(Factory.getFeedBack(guess,secretCode).equals(List("Black","Black","Black","Black")))
  }

  test("A guess containing only one correct colour in correct place produces Black"){
    val secretCode = List("B","B","R","W")
    val guess = "TTRT"
    assert(Factory.getFeedBack(guess,secretCode).equals(List("Black")))
  }

  test("A guess containing only one correct colour out of place produces White"){
    val secretCode = List("B","B","R","W")
    val guess = "TRTT"
    assert(Factory.getFeedBack(guess,secretCode).equals(List("White")))
  }

  test("A guess containing one correct colour in correct place and one correct colour out of place produces Black White"){
    val secretCode = List("B","B","R","W")
    val guess = "TBTR"
    assert(Factory.getFeedBack(guess,secretCode).equals(List("Black","White")))
  }

  test("A guess containing no correct colours produces No Pegs!"){
    val secretCode = List("B","B","R","W")
    val guess = "TTTT"
    assert(Factory.getFeedBack(guess,secretCode).equals(List("No Pegs!")))
  }
}
