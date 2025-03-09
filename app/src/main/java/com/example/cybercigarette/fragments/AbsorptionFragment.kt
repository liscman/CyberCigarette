package com.example.cybercigarette.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cybercigarette.databinding.FragmentAbsorptionBinding

class AbsorptionFragment : Fragment() {
    private var _binding: FragmentAbsorptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsorptionBinding.inflate(inflater, container, false)

        //TODO Empty fragment

        return binding.root
    }
}