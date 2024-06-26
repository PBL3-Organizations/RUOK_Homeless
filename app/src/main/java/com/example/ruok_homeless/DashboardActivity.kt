package com.example.ruok_homeless

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ruok_homeless.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //네비게이션 드로어 바인딩
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.app_name, R.string.app_name)
        toggle.syncState()


        setFragment(DashboardFragment())

        //네비게이션 드로어 선택 시 해당 Fragment로 전환
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tabLogout -> setFragment(LogoutFragment())
                R.id.tabInfoRevision -> setFragment(CheckNumFragment())
                R.id.tabDashboard -> setFragment(DashboardFragment())
                R.id.tabList -> setFragment(RequestFragment())
                R.id.tabAccess -> setFragment(AccessFragment())
            }
            binding.drawerLayout.closeDrawers()
            false
        }

    }
    //나중에 값 전달시 용이하게 함수로 작성함
    fun setFragment(fragment: Fragment){
        var bundle = Bundle()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.rootLayout, fragment).commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

