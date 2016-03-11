/**
  * Created by geoff_000 on 11/03/2016.
  */
trait Game {

  def runGames

  class GameImpl extends Game{

    val colourSetMap = Map("B"->"Black","O"->"Orange","G"->"Green","P"->"Purple","S"->"Scarlet","Y"->"Yellow")
    val numberOfGuesses = 12
    val codeSetLength = 4

    def runGames={

    }



  }


}
