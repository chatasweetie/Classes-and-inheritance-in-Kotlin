// to run https://kotlinlang.org/docs/command-line.html#install-the-compiler
// project from https://developer.android.com/codelabs/basic-android-kotlin-training-classes-and-inheritance

import kotlin.math.PI
import kotlin.math.sqrt


fun main() {
    val squareCabin = SquareCabin(6, 50.0)

    println("\nSquare Cabin\n============")
    println("Capacity: ${squareCabin.capacity}")
    println("Material: ${squareCabin.buildingMaterial}")
    println("Has room? ${squareCabin.hasRoom()}")
    println("Floor area: %.2f".format(squareCabin.floorArea()))

    val roundHut = RoundHut(3, 10.0)

    // working with a specific instance of a class and need to access multiple 
    // properties and functions of that instance, use with statement.
    with(roundHut) {
        println("\nRound Hut\n=========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Carpet size: ${calculateMaxCarpetSize()}")
    }

    val roundTower = RoundTower(4, 15.5)

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet size: ${calculateMaxCarpetSize()}")
    }

}

// An "abstract" class is a class that cannot be instantiated because 
// it is not fully implemented You can think of it as a sketch. A sketch 
// incorporates the ideas and plans for something, but not usually enough 
// information to build it. You use a sketch (abstract class) to create a 
// blueprint (class) from which you build the actual object instance.
abstract class Dwelling(private var residents: Int){
    abstract val buildingMaterial: String
    abstract val capacity: Int

    // determines whether there is room for another resident in the dwelling
    fun hasRoom(): Boolean {
        return residents < capacity
    }

    // All abstract methods defined in an abstract class must be implemented 
    // in any of its subclasses. Need to implement bstract methods in the subclasses.
    abstract fun floorArea(): Double

    fun getRoom(){
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

class SquareCabin(
    residents: Int,
    val length: Double) : Dwelling(residents){
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length * length
    }
}

// By default, in Kotlin, classes are final and cannot be subclassed. With the 
// open keyword,  abstract classes or classes can be inherited
open class RoundHut(
    residents: Int,
    val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
    }

    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

class RoundTower(
    residents: Int, 
    radius: Double,
    val floors: Int = 2) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
}
