package cuid

import org.scalatest._

class CuidSpec extends AsyncFreeSpec with MustMatchers {
  "true" in {
    val cuid = Cuid()
    cuid.length must be > 0
  }
}
