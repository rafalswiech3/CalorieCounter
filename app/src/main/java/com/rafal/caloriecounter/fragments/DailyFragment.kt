package com.rafal.caloriecounter.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.adapters.MealsAdapter
import com.rafal.caloriecounter.adapters.ProductAdapter
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.data.MealTotals
import com.rafal.caloriecounter.databinding.FragmentDailyBinding
import com.rafal.caloriecounter.viewmodels.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import javax.inject.Inject

private const val MEALS_COUNT = 5;

@AndroidEntryPoint
class DailyFragment : Fragment(), MealsAdapter.MealsAdapterListener,
    ProductAdapter.ProductAdapterListener {
    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MealsAdapter

    val viewModel: DailyViewModel by viewModels()

    private val products = Array<MutableList<IngredientSearch>>(MEALS_COUNT) { mutableListOf() }

    private lateinit var meals: List<String>

    private val totalsList = Array<MealTotals>(MEALS_COUNT) { MealTotals(0f, 0f, 0f, 0f) }

    private lateinit var highlightedDate: String

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    private var calculatedBMR: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        highlightedDate = SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)
        calculatedBMR = sharedPrefs.getFloat(BMR_PREF, 0f)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meals = listOf(
            getString(R.string.breakfast),
            getString(R.string.second_breakfast),
            getString(R.string.lunch),
            getString(R.string.diner),
            getString(R.string.supper)
        )

        adapter = MealsAdapter(meals, this)
        val rv = binding.dailyRv
        rv.adapter = adapter

        updateDateTextView()

        loadAllProducts(highlightedDate)

        binding.dailyNextDayBtn.setOnClickListener {
            changeDate(1)
            loadAllProducts(highlightedDate)
        }

        binding.dailyPreviousDayBtn.setOnClickListener {
            changeDate(-1)
            loadAllProducts(highlightedDate)
        }
    }

    private fun loadAllProducts(date: String) {
        meals.forEachIndexed { index, s ->
            viewModel.loadProducts(index, date)
        }
    }

    override fun viewHolderBind(pos: Int, holder: MealsAdapter.ViewHolder) {
        viewModel.productsLiveDataArray.also {
            holder.binding.rv.adapter = ProductAdapter(products[pos], this, pos)
            it[pos].observe(viewLifecycleOwner) { list ->
                products[pos].clear()
                products[pos].addAll(list)

                totalsList[pos].clearAll()
                list.forEach { item ->
                    totalsList[pos].update(
                        kcal = item.nutrients.getCalories().amount,
                        fat = item.nutrients.getFat().amount,
                        carbs = item.nutrients.getCarbs().amount,
                        proteins = item.nutrients.getProtein().amount,
                    )
                }

                adapter.viewHolders[pos].updateTotals(totalsList[pos])

                holder.binding.rv.adapter?.let { adapter ->
                    adapter.notifyDataSetChanged()
                }

                updateDailyTotals(calculateDailyTotals())
            }
        }
    }

    override fun addItemClicked(pos: Int) {
        val action = DailyFragmentDirections.actionDailyFragmentToSearchFragment(
            mealID = pos,
            date = highlightedDate
        )
        findNavController().navigate(action)
    }

    override fun onProductRemoveClick(ingredient: IngredientSearch, mealIndex: Int) {
        viewModel.deleteProduct(ingredient, mealIndex)
    }

    private fun calculateDailyTotals(): MealTotals {
        val kcal = totalsList.sumOf {
            it.kcal.toDouble()
        }.toFloat()

        val fats = totalsList.sumOf {
            it.fats.toDouble()
        }.toFloat()

        val proteins = totalsList.sumOf {
            it.proteins.toDouble()
        }.toFloat()

        val carbs = totalsList.sumOf {
            it.carbs.toDouble()
        }.toFloat()

        return MealTotals(kcal = kcal, fats = fats, carbs = carbs, proteins = proteins)
    }

    private fun updateDailyTotals(totals: MealTotals) {
        binding.apply {
            dailyTotalsKcalTv.text = String.format("%.2f", totals.kcal) + " / " +
                    String.format("%.2f", calculatedBMR)
            dailyTotalsFatsTv.text = String.format("%.2f", totals.fats) + " g"
            dailyTotalsProteinsTv.text = String.format("%.2f", totals.proteins) + " g"
            dailyTotalsCarbsTv.text = String.format("%.2f", totals.carbs) + " g"
        }
    }

    private fun changeDate(byDays: Int) {
        val format = SimpleDateFormat("dd-MM-yyyy")
        val date = format.parse(highlightedDate)
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, byDays)
        highlightedDate = format.format(calendar.time)
        updateDateTextView()
    }

    private fun updateDateTextView() {
        binding.dailyDateTv.text = highlightedDate
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.daily_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.daily_menu_settings -> {
                val action = DailyFragmentDirections.actionDailyFragmentToCalculatorFragment()
                findNavController().navigate(action)
                true
            }
            else -> false
        }
    }
}