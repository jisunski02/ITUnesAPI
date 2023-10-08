package com.apple.itunesapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.apple.itunesapi.databinding.ActivityMainBinding
import com.apple.itunesapi.presentation.adapter.MoviesAdapter
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModel
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Annotate @AndroidEntrypoint to access the generated hilt module and any related components and to provide viewmodel factory
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MoviesViewModelFactory
    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMovies.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

    }
}