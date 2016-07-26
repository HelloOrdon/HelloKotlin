package com.helloordon.hellokotlin

import org.junit.Assert
import org.junit.Test

class GameOfLifeTest {

    @Test
    fun deadWorldShouldBeDeadAt_0_0() {
        val lives = emptyList<Pair<Int, Int>>()
        val cell = 0 to 0
        Assert.assertFalse(isWorldAlive(lives, cell))
    }

    @Test
    fun aliveCellShouldBeAliveAt_0_0() {
        val lives = listOf(0 to 0)
        val cell = 0 to 0
        Assert.assertTrue(isWorldAlive(lives, cell))
    }

    @Test
    fun worldWithAliveCellShouldBeDeadAt_0_0() {
        val lives = listOf(1 to 1)
        val cell = 0 to 0
        Assert.assertFalse(isWorldAlive(lives, cell))
    }

    @Test
    fun worldWithAliveCellAt_1_1_ShouldBeAliveAt_1_1() {
        val lives = listOf(1 to 1)
        val cell = 1 to 1
        Assert.assertTrue(isWorldAlive(lives, cell))
    }

    @Test
    fun singleCellShouldDieInNextRound() {
        val lives = listOf(0 to 0)
        val cell = 0 to 0
        Assert.assertFalse(isWorldAlive(goNextRound(lives), cell))
    }

    @Test
    fun groupOfFourCellsShouldSurviveInNextRound() {
        val lives = (0..1).flatMap { x -> (0..1).map { y -> x to y } }
        val cell = 0 to 0
        val nextRound = goNextRound(lives)
        Assert.assertTrue(isWorldAlive(nextRound, cell))
    }
}

fun goNextRound(lives: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    return lives.filter { shouldSurvive(lives, it) }
}

fun shouldSurvive(lives: List<Pair<Int, Int>>, cell: Pair<Int, Int>): Boolean {
    return getNeighbours(cell).count { lives.contains(it) } == 3
}

fun getNeighbours(cell: Pair<Int, Int>): List<Pair<Int, Int>> {
    return (-1..1).flatMap { dx -> (-1..1).map { dy -> (cell.first + dx) to (cell.second + dy) } }.filterNot { it == cell }
}

fun isWorldAlive(lives: List<Pair<Int, Int>>, cell: Pair<Int, Int>): Boolean {
    return lives.contains(cell);
}
