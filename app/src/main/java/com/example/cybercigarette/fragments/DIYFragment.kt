package com.example.cybercigarette.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cybercigarette.databinding.FragmentDiyBinding

class DIYFragment : Fragment() {
    private var _binding: FragmentDiyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiyBinding.inflate(inflater, container, false)

        //TODO Empty fragment

        return binding.root
    }
}