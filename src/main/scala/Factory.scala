

import scala.collection.mutable.ListBuffer

/**
  * A factory to produce a valid instance of a game
  *
  *
  *
  */

import com.softwaremill.macwire._

object Factory {


  //var game:gameAbstractImpl = null

  def getInstance(c: Class[_], b: Boolean): Game = {

    /**
      * Using a factory to initialize a Game object using a properties file GameProps. The properties file contains the
      * members required to initialize a game instance. The wiring is through MacWire. Not successful in using the MacWire
      * macro to initialize a GameImpl which takes a boolean constructor. Not sure how to do this so instead the concrete
      * class of Game is via new keyword.
      */


    lazy val gameProps = wire[GameProps]
    //lazy val g = wire[GameImpl] how to do this using the def this() method supplied?

    val g:gameAbstractImpl = new GameImpl(b)

    g.setColourSetMap(gameProps.getColourSetMap)
    val secretCode = generateCode(List[String](),gameProps.getGuessSize,gameProps.getColourSetMap)
    g.setSecretCode(secretCode)
    g.setGuessList(initGuessList(gameProps.getNumberOfGuesses))
    g
  }

  def initGuessList(x:Int):List[String]={
    val initGuessList = List.fill(x)("....")
    initGuessList
  }



  /**
    * A recursive method to generate the secret code as an immutable list using the set of available colours and the desired
    * length of the code
    *
     * @param list
    * @param codeLength
    * @param colourSetMap
    * @return
    */

    def generateCode(list: List[String], codeLength: Int, colourSetMap: Map[String, String]): List[String] = {

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
        generateCode(listInTheMaking, codeLength, colourSetMap)
      }
    }


}
