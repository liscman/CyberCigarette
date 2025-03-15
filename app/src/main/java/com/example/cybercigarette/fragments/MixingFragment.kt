package com.example.cybercigarette.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cybercigarette.MainViewModel
import com.example.cybercigarette.R
import com.example.cybercigarette.adapters.MixingRecyclerAdapter
import com.example.cybercigarette.backend.mixing.MixingElement
import com.example.cybercigarette.databinding.FragmentMixingBinding

class MixingFragment : Fragment() {
    private var _binding: FragmentMixingBinding? = null
    private val binding get() = _binding!!

    private val vm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMixingBinding.inflate(inflater, container, false)

        setupRecycler()
        loadOutput()
        setupAddButton()

        return binding.root
    }

    fun setupAddButton() {
        binding.mixingAddBtn.setOnClickListener {
            vm.mixing.list.add(MixingElement(0, 0))
            binding.mixingContentRecycler.adapter?.notifyItemInserted(vm.mixing.list.size)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupRecycler(){
        binding.mixingContentRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.mixingContentRecycler.adapter = MixingRecyclerAdapter(vm){ action, i, value ->
            when(action){
                MixingRecyclerAdapter.ActionType.REMOVE -> {
                    vm.mixing.list.removeAt(i)
                    binding.mixingContentRecycler.adapter?.notifyDataSetChanged()
                    loadOutput()
                }
                MixingRecyclerAdapter.ActionType.EDIT_NICOTINE -> {
                    vm.mixing.list[i].nicotine = value
                    loadOutput()
                }
                MixingRecyclerAdapter.ActionType.EDIT_VOLUME -> {
                    vm.mixing.list[i].volume = value
                    loadOutput()
                }
            }
        }
    }

    fun loadOutput(){
        val volume = vm.mixing.getMixedVolume()
        val nicotine = vm.mixing.getMixedNicotine()

        binding.mixingVolumeTxt.setText("${getString(R.string.volume)}: $volume ml")
        binding.mixingNicotineTxt.setText("${getString(R.string.nicotine)}: $nicotine mg/ml")
    }
}