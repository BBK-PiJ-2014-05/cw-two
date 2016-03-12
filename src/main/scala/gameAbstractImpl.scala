/**
  * Created by geoff_000 on 11/03/2016.
  */

import scala.collection.mutable.ListBuffer
import scala.io.StdIn.{readLine,readInt}

abstract class gameAbstractImpl extends Game {


  private var showCode: Boolean = false

  var colourSetMap:Map[String,String] = Map()
  var guessList:List[String] = List()
  var secretCode:List[String] = List()
  var gameWon = false

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

  def showGuessList(list:List[String])={
    for(x <-list){
      println(x.toString)
    }
  }

  def showCodeInGame(secretCode:List[String],showCode:Boolean)={
    var visible = "Secret Code: "
    if(showCode == true){
      for(x <-secretCode){
        visible += x.toString
      }
    }
    println(" ")
  }


  def startGuessing(list:List[String],guessesLeft:Int):List[String]={
    if (guessesLeft == 0){
      list
    }
    val guess = getGuess()
    val feedBackList = getFeedBack(guess,secretCode)
    //print(feedBackList.toString())
    if (feedBackList.forall(x=>x.equals("Black"))){
      gameWon = true
    }
      val newList = list.updated(guessList.size - guessesLeft,guess + " Result: " + feedBackList.mkString(","))
      showGuessList(newList)
      startGuessing(newList,guessesLeft-1)
    }



  def getGuess():String= {
    val guess = readLine("Next guess: ")
    if (!validGuess(guess,colourSetMap)){
      getGuess
    }
    guess

  }


  def runGames={
    intro(colourSetMap)
    print("Secret code: ")
    showCodeInGame(secretCode,showCode)
    showGuessList(guessList)
    startGuessing(guessList,guessList.size)




  }

  def colourListString(colourSetMap:Map[String,String]):String={
    val result:String = colourSetMap.values.mkString(", ")
    result
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




  def intro(colourSetMap:Map[String,String])={
    println("Welcome to Mastermind.\n\n" +

      "This is a text version of the classic board game Mastermind.\n" +
      "The computer will think of a secret code.\n" +
      "The code consists of " + secretCode.length + " colored pegs.\n" +
      "The pegs may be one of " + colourSetMap.size + " colours: " + colourListString(colourSetMap) + "\n" +
      "q1_2.A color may appear more than once in the code. \n\n" +

      "You try to guess what colored pegs are in the code and what order they are in \n" +
      "After making a guess the result will be displayed. \n" +
      "q1_2.A result consists of a black peg for each peg you have exactly correct (color and position) in your guess. \n" +
      "For each peg in the guess that is the correct color, but is out of position, you get a white peg. \n\n" +

      "Only the first letter of the color is displayed. q1_2.B for Blue, R for Red, and so forth.\n" +
      "When entering guesses you only need to enter the first character of the color as a capital letter. \n\n" +

      "You have " + guessList.length + " to guess the answer or you lose the game. \n\n" +

      "Generating secret code...\n")
  }




}
