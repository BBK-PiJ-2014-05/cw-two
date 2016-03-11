def generateCode(list: List[Any],codeLength:Int,colourSetMap:Map[String,String]): List[Any] = {
  val colourList = colourSetMap.keySet.toList
  if (list.length == codeLength) {
    list
  } else {
    val r = generateRan
    val randomColour = colourList(r)
    val listInTheMaking = randomColour :: list
    generateCode(listInTheMaking,codeLength,colourSetMap)
  }
  def generateRan: Int = {
    val r = scala.util.Random
    val result = r.nextInt(colourList.size - 1)
    result
  }
}
val list = List()
val map = Map("B"->"Black","C"->"Calico","D"->"Dogs")
val result = generateCode(list,4,map)