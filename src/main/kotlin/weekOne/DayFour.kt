package weekOne

import java.io.File

class DayFour {

    private val passports = mutableListOf<String>()
    private val passportsSorted = mutableListOf<String>()
    private val passportFile = File("./inputs/dayFour.txt")

    private val birthYear = "byr"
    private val issueYear = "iyr"
    private val expirationYear = "eyr"
    private val height = "hgt"
    private val hairColor = "hcl"
    private val eyeColor = "ecl"
    private val passportID = "pid"

    private val reqFields = listOf(
        birthYear,
        issueYear,
        expirationYear,
        height,
        hairColor,
        eyeColor,
        passportID
    )

    private val hairColorRegex = "#([0-9]|[a-f]){6}".toRegex()
    private val eyeColors = listOf(
        "amb",
        "blu",
        "brn",
        "gry",
        "grn",
        "hzl",
        "oth"
    )

    fun readPassportFile() {
        passportFile.useLines { lines ->
            lines.forEach {
                passports.add(it)
            }
        }
        sortPassports()
    }

    private fun sortPassports() {
        var passportString = ""
        for (line in passports) {
            if (line.isEmpty()) {
                passportsSorted.add(passportString)
                passportString = ""
            }
            passportString += "$line "
            //end of list
            if (passports.indexOf(line) == passports.size - 1) {
                passportsSorted.add(passportString)
            }
        }
    }

    fun validatePassportsByField() {
        var validPassports = 0
        passportsSorted.forEach { passport ->
            var requiredFieldsPresent = 0
            reqFields.forEach { req ->
                if (passport.contains(req)) {
                    requiredFieldsPresent++
                }
            }
            if (requiredFieldsPresent == 7) {
                validPassports++
            }
        }
        println("Valid passports: $validPassports")
    }

    fun validatePassportsByFieldAndReq() {
        var validPassports = 0
        println(passportsSorted.size)
        passportsSorted.forEach { passport ->
            var requiredFieldsPresent = 0
            var requiredFieldsValid = 0
            reqFields.forEach { req ->
                if (passport.contains(req)) {
                    requiredFieldsPresent++
                    val index = passport.lastIndexOf(req)
                    val splitOne = passport.substring(index)
                    val splitTwo = splitOne.substring(4)
                    val splitThree = splitTwo.split(" ")
                    val valueToCheck = splitThree[0]
                    when (req) {
                        birthYear -> if ((1920..2002).contains(valueToCheck.toInt())) {
                            requiredFieldsValid++
                        }
                        issueYear -> if ((2010..2020).contains(valueToCheck.toInt())) {
                            requiredFieldsValid++
                        }
                        expirationYear -> if ((2020..2030).contains(valueToCheck.toInt())) {
                            requiredFieldsValid++
                        }
                        height -> if (valueToCheck.contains("cm")) {
                            if ((150..193).contains(valueToCheck.removeSuffix("cm").toInt())) {
                                requiredFieldsValid++
                            }
                        } else if (valueToCheck.contains("in")) {
                            if ((59..76).contains(valueToCheck.removeSuffix("in").toInt())) {
                                requiredFieldsValid++
                            }
                        }
                        hairColor -> if (hairColorRegex.containsMatchIn(valueToCheck)) {
                            requiredFieldsValid++
                        }
                        eyeColor -> if (valueToCheck in eyeColors) {
                            requiredFieldsValid++
                        }
                        passportID -> if (valueToCheck.toIntOrNull() != null) {
                            if (valueToCheck.length == 9) {
                                requiredFieldsValid++
                            }
                        }
                    }
                }
            }
            if ((requiredFieldsPresent + requiredFieldsValid) == 14) {
                validPassports++
            }
        }
        println("Valid passports: $validPassports")
    }
}