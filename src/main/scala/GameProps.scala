/**
  * Created by geoff_000 on 14/03/2016.
  */

import com.softwaremill.macwire._

class GameProps {

  var COLOURS:Map[String,String] = Map("O" -> "orange", "B" -> "blue", "G" -> "green", "P" -> "purple", "R" -> "red", "Y" -> "yellow")
  var GUESSSIZE:Int = 4
  var NUMBEROFGUESSES = 12


  def getColourSetMap = COLOURS
  def getColourKeys = COLOURS.keySet
  def getColourValues = COLOURS.values
  def getGuessSize = GUESSSIZE
  def getNumberOfGuesses = NUMBEROFGUESSES


 // object GameProps {

   //   COLOURS = Map("O" -> "orange", "B" -> "blue", "G" -> "green", "P" -> "purple", "R" -> "red", "Y" -> "yellow")
     // GUESSSIZE = 4
      //NUMBEROFGUESSES = 12

    //}

}
