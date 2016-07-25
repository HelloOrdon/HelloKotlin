package com.helloordon.hellokotlin

data class Cell(val x: Int, val y: Int) {
    fun allNeighbours() = (-1..1).flatMap { dx -> (-1..1).map { dy -> Cell(x + dx, y + dy) } }.filterNot { it == this }
}
