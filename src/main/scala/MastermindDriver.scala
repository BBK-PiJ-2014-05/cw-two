/**
  * Created by geoff_000 on 11/03/2016.
  */
object MastermindDriver {

  def main(args:Array[String])= {


    if(args.length>0) {

      if (args(0).equals("1")) {

        val g = Factory.getInstance(classOf[Game], true)
        g.runGames

      }

    }

    val g = Factory.getInstance(classOf[Game],false)
    g.runGames


  }
}
