import org.scalatest.FunSuite

/**
  * Created by geoff_000 on 11/03/2016.
  */
class FactorySuite extends FunSuite {

  test("A code should be a list of length n, where n is the given codeLength"){
    val colourMap = Map("O"-> "Orange","B"-> "Black","R"->"Red","W"->"White","P"->"Purple")
    val list = List()
    assert(Factory.generateCode(list,4,colourMap).length == 4)
  }

  test("A valid code contains only colours contained in the colourSetMap"){
    val colourMap = Map("O"-> "Orange","B"-> "Black","R"->"Red","W"->"White","P"->"Purple")
    val list = List()
    var result = true
    val codeList = Factory.generateCode(list,4,colourMap)
    for (x <- codeList){
      if (!colourMap.contains(x)){
        result = false
      }
    }
    assert(result == true)
  }



}
