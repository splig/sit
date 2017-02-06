package sit

object Tests extends App {

  val cola = new MenuItem(50, Cold, Drink)
  println(" Cola Price " + cola.price)
  assert(cola.price == 50)

  CafeX.setMenuItems(Map(
    "Cola" -> new MenuItem(50, Cold, Drink),
    "Coffee" -> new MenuItem(100, Hot, Drink),
    "Cheese Sandwich" -> new MenuItem(200, Cold, Food),
    "Steak Sandwich" -> new MenuItem(450, Hot, Food)))

  val total = CafeX.totalBillFloat(List("Cola", "Coffee", "Cheese Sandwich"))
  println(" Total " + total)
  assert(total == 3.5f)
    
  
  
  println("All Tests Passed (if you can read this!)" )  
}