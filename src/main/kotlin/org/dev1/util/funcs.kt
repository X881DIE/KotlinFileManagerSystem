package org.dev1.util



fun AbsCompare(a:Double,b:Double):Boolean{
    return Math.abs(a) == Math.abs(b)
}
typealias DoubleCompute = (Double,Double)->Double
fun compute(computer:DoubleCompute,v1:Double,v2:Double):Double{


    return computer(v1,v2)
}

fun main(args:Array<String>):Unit{

    println(
        compute(
            {a:Double,b:Double->Math.sin(a+b)},
            1.00,
            2.00
        )

    )
}