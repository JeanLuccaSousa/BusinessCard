package me.dio.businesscard.UI

import android.content.Intent
import gitandroid.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import me.dio.businesscard.App
import me.dio.businesscard.databinding.ActivityMainBinding
import me.dio.businesscard.util.image.Companion.share

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {
            card -> Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, { businessCards ->
            adapter.submitList(businessCards)
        })
    }
}