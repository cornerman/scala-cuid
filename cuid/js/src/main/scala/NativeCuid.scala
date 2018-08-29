package cuid

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("cuid", JSImport.Namespace, globalFallback = "cuid")
private[cuid] object JsCuid extends js.Object {
  def apply(): String = js.native
}

private[cuid] trait NativeCuid extends Cuid {
  def apply(): String = JsCuid()
}
