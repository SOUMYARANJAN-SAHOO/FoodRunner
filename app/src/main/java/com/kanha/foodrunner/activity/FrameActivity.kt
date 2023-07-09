package com.kanha.foodrunner.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kanha.foodrunner.R
import com.kanha.foodrunner.databinding.ActivityFrameBinding
import com.kanha.foodrunner.fragments.DashboardFragment
import com.kanha.foodrunner.fragments.FAQFragment
import com.kanha.foodrunner.fragments.FavouriteFragment
import com.kanha.foodrunner.fragments.UserProfileFragment
import com.kanha.foodrunner.model.User


class FrameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //opening the dashboard by default
        openFragment(DashboardFragment(), "Dashboard", R.id.home)

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MySharedPref", MODE_PRIVATE)

        //setting up the drawer
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawer, R.string.open_draw, R.string.close_drawer)
        binding.drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //setting the hamburger icon
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawer.open()
        }

        //declaring the user
        val user = User(
            sharedPreferences.getString("name", "").toString(),
            sharedPreferences.getString("phone", "").toString(),
            sharedPreferences.getString("password", "").toString(),
            sharedPreferences.getString("email", "").toString(),
            sharedPreferences.getString("address", "").toString()
        )
        val navigationHeader = binding.navigationView.getHeaderView(0)
        navigationHeader.findViewById<TextView>(R.id.header_name).text = user.name
        navigationHeader.findViewById<TextView>(R.id.header_email).text = user.email

        //setting onclicks on the navigations
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    openFragment(DashboardFragment(), "Dashboard", R.id.home)
                    binding.drawer.close()
                }

                R.id.favourite -> {
                    openFragment(FavouriteFragment(), "Favourite", R.id.favourite)
                    binding.drawer.close()
                }

                R.id.faq -> {
                    openFragment(FAQFragment(), "FAQs", R.id.faq)
                    binding.drawer.close()
                }

                R.id.profile -> {
                    openFragment(UserProfileFragment(), "Profile", R.id.profile)
                    binding.drawer.close()
                }

                R.id.logout -> {
                    startActivity(Intent(this@FrameActivity, LoginActivity::class.java))
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

    //simple function to fragment to open fragments
    private fun openFragment(frag: Fragment, title: String, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.drawerFrame, frag)
            .commit()
        binding.topAppBar.title = title
        binding.navigationView.setCheckedItem(id)
    }
}