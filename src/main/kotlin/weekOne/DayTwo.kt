package weekOne

import java.io.File

class DayTwo {

    private val passwordList = mutableListOf<String>()
    private val passwordFile = File("./inputs/dayTwo.txt")

    fun readPasswordFile() {
        passwordFile.useLines { lines ->
            lines.forEach {
                passwordList.add(it)
            }
        }
        println(passwordList)
    }

    //Part one
    fun validatePasswordsByRequirement() {
        var validPasswords = 0
        passwordList.forEach { line ->
            val policyAndPassword = line.split(" ")
            val min = policyAndPassword[0].split("-").first().toInt()
            val max = policyAndPassword[0].split("-").last().toInt()
            val requiredChar = policyAndPassword[1].first().toString()

//            println("$min-$max $requiredChar")

            val count = policyAndPassword[2].count {
                requiredChar.contains(it)
            }

            if (count in min..max) {
                validPasswords++
            }
        }
        println("Valid passwords: $validPasswords")
    }
    
    //Part two
    fun validatePasswordsByIndex() {
        var validPasswords = 0
        passwordList.forEach { line ->
            val policyAndPassword = line.split(" ")
            val indexOne = policyAndPassword[0].split("-").first().toInt()
            val indexTwo = policyAndPassword[0].split("-").last().toInt()
            val requiredChar = policyAndPassword[1].first().toString()

            val password = policyAndPassword[2]

            val first = password[indexOne-1].toString()
            val second = password[indexTwo-1].toString()

            when {
                first == requiredChar && second == requiredChar -> {
                }
                first == requiredChar -> {
                    validPasswords++
                }
                second == requiredChar -> {
                    validPasswords++
                }
            }
            println("$indexOne-$indexTwo $requiredChar: Password: $password")

        }
        println("Valid passwords by index: $validPasswords")

    }


}