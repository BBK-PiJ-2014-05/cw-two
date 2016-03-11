import _root_.Game.GameImpl
import _root_.Game.GameImpl

import scala.collection.mutable.ListBuffer

/**
  * Created by geoff_000 on 11/03/2016.
  */
object Factory {



  def validGuess(guess:String,colourSetMap:Map[String,String]):Boolean={
    var response = true
    for (c <- guess) {
      if (!colourSetMap.contains(c.toString)) {
        response = false
      }
    }
    response
  }

  def getFeedBack(guess: String, codeList: List[String]): List[String] = {
    val feedBack = ListBuffer[String]()
    var x = 0
    for (i <- guess) {
      if (i.toString.equals(codeList(x))) {
        feedBack += "Black"
      }
      else if (codeList.contains(i.toString)) {
        feedBack += "White"
      }
      x += 1
    }
    if (feedBack.forall(x => x.equals("Black"))) {
      //gameWon = true
    }
    if (feedBack.isEmpty) {
      feedBack += "No Pegs!"
    }
    val feedBackList = feedBack.toList
    feedBackList.sorted
  }

  def generateCode(list: List[String],codeLength:Int,colourSetMap:Map[String,String]): List[String] = {
    val colourList = colourSetMap.keySet.toList
    if (list.length == codeLength) {
      list
    } else {
      def generateRan: Int = {
        val r = scala.util.Random
        val result = r.nextInt(colourList.size - 0)
        result
      }
      val r = generateRan
      val randomColour = colourList(r)
      val listInTheMaking = randomColour :: list
      generateCode(listInTheMaking,codeLength,colourSetMap)
    }
  }


}
