package com.csm.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1. coroutine
        //2. First Coroutine
//        GlobalScope.launch {
//            delay(3000L)
//            Log.d(TAG,"coroutines says hello from threads  ${Thread.currentThread().name}")
//
//        }
//        Log.d(TAG,"Hello coroutines from thread ${Thread.currentThread().name}")

        //3. suspend fucntion coroutine

//        delay(3000L) // error karena suspend function bagian dari coroutine
        GlobalScope.launch {
//            //jadi 3 detik
//           val callNetwork1 =  callNetworkAnswer()
//            Log.d(TAG, callNetwork1)
//
//            //tunggu 3 detik
//            val callNework2 = callNetworkAnswer2()
//            Log.d(TAG, callNework2)

            //jadi 6 tunggu detik
//            val callNetwork1 =  callNetworkAnswer()
//            val callNework2 = callNetworkAnswer2()
//            Log.d(TAG, callNetwork1)
//            Log.d(TAG, callNework2)



//            4. Coroutine Context
            val tvDummy :TextView = findViewById(R.id.tvDummy)
            GlobalScope.launch(Dispatchers.IO){
                Log.d(TAG,"Starting coroutine in thread ${Thread.currentThread().name}")
                val answer = callNetworkAnswer()
                withContext(Dispatchers.Main){
                    Log.d(TAG,"Setting text in thread ${Thread.currentThread().name}")
                    tvDummy.text = answer
                }
            }




        }
    }

    //doCallNetworkAnswer()
   suspend fun callNetworkAnswer():String{
        delay(3000L)
        return "This is answer 1"
    }

    suspend fun callNetworkAnswer2():String{
        delay(3000L)
        return "This is answer 2"
    }
}