/**
  * Created by geoff_000 on 11/03/2016.
  */
object MastermindDriver {

  def main(args:Array[String])= {


    val g = Factory.getInstance(classOf[Game], true)
    g.runGames

  }
}
