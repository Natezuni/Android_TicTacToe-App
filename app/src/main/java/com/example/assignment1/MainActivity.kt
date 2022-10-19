package com.example.assignment1

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var grid: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: MyViewModel by viewModels()






        grid = arrayOf(
            arrayOf(
                findViewById(R.id.top_left),
                findViewById(R.id.top_middle),
                findViewById(R.id.top_right)
            ),
            arrayOf(
                findViewById(R.id.middle_left),
                findViewById(R.id.middle_middle),
                findViewById(R.id.middle_right)
            ),
            arrayOf(
                findViewById(R.id.bottom_left),
                findViewById(R.id.bottom_middle),
                findViewById(R.id.bottom_right)
            )
        )

        //here
        for (i in grid.indices) {
            for (x in grid[i].indices) {
                grid[i][x].setOnClickListener {

                    if (grid[i][x].text == "") {
                        grid[i][x].text = model.turn
                        model.gridState[i][x] = model.turn

                        model.turn =
                            if (model.turn == "X") {
                                "O"
                            } else {
                                "X"
                            }
                        if(model.checkWinner(*grid)) {
                            Toast.makeText(this@MainActivity, "You won! Board will clear in 3 seconds", Toast.LENGTH_LONG).show()
                            CoroutineScope(Dispatchers.IO).launch {
                                delay(TimeUnit.SECONDS.toMillis(3))
                                withContext(Dispatchers.Main) {
                                    for (i in grid.indices) {
                                        for (x in grid[i].indices) {
                                            grid[i][x].text = ""
                                            grid[i][x].setBackgroundColor(Color.parseColor("#808080"))
                                            model.turn = "X"
                                        }
                                    }
                                }

                            }
//                            for (i in grid.indices) {
//                                for (x in grid[i].indices) {
//                                    if (grid[i][x].text == model.turn) {
//                                        grid[i][x].setBackgroundColor(Color.GREEN)
//                                    } else {
//                                        grid[i][x].setBackgroundColor(Color.RED)
//                                    }
//                                }
//                            }
                        } else {
                            var counter = 0
                            for (i in grid.indices) {
                                for (x in grid[i].indices) {
                                    if (grid[i][x].text != "") {
                                        counter += 1
                                    }
                                }
                            }
                            if (counter == 9) {
                                Toast.makeText(this@MainActivity, "This is a tie! You can reset the game!", Toast.LENGTH_LONG).show()
                            }

//                            if (model.state == "Switch1:ON") {
//                                model.checkTie(*grid)
//                                Toast.makeText(this@MainActivity, "made it here", Toast.LENGTH_SHORT).show()
//                            } else if (model.state == "Switch1:OFF") {
//                                Toast.makeText(this@MainActivity, "made it here2", Toast.LENGTH_SHORT).show()
//                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "This square is already filled in!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        var resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            for (i in grid.indices) {
                for (x in grid[i].indices) {
                    grid[i][x].text = ""
                    grid[i][x].setBackgroundColor(Color.parseColor("#808080"))
                    model.turn = "X"
                }
            }
        }
        var readmeButton = findViewById<Button>(R.id.readmeButton)
        readmeButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "I was not able to figure out how to keep the switch state when leaving the settings page. I have commented code showing my trying and not being able to figure it out. Besides the auto-clear, everything should work fine.", Toast.LENGTH_LONG).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.new_game -> {
                val intent = Intent(this, SettingsActivity::class.java)
                // start your next activity
                startActivity(intent)
                true
            }
            R.id.help -> {
                SettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


