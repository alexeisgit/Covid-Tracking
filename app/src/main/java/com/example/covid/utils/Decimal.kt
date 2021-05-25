package com.example.covid.utils

fun decimalFromNumber(number: Int, delimeter: String? = ","): String{

    val text = number.toString() //"456132" "456 132"
                                 // 012345
    var result = ""
    var digitAlready = 3-text.length%3
    for (i in text.indices){
        result += text[i]
        digitAlready++
        if (digitAlready%3 == 0 && i != text.lastIndex){
            result += delimeter
            digitAlready = 0
        }
    }

    return result
}
