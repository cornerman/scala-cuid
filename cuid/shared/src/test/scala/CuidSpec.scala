package cuid

import org.scalatest._
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.must.Matchers

class CuidSpec extends AsyncFreeSpec with Matchers {
  "true" in {
    val cuid = Cuid()
    cuid.length must be > 0
  }
}
