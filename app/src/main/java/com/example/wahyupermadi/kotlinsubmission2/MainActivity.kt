package com.example.wahyupermadi.kotlinsubmission2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import com.example.wahyupermadi.kotlinsubmission2.fragments.NextMatchFragment
import com.example.wahyupermadi.kotlinsubmission2.fragments.PreviusMatchFragment

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        defaultNav()
    }

    private fun defaultNav() {
        toolbar.title = "Football Match Schedule"
        val prevMatch = PreviusMatchFragment.newInstance()
        openFragment(prevMatch)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nextmatch -> {
                toolbar.title = "Football Match Schedule"
                val nextMatch = NextMatchFragment.newInstance()
                openFragment(nextMatch)
                return@OnNavigationItemSelectedListener true
            }
            R.id.previewsmatch -> {
                toolbar.title = "Football Match Schedule"
                val prevMatch = PreviusMatchFragment.newInstance()
                openFragment(prevMatch)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction =  supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
