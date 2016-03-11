import org.scalatest.FunSuite

/**
  * Created by geoff_000 on 11/03/2016.
  */
class SetSuite extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

  test("1 should equal 1"){
    assert(1 == 1)
  }

}
