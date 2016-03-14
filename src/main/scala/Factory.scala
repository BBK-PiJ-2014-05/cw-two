

import scala.collection.mutable.ListBuffer

/**
  * A factory to produce a valid instance of a game
  *
  *
  *
  */
object Factory {


  //var game:gameAbstractImpl = null

  def getInstance(c: Class[_], b: Boolean): Game = {

    /**
      * the members colourSetMap, codeLength and numberOfGuesses form the base parameters of a game. These could be contained in a separate file (such as a Beans properties file)
      * so the values of each could be injected into a game object
      */
    val colourSetMap = Map("O" -> "orange", "B" -> "blue", "G" -> "green", "P" -> "purple", "R" -> "red", "Y" -> "yellow")
    val codeLength = 4
    val numberOfGuesses = 12


    var g:gameAbstractImpl = new GameImpl(b)


    g.setColourSetMap(colourSetMap)
    val secretCode = generateCode(List[String](),codeLength,colourSetMap)
    g.setSecretCode(secretCode)
    g.setGuessList(initGuessList(numberOfGuesses))
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
