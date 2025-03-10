package com.example.cybercigarette.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.cybercigarette.R
import com.example.cybercigarette.backend.CoilBackend
import com.example.cybercigarette.databinding.FragmentCoilBinding


class CoilFragment : Fragment() {
    private var _binding: FragmentCoilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoilBinding.inflate(inflater, container, false)

        CoilBackend.buildChart(binding.coilMainChart, requireContext())
        hideOutput()
        addListeners()

        return binding.root
    }

    private fun addListeners() {
        //Power by resistance
        val suggestions =
            arrayOf("0.1", "0.15", "0.2", "0.3", "0.4", "0.5", "0.6", "0.8", "1.0", "1.2")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, suggestions)
        binding.coilResistanceInput.setAdapter(adapter)

        binding.coilResistanceBtn.setOnClickListener {
            hideOutput()
            var resistance = binding.coilResistanceInput.text.toString().toFloatOrNull()
            if (resistance != null) {
                if (resistance !in 0.05..1.9){
                    resistance = 0.6f
                    binding.coilResistanceInput.setText("0.6")
                }
                binding.coilResistanceMinimumOutTxt.text = "${CoilBackend.minimumPower(resistance)}W"
                binding.coilResistanceRecommendedOutTxt.text = "${CoilBackend.recommendedPower(resistance)}W"
                binding.coilResistanceMaximumOutTxt.text = "${CoilBackend.maximumPower(resistance)}"
                binding.coilResistanceOutputContainer.visibility = View.VISIBLE
            }else
                toast("${getString(R.string.error)}: ${getString(R.string.wrong_resistance)}")
        }


        //Resistance by power
        binding.coilPowerBtn.setOnClickListener {
            hideOutput()
            var power = binding.coilPowerInput.text.toString().toIntOrNull()
            if (power != null) {
                if (power !in 1..250){
                    power = 60
                    binding.coilPowerInput.setText("60")
                }
                binding.coilPowerMinimumOutTxt.text = "${CoilBackend.minimumResistance(power)}Ω"
                binding.coilPowerRecommendedOutTxt.text = "${CoilBackend.recommendedResistance(power)}Ω"
                binding.coilPowerMaximumOutTxt.text = "${CoilBackend.maximumResistance(power)}Ω"
                binding.coilPowerOutputContainer.visibility = View.VISIBLE
            }else
                toast("${getString(R.string.error)}: ${getString(R.string.wrong_power)}")
        }
    }

    private fun hideOutput() {
        binding.coilPowerOutputContainer.visibility = View.GONE
        binding.coilResistanceOutputContainer.visibility = View.GONE
    }

    private fun toast(text: String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
