/**
  * Created by geoff_000 on 11/03/2016.
  */
abstract class gameAbstractImpl extends Game {


  private var showCode: Boolean = false

  var colourSetMap:Map[String,String] = Map()
  var guessList:List[String] = List()
  var secretCode:List[String] = List()

  def setColourSetMap(colourSetMap:Map[String,String])={
    this.colourSetMap = colourSetMap
  }

  def setGuessList(guessList:List[String])={
    this.guessList = guessList
  }

  def setSecretCode(secretCode:List[String])={
    this.secretCode = secretCode
  }

  

  def getGuessList = guessList

  /**
    * Create a Game object.
    *
    * @param easy If easy is true the secret code will be
    *             revealed at all times when playing the game. If easy is
    *             false the secret code is not revealed until correctly guessed
    *             or the player runs out of turns.
    */
  def this(easy: Boolean) {
    this()
    showCode = easy
  }

  def validGuess(guess:String,colourSetMap:Map[String,String]):Boolean={
    var response = true
    for (c <- guess) {
      if (!colourSetMap.contains(c.toString)) {
        response = false
      }
    }
    response
  }

  def runGames={
    println("Let the game begin...Please choose from the following colours " + colourListString(colourSetMap))
    print("Secret code: ")
    if (showCode == true){
      println(secretCode)
    }



  }

  def colourListString(colourSetMap:Map[String,String]):String={
    var result:String = ""
    for (x <- colourSetMap.values){
      result += x.toString + ", "

    }
    result
  }






}
