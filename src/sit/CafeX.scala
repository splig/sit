package sit

object CafeX extends App {

  var menuItems: Map[String, MenuItem] = Map()

  def setMenuItems(mItems: Map[String, MenuItem]): Unit = {
    menuItems = mItems
  }

  //********************************************************
  // Private Methods

  private def getItemPrice(itemName: String): Int = {
    val item = menuItems.get(itemName)
    if (item.isEmpty) 0 else item.get.price
  }

  private def calculateTotalBill(items: List[String]): Int = {
    items.foldLeft(0)((total, itemName) => getItemPrice(itemName) + total)
  }

  private def calculateFormattedBill(pennies: Int): Float = {
    pennies.toFloat / 100
  }

  //********************************************************
  // Public Methods

  def totalBillFloat(items: List[String]): Float = {

    val billInPennies = this.calculateTotalBill(items)
    calculateFormattedBill(billInPennies)
  }

}