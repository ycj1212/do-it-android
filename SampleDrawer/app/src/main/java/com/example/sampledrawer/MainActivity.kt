package com.example.sampledrawer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // private lateinit var appBarConfiguration: AppBarConfiguration
    var fragment1: Fragment1? = null
    var fragment2: Fragment2? = null
    var fragment3: Fragment3? = null

    var drawerLayout: DrawerLayout? = null
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().add(R.id.container, fragment1!!).commit()

        /*
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        */
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menu1) {
            Toast.makeText(this, "첫 번째 메뉴 선택됨. ", Toast.LENGTH_LONG).show()
            onFragmentSelected(0, null)
        } else if (id == R.id.menu2) {
            Toast.makeText(this, "두 번째 메뉴 선택됨. ", Toast.LENGTH_LONG).show()
            onFragmentSelected(1, null)
        } else if (id == R.id.menu3) {
            Toast.makeText(this, "세 번째 메뉴 선택됨. ", Toast.LENGTH_LONG).show()
            onFragmentSelected(2, null)
        }

        drawerLayout?.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onFragmentSelected(position: Int, bundle: Bundle) {
        var curFragment: Fragment? = null

        if (position == 0) {
            curFragment = fragment1
            toolbar?.title = "첫 번째 화면"
        } else if (position == 1) {
            curFragment = fragment2
            toolbar?.title = "두 번째 화면"
        } else if (position == 2) {
            curFragment = fragment3
            toolbar?.title = "세 번째 화면"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
