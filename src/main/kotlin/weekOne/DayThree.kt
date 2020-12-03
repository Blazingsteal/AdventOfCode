package weekOne

import java.io.File

class DayThree {

    private val slopeMap = mutableListOf<String>()
    private val slopeMapFile = File("./dayThree.txt")
    private val tree = "#"

    fun readMapFile() {
        slopeMapFile.useLines { lines ->
            lines.forEach {
                slopeMap.add(it)
            }
        }
        println(slopeMap)
        println(slopeMap.size)
    }

    fun traverseMap(right: Int, down: Int): Long{
        val startX = 0
        val startY = 0
        var treesEncountered = 0L
        val mapWidth = 30

        var x = startX

        for(y in startY until slopeMap.size step down){
            if (slopeMap[y][x].toString() == tree){
                treesEncountered++
            }
            if (x + right > mapWidth){
                x = (x + right) - mapWidth-1
            } else {
                x += right
            }
        }
        println("Trees encountered: $treesEncountered")

        return treesEncountered
    }
}