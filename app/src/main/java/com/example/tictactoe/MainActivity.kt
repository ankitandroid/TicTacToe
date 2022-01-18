package com.example.tictactoe

import android.media.MediaDrm
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener{
    var PLAYER=true
    var TURN_COUNT=0
    var boardStatus=Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val DisplayText2=findViewById<TextView>(R.id.DisplayText)
//        val Button1=findViewById<Button>(R.id.Button1)
//        val Button2=findViewById<Button>(R.id.Button2)
//        val Button3=findViewById<Button>(R.id.Button3)
//        val Button4=findViewById<Button>(R.id.Button4)
//        val Button5=findViewById<Button>(R.id.Button5)
//        val Button6=findViewById<Button>(R.id.Button6)
//        val Button7=findViewById<Button>(R.id.Button7)
//        val Button8=findViewById<Button>(R.id.Button8)
//        val Button9=findViewById<Button>(R.id.Button9)
//        val Button10=findViewById<Button>(R.id.Button10)
        board= arrayOf(
            arrayOf(Button1,Button2,Button3),
            arrayOf(Button4,Button5,Button6),
            arrayOf(Button7,Button8,Button9)
        )
        for(i in board) {
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        Button10.setOnClickListener{
            TURN_COUNT=0
            PLAYER=true
            DisplayText.text="Player X Turn"
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] =-1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.Button1 ->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.Button2 ->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.Button3 ->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.Button4 ->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.Button5 ->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.Button6 ->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.Button7 ->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.Button8 ->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.Button9 ->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        PLAYER=!PLAYER
        TURN_COUNT++
        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }
        if(TURN_COUNT==9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[i][0]==0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[0][i]==0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateDisplay("Player X Winner")
            }
            else if(boardStatus[0][0]==0) {
                updateDisplay(("Player O Winner"))
            }
        }
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updateDisplay("Player X Winner")
            }
            else if(boardStatus[0][2]==0) {
                updateDisplay(("Player O Winner"))
            }
        }
    }

    private fun updateDisplay(text: String) {
        val DisplayText=findViewById<TextView>(R.id.DisplayText)
        DisplayText.text=text
        if(text.contains("Winner")){
            disableButton()
        }
    }
    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player)"X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}