package cuid

trait Cuid {
  def apply(): String
}

object Cuid extends NativeCuid with Cuid
