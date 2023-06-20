package com.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.bookstore.Authentification.Welcome
import com.bookstore.Fragments.CreateBook
import com.bookstore.Fragments.Mainpage
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            navigation.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.item3 ->{
                        supportFragmentManager
                            .beginTransaction().
                            replace(R.id.place_holder, CreateBook.newInstance())
                            .commit()
                        drawer.closeDrawer(GravityCompat.START)
                    }
                }
                true
            }
        }

        supportFragmentManager
            .beginTransaction().
            replace(R.id.place_holder, Mainpage.newInstance())
            .commit()

        binding.bottomMenu.selectedItemId = R.id.mainpage
        binding.apply {
            bottomMenu.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu -> {
                        drawer.openDrawer(GravityCompat.START)
                    }
                    R.id.mainpage -> {
                        supportFragmentManager
                            .beginTransaction().
                            replace(R.id.place_holder, Mainpage.newInstance())
                            .commit()
                    }
                    R.id.logout -> {
                        Toast.makeText(applicationContext, "You`ve been Logged out!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Welcome::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
        }
    }
}