package com.example.PS_MemeApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.PS_MemeApp.adapters.ViewPagerAdapter
import com.example.PS_MemeApp.viewmodel.MemesViewModel
import com.example.PS_MemeApp.viewmodel.MemesViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var memesviewModel: MemesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val repository=(application as MyApplication).memesRepository

        ViewModelProvider(this, MemesViewModelFactory(repository)).get(MemesViewModel::class.java)
            .also { memesviewModel = it }
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val shareButton = findViewById<Button>(R.id.shareButton)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val nextButton = findViewById<Button>(R.id.nextbutton)

        memesviewModel.memes.observe(this) {
            val adapter = ViewPagerAdapter(this,it.data.memes)
            viewPager.adapter = adapter

            viewPager.offscreenPageLimit = 3
        }

        deleteButton.setOnClickListener {
            Log.d("MAIN", viewPager.currentItem.toString())
            memesviewModel.memes.observe(this){
                memesviewModel.deleteMeme(it.data.memes[viewPager.currentItem])
            }

            Toast.makeText(applicationContext, "DELETED", Toast.LENGTH_SHORT).show()
        }

        shareButton.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            val body = "Your body here"
            val sub = "Your Subject"
            myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
            myIntent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(myIntent, "Share Using"))
        }

        prevButton.setOnClickListener{
            if (viewPager.currentItem == 0){
                Toast.makeText(this,"You are on start",Toast.LENGTH_SHORT).show()
            }
            else{
                viewPager.currentItem--
            }
        }
        nextButton.setOnClickListener{
            viewPager.currentItem++
        }
    }
}


