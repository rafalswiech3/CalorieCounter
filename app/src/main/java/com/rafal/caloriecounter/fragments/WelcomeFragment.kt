package com.rafal.caloriecounter.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(sharedPrefs.getFloat(BMR_PREF, 0f) != 0f) {
            navigateToDailyFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcomeNextBtn.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToCalculatorFragment()
            findNavController().navigate(action)
        }
    }

    private fun navigateToDailyFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToDailyFragment()
        findNavController().navigate(action)
    }

}