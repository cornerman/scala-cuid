package cuid

import cool.graph.cuid.{Cuid => JavaCuid}
import android.content.Context

private[cuid] class NativeCuid extends Cuid {
  private var context = Option.empty[Context]

  def setContext(context: Context): Unit = contextOpt = Some(context)
  def apply(): String = contextOpt match {
    case Some(context) => new JavaCuid(context).createCuid()
    case None => throw new IllegalStateException("Android Cuid does not have a context. Use Cuid.setContext(context).")
  }
}
