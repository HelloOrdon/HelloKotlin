package com.helloordon.hellokotlin

class World(val lives: Set<Cell>) {

    fun isAlive(cell: Cell) = lives.contains(cell)

    fun goNextRound(): World {
        return World(survivedCells() + arisenCells())
    }

    private fun survivedCells() = lives.filter { cell -> shouldSurvive(cell) }.toSet()

    private fun shouldSurvive(cell: Cell) = livingNeighboursCount(cell) == 3 || livingNeighboursCount(cell) == 2

    private fun arisenCells(): Set<Cell> {
        return lives.flatMap { cell -> cell.allNeighbours() }.filter { possibleAriseCell -> shouldArise(possibleAriseCell) }.toSet()
    }

    private fun shouldArise(cell: Cell) = livingNeighboursCount(cell) == 3

    private fun livingNeighboursCount(cell: Cell): Int {
        return cell.allNeighbours().filter { possibleNeighbour -> isAlive(possibleNeighbour) }.size
    }
}
