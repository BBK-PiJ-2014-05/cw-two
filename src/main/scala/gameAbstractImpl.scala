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


  /**
    * a valid guess contains only chars from the set of available colours and is of the same length as the secret code
    * 8
    * @param guess
    * @param colourSetMap
    * @param secretCode
    * @return
    */

  def validGuess(guess:String,colourSetMap:Map[String,String],secretCode:List[String]):Boolean={
    var response = true
    if(guess.length != secretCode.length){
      response = false
    }

    for (c <- guess) {
      if (!colourSetMap.contains(c.toString)) {
        response = false
      }
    }
    response
  }

  /**
    * prints the list of all previous guesses (with feedback)
    *
    * @param list
    */

  def showGuessList(list:List[String])={
    for(x <-list){
      println(x.toString)
    }
  }

  /**
    * If true the code is revealed throughout the game
    *
    * @param secretCode
    * @param showCode
    */

  def showCodeInGame(secretCode:List[String],showCode:Boolean)= {
    var visible = " "
    if (showCode == true) {
      println(visible + secretCode.mkString(","))
    }
  }


  /**
    * The player runs through a cycle of guessing until a correct guess is provided or the player has run out of guesses
    *
    * @param list
    * @param guessesLeft
    * @return
    */

  def startGuessing(list:List[String],guessesLeft:Int):List[String]= {

      if (guessesLeft == 0 || gameWon) {
        return list
      }
      val guess = getGuess()
      val feedBackList = getFeedBack(guess, secretCode)

      if (feedBackList.size == secretCode.size &&  feedBackList.forall(x => x.equals("Black"))) {
        gameWon = true
      }
      val newList = list.updated(guessList.size - guessesLeft, guess + " Result: " + feedBackList.mkString(","))
      showGuessList(newList)
      startGuessing(newList, guessesLeft - 1)
    }


  /**
    * The player is prompted for a guess until a valid guess is provided.
    *
    * @return
    */

  def getGuess():String= {
    var guess = ""
    while (!validGuess(guess,colourSetMap,secretCode)){
      guess = readLine("Next guess: ")
    }
    guess

  }

  /**
    * implemented in the concrete class GameImpl
    */
  def runGames={

  }

  def colourListString(colourSetMap:Map[String,String]):String={
    val result:String = colourSetMap.values.mkString(", ")
    result
  }

  /**
    * Get feedback after each guess. A feedback consists of either 0..n Black followed by 0..n White or No Pegs!
    *
    * @param guess
    * @param codeList
    * @return
    */


  def getFeedBack(guess: String, codeList: List[String]): List[String] = {
    val feedBack = ListBuffer[String]()
    val guessRemaining = guess.toCharArray.toBuffer
    val codeLeft = codeList.toArray[String].toBuffer

    var zipped = guessRemaining zip codeLeft
      for((a,b) <- zipped){
        if (a.toString.equals(b)){
          feedBack += "Black"
          guessRemaining -= a
          codeLeft -= b
        }
      }
      for(c <- guessRemaining){
        if (codeLeft.contains(c.toString)){
          feedBack += "White"
          codeLeft -= c.toString
        }
      }
      if(feedBack.isEmpty) feedBack += "No Pegs!"
    val result = feedBack.toList
    result
  }


  /**
    * This script runs after a game is won or the player has run out of guesses
    *
    * @return
    */

  def endGame():Boolean={

    if(gameWon){
      println("\nWell Done- You guessed correctly!\n")
      println("The secret code was " + secretCode.mkString(",") +  " \n")
    } else {
      println("\nToo Bad- you ran out of guesses before making the correct guess!\n")
      println("The secret code was " + secretCode.mkString(",") +  " \n")
    }
    val choice = readLine("Press 'k' to quit or any other key to play again.")
    if(choice.equals("k")){
      return true
    } else return false

  }

  /**
    * The introduction to the game including instructions on how to play
    *
    * @param colourSetMap
    */

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
