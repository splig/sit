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

  private def getItemIsFood(itemName: String): Boolean = {
    val item = menuItems.get(itemName)
    if (item.isEmpty) false else (item.get.foodOrDrink == Food)
  }

  private def getItemIsHotFood(itemName: String): Boolean = {
    val item = menuItems.get(itemName)
    if (item.isEmpty) false else (item.get.foodOrDrink == Food && item.get.hotOrCold == Hot)
  }

  // Functions of for calculating Service Charge 
  private def hasFood(items: List[String]): Boolean = {
    items.foldLeft(false)((hasSomeFood, itemName) => getItemIsFood(itemName) || hasSomeFood)
  }

  private def hasHotFood(items: List[String]): Boolean = {
    items.foldLeft(false)((hasHotFood, itemName) => getItemIsHotFood(itemName) || hasHotFood)
  }

  private def applyServiceCharge(hasFood: Boolean, hasHotFood: Boolean, price: Int): Int = {
    // Any Food - 10%
    // Any Hot Food - 20%

    def capService(x: Int) = if (x <= 2000) x else 2000

    def calcService(x: Int, level: Float) = {
      val service = ((x * 1.0f) * (level / 100)).toInt
      capService(service)
    }

    def applyCorrectService(f: Boolean, h: Boolean, p: Int) = (f, h, p) match {
      case (_, true, _) => p + calcService(p, 20.0f)
      case (true, false, _) => p + calcService(p, 10.0f)
      case _ => p
    }

    applyCorrectService(hasFood, hasHotFood, price)
  }

  //********************************************************
  // Public Methods

  def totalBillFloat(items: List[String]): String = {

    val billInPennies = this.calculateTotalBill(items)
    val billWithService = applyServiceCharge(hasFood(items), hasHotFood(items), calculateTotalBill(items))
    val total = calculateFormattedBill(billWithService)
    "%1.2f".format(total)
  }

}