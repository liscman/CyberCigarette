package com.example.cybercigarette.adapters

import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cybercigarette.MainViewModel
import com.example.cybercigarette.databinding.RowMixingElementBinding

class MixingRecyclerAdapter(
    private val vm: MainViewModel,
    private val cb: (ActionType, Int, Int) -> Unit,
): RecyclerView.Adapter<MixingRecyclerAdapter.MixingRecyclerViewHolder>() {
    private var _binding: RowMixingElementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MixingRecyclerViewHolder {
        _binding = RowMixingElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MixingRecyclerViewHolder(binding)
    }

    override fun getItemCount() = vm.mixing.list.size


    override fun onBindViewHolder(holder: MixingRecyclerViewHolder, position: Int) {
        val item = vm.mixing.list[position]
        holder.binding.rowMixingNicotineInput.setText(item.nicotine.toString())
        holder.binding.rowMixingVolumeInput.setText(item.volume.toString())
        holder.binding.rowMixingNumber.setText((position+1).toString())

        val handler = android.os.Handler(Looper.getMainLooper())

        holder.binding.rowMixingNicotineInput.doAfterTextChanged {
            handler.post {
                cb(
                    ActionType.EDIT_NICOTINE,
                    position,
                    holder.binding.rowMixingNicotineInput.text.toString().toIntOrNull() ?: 0
                )
            }
        }
        holder.binding.rowMixingVolumeInput.doAfterTextChanged {
            handler.post {
                cb(
                    ActionType.EDIT_VOLUME,
                    position,
                    holder.binding.rowMixingVolumeInput.text.toString().toIntOrNull() ?: 0
                )
            }
        }
        holder.binding.rowMixingRemoveBtn.setOnClickListener {
            cb(ActionType.REMOVE, position, -1)
        }
    }

    enum class ActionType{
        REMOVE,
        EDIT_NICOTINE,
        EDIT_VOLUME,
    }

    inner class MixingRecyclerViewHolder(val binding: RowMixingElementBinding): RecyclerView.ViewHolder(binding.root)
}
