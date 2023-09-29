package com.csm.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.w3c.dom.Text
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

data class Person(
    val name:String = "",
    val age:Int=-1
)

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
//        GlobalScope.launch {
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
//        GlobalScope.launch {
//
//            val tvDummy :TextView = findViewById(R.id.tvDummy)
//            GlobalScope.launch(Dispatchers.IO){
//                Log.d(TAG,"Starting coroutine in thread ${Thread.currentThread().name}")
//                val answer = callNetworkAnswer()
//                withContext(Dispatchers.Main){
//                    Log.d(TAG,"Setting text in thread ${Thread.currentThread().name}")
//                    tvDummy.text = answer
//                }
//            }


//        5. run blocking
//        Log.d(TAG,"Before runBlocking")
//        runBlocking {
//            Log.d(TAG,"Start of runBlocking")
//            delay(5000L)
//            Log.d(TAG,"End of runBlocking")
//
//        }
//        Log.d(TAG,"after runBlocking")

        //sama seperti
//        Log.d(TAG,"Before runBlocking")
//            Log.d(TAG,"Start of runBlocking")
//            Thread.sleep(5000L)
//            Log.d(TAG,"End of runBlocking")
//        Log.d(TAG,"after runBlocking")

//        Log.d(TAG,"Before runBlocking")
//        runBlocking {
//          launch {
//              delay(3000L)
//              Log.d(TAG,"Finished IO coroutine 1")
//          }
//            launch {
//                delay(3000L)
//                Log.d(TAG,"Finished IO coroutine 2")
//            }
//
//        }
//        Log.d(TAG,"after runBlocking")


//       6. Jobs, Waiting, Cancelation
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5)   {
//                Log.d(TAG,"Coroutine is still working...")
//                delay(1000L)
//            }
//        }

//        runBlocking {
//            job.join()
//            Log.d(TAG,"Main thread is continuiting...")
//        }


//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d(TAG,"Starting long running calculation...")
//            withTimeout(3000L){
//                for(i in 30..50){
//                    if(isActive){
//                        Log.d(TAG,"result for i = $i: ${fib(i)}")
//
//                    }
//                }
//            }
//
//            Log.d(TAG,"Ending long running calculation...")
//        }
//
//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG,"Canceled job: ")
//        }


//        7.Async and Await

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val answer1 = async { doCallNetwork1() }
//                val answer2 = async { doCallNetwor2() }
//
//                Log.d(TAG,"Answer 1 is ${answer1.await()}")
//                Log.d(TAG,"Answer 2 is ${answer2.await()}")
//            }
//            Log.d(TAG,"Request took $time ms.")
//        }


//        8. lifecycleScope and viewModelScope
//                    val btnStartActivity :Button = findViewById(R.id.btnStartActivity)
//        btnStartActivity.setOnClickListener{
//            lifecycleScope.launch {
//                while (true){
//                    delay(1000L)
//                    Log.d(TAG,"Still running...")
//
//                }
//            }
//            GlobalScope.launch {
//                delay(5000L)
//                Intent(this@MainActivity,SecondActivity::class.java).also{
//                    startActivity(it)
//                    finish()
//                }
//            }
//        }

//        9. Coroutines with Firebase Firestore
        val tvData:TextView = findViewById(R.id.tvData)
        val tutorialDocument = Firebase.firestore.collection("coroutine").document("tutorial")

//        val tutorialDocument = Firebase.firestore.collection("coroutine").document("tutorial")
        val peter = Person("Peter",24)
        GlobalScope.launch (Dispatchers.IO){
            delay(3000L)
            tutorialDocument.set(peter).await()
            val person = tutorialDocument.get().await().toObject(Person::class.java)
            withContext(Dispatchers.Main){
                tvData.text = person.toString()
            }
        }

    }

    suspend fun doCallNetwork1():String
    {
        delay(3000L)
        return "Answer 1"
    }


    suspend fun doCallNetwor2():String
    {
        delay(3000L)
        return "Answer 2"
    }

    fun fib(n:Int):Long
    {
        return if (n == 0) 0
        else if(n == 1) 1
        else fib(n-1) + fib(n-2)
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

