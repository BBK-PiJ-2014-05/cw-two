/**
  * Created by geoff_000 on 11/03/2016.
  */
class GameImpl extends gameAbstractImpl {


  private var showCode:Boolean = false


  def this(easy:Boolean){
    this()
    showCode= easy
  }




  override def showCodeInGame(secretCode:List[String],showCode:Boolean)= {
    var visible = "Secret Code: "
    if (showCode == true) {
      println(visible + secretCode.mkString(","))
    } else {
      println(visible)
    }
  }


  override def runGames={
    intro(colourSetMap)
    showCodeInGame(secretCode,showCode)
    showGuessList(guessList)
    val endList = startGuessing(guessList,guessList.size)
    if(endGame()) {
      println("Good bye!")
    } else {
      val g = Factory.getInstance(classOf[Game],false)
      g.runGames
    }



  }








}
