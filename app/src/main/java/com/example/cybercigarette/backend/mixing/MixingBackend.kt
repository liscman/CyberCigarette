package com.example.cybercigarette.backend.mixing

class MixingBackend {
    val list = mutableListOf<MixingElement>(
        MixingElement(20, 10),
        MixingElement(0, 10),
    )

    fun getMixedVolume() = list.sumOf { it.volume }

    fun getMixedNicotine() =
        if (list.sumOf { it.volume } > 0) list.sumOf { it.nicotine*it.volume }/list.sumOf { it.volume } else 0
}