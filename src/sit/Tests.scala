package sit

object Tests extends App {

  val cola = new MenuItem(50, Cold, Drink)
  println(" Cola Price " + cola.price)
  assert(cola.price == 50)

  // Adding *expensive* hot and cold food to test the service charge cap
  CafeX.setMenuItems(Map(
    "Cola" -> new MenuItem(50, Cold, Drink),
    "Coffee" -> new MenuItem(100, Hot, Drink),
    "Cheese Sandwich" -> new MenuItem(200, Cold, Food),
    "Steak Sandwich" -> new MenuItem(450, Hot, Food),
    "Eels" -> new MenuItem(11000, Hot, Food),
    "Cabbage" -> new MenuItem(11000, Cold, Food)))

    
  val total = CafeX.totalBillFloat(List("Cola", "Coffee", "Cheese Sandwich"))
  println(" Total " + total)
  assert(total == "3.85")
    
  val cabbage = CafeX.totalBillFloat(List("Cabbage"))
  println(" cabbage " + cabbage)
  assert(cabbage == "121.00")	// 10% Service Charge for Cold Food 
  
  val eels = CafeX.totalBillFloat(List("Eels"))
  println(" eels " + eels)
  assert(eels == "130.00")	// Max £20 Service Charge for Hot Food 
  
  println("All Tests Passed (if you can read this!)" )  
}