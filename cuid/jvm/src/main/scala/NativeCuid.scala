package cuid

import cool.graph.cuid.{Cuid => JavaCuid}

private[cuid] class NativeCuid extends Cuid {
  def apply(): String = JavaCuid.createCuid()
}
