package com.example.assignment1

import android.graphics.Color
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    //code

    var turn = "X"
    var state = "Switch:OFF"
    val gridState: Array<Array<String>> = arrayOf(
        arrayOf("-", "-", "-"),
        arrayOf("-", "-", "-"),
        arrayOf("-", "-", "-"),
    )

    fun checkTie(vararg grid: Array<Button>) {
        var counter = 0
        for(i in grid.indices) {
            for (x in grid[i].indices) {
                if (grid[i][x].text != "") {
                    counter +=1
                }

            }
        }

        if (counter == 9) {
            for(i in grid.indices) {
                for (x in grid.indices) {
                    grid[i][x].text = ""
                    grid[i][x].setBackgroundColor(Color.parseColor("#808080"))
                    turn = "X"

                }
            }

        } else {
            return
        }
    }

    fun checkWinner(vararg grid: Array<Button>): Boolean {
        //horizontal
        if (grid[0][0].text != "" && grid[0][0].text == grid[0][1].text && grid[0][1].text == grid[0][2].text) {
            grid[0][0].setBackgroundColor(Color.GREEN)
            grid[0][1].setBackgroundColor(Color.GREEN)
            grid[0][2].setBackgroundColor(Color.GREEN)
            return true
        } else if (grid[1][0].text != "" && grid[1][0].text == grid[1][1].text && grid[1][1].text == grid[1][2].text) {
            grid[1][0].setBackgroundColor(Color.GREEN)
            grid[1][1].setBackgroundColor(Color.GREEN)
            grid[1][2].setBackgroundColor(Color.GREEN)
            return true
        } else if (grid[2][0].text != "" && grid[2][0].text == grid[2][1].text && grid[2][1].text == grid[2][2].text) {
            grid[2][0].setBackgroundColor(Color.GREEN)
            grid[2][1].setBackgroundColor(Color.GREEN)
            grid[2][2].setBackgroundColor(Color.GREEN)
            return true
        }
        //vertical
        else if (grid[0][0].text != "" && grid[0][0].text == grid[1][0].text && grid[1][0].text == grid[2][0].text) {
            grid[0][0].setBackgroundColor(Color.GREEN)
            grid[1][0].setBackgroundColor(Color.GREEN)
            grid[2][0].setBackgroundColor(Color.GREEN)
            return true
        } else if (grid[0][1].text != "" && grid[0][1].text == grid[1][1].text && grid[1][1].text == grid[2][1].text) {
            grid[0][1].setBackgroundColor(Color.GREEN)
            grid[1][1].setBackgroundColor(Color.GREEN)
            grid[2][1].setBackgroundColor(Color.GREEN)
            return true
        } else if (grid[0][2].text != "" && grid[0][2].text == grid[1][2].text && grid[1][2].text == grid[2][2].text) {
            grid[0][2].setBackgroundColor(Color.GREEN)
            grid[1][2].setBackgroundColor(Color.GREEN)
            grid[2][2].setBackgroundColor(Color.GREEN)
            return true
        }

        //diagonal
        else if (grid[0][0].text != "" && grid[0][0].text == grid[1][1].text && grid[1][1].text == grid[2][2].text) {
            grid[0][0].setBackgroundColor(Color.GREEN)
            grid[1][1].setBackgroundColor(Color.GREEN)
            grid[2][2].setBackgroundColor(Color.GREEN)
            return true
        } else if (grid[0][2].text != "" && grid[0][2].text == grid[1][1].text && grid[1][1].text == grid[2][0].text) {
            grid[0][2].setBackgroundColor(Color.GREEN)
            grid[1][1].setBackgroundColor(Color.GREEN)
            grid[2][0].setBackgroundColor(Color.GREEN)
            return true
        }

        return false
    }

}

