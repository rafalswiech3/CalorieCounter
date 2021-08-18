package com.rafal.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.databinding.FragmentCalculatorBinding
import com.rafal.caloriecounter.utilities.BMRCalculatorUtil
import com.rafal.caloriecounter.viewmodels.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calcCalculateBtn.setOnClickListener(calculateButtonListener)

        viewModel.bmrLiveData.observe(viewLifecycleOwner) { bmr ->
            binding.calcCalculatedBmrTv.visibility = View.VISIBLE
            binding.calcCalculatedBmrTv.text = bmr.toString() + " ${getString(R.string.kcal)}"
            binding.calcYourBmrTv.visibility = View.VISIBLE
            binding.calcApplyButton.visibility = View.VISIBLE
        }
    }

    private val calculateButtonListener = View.OnClickListener {
        var validationFailed = false
        if (!BMRCalculatorUtil.validateGender(binding.calcGenderSpinner.selectedItemPosition)) {
            (binding.calcGenderSpinner.selectedView as TextView).error =
                getString(R.string.item_required)
            validationFailed = true
        }
        if (!BMRCalculatorUtil.validateWeight(binding.calcWeightIn.text.toString())) {
            binding.calcWeightIn.error = getString(R.string.item_required)
            validationFailed = true
        }
        if (!BMRCalculatorUtil.validateHeight(binding.calcHeightIn.text.toString())) {
            binding.calcHeightIn.error = getString(R.string.item_required)
            validationFailed = true
        }
        if (!BMRCalculatorUtil.validateAge(binding.calcAgeIn.text.toString())) {
            binding.calcAgeIn.error = getString(R.string.item_required)
            validationFailed = true
        }
        if (!BMRCalculatorUtil.validateActivity(binding.calcActivitySpinner.selectedItemPosition)) {
            (binding.calcActivitySpinner.selectedView as TextView).error =
                getString(R.string.item_required)
            validationFailed = true
        }
        if (!BMRCalculatorUtil.validateGoal(binding.calcGoalSpinner.selectedItemPosition)) {
            (binding.calcGoalSpinner.selectedView as TextView).error =
                getString(R.string.item_required)
            validationFailed = true
        }

        if (!validationFailed) {
            viewModel.calculateBMR(
                gender = binding.calcGenderSpinner.selectedItemPosition,
                weight = binding.calcWeightIn.text.toString().toFloat(),
                height = binding.calcHeightIn.text.toString().toFloat(),
                age = binding.calcAgeIn.text.toString().toInt(),
                activity = binding.calcActivitySpinner.selectedItemPosition,
                goal = binding.calcGoalSpinner.selectedItemPosition
            )
        }
    }

}