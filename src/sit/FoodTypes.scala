package sit

trait ItemType {}
trait ItemTempurature {}

case object Hot  extends ItemTempurature
case object Cold extends ItemTempurature

case object Drink extends ItemType
case object Food  extends ItemType
