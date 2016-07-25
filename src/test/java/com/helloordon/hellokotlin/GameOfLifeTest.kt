package com.helloordon.hellokotlin

import org.junit.Assert
import org.junit.Test

class GameOfLifeTest {

    @Test
    fun shouldWorldWithAliveCellBeAliveAtThisCell() {
        val cell = Cell(0, 0)
        val world = World(setOf(cell));
        Assert.assertTrue(world.isAlive(cell));
    }

    @Test
    fun emptyWorldShouldBeDeadAt_0_0() {
        val cell = Cell(0, 0)
        val world = World(emptySet())
        Assert.assertFalse(world.isAlive(cell))
    }

    @Test
    fun shouldWorldWithAlive_0_0_BeDeadAt_1_1() {
        val cell_1_1 = Cell(1, 1)
        val world = World(setOf(Cell(0, 0)));
        Assert.assertFalse(world.isAlive(cell_1_1))
    }

    @Test
    fun shouldDeadWorldAfterTickBeDead() {
        val world = World(setOf())
        val newWorld = world.goNextRound();
        Assert.assertFalse(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun singleCellShouldDieInNextRound() {
        val world = World(setOf(Cell(0, 0)))
        val newWorld = world.goNextRound();
        Assert.assertFalse(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldCellOn_0_0_surviveFromTheGroupOfFour() {
        val aliveCells = setOf(Cell(0, 0), Cell(1, 0), Cell(0, 1), Cell(1, 1))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertTrue(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldCellAt_0_0_BeDeadWhenFourCellAreNotInGroup() {
        val aliveCells = setOf(Cell(0, 0), Cell(4, 0), Cell(0, 4), Cell(4, 4))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertFalse(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldAriseWhenThreeLivingNeighboursAround() {
        val aliveCells = setOf(Cell(1, 0), Cell(0, 1), Cell(1, 1))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertTrue(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldSurviveWithTwoNeighbours() {
        val aliveCells = setOf(Cell(0, 0), Cell(1, 0), Cell(0, 1))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertTrue(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldNotAriseWithTwoNeighbours() {
        val aliveCells = setOf(Cell(1, 0), Cell(0, 1))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertFalse(newWorld.isAlive(Cell(0, 0)))
    }

    @Test
    fun shouldAriseWhenThreeLivingNeighboursAroundEvenFarFrom_0_0() {
        val aliveCells = setOf(Cell(5, 4), Cell(4, 5), Cell(5, 5))
        val world = World(aliveCells)
        val newWorld = world.goNextRound();
        Assert.assertTrue(newWorld.isAlive(Cell(4, 4)))
    }
}
