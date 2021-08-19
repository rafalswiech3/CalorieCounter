package com.rafal.caloriecounter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafal.caloriecounter.adapters.MealsAdapter
import com.rafal.caloriecounter.adapters.ProductAdapter
import com.rafal.caloriecounter.databinding.FragmentDailyBinding


class DailyFragment : Fragment(), MealsAdapter.AddItemClickListener {
    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private val breakFastData = mutableListOf<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meals = listOf(
            getString(R.string.breakfast),
            getString(R.string.second_breakfast),
            getString(R.string.lunch),
            getString(R.string.diner),
            getString(R.string.supper)
        )

        val adapter = MealsAdapter(meals, this)
        val rv = binding.dailyRv
        rv.adapter = adapter

//        val adapter = ProductAdapter(breakFastData)
//        val breakfastRecyclerView = binding.dailyBreakfastRv
//        breakfastRecyclerView.adapter = adapter
//
//        binding.dailyBreakfastLayout.setOnClickListener {
//            if (breakfastRecyclerView.visibility == View.VISIBLE)
//                breakfastRecyclerView.visibility = View.GONE
//            else
//                breakfastRecyclerView.visibility = View.VISIBLE
//        }
//
//        binding.dailyBreakfastAdd.setOnClickListener {
//            Log.d("TAG", "BREAKFAAST ADD")
//            breakFastData.add("Mleko")
//            adapter.notifyDataSetChanged()
//        }
    }

    override fun addItemClicked(pos: Int) {
        Log.d("TAG", "Clicked: $pos")
    }
}