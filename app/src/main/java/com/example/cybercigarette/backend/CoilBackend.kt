package com.example.cybercigarette.backend

import android.content.Context
import com.example.cybercigarette.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.Legend
import kotlin.math.sqrt

object CoilBackend {
    fun minimumPower(resistance: Float) =
        (3f/(resistance*resistance)).toInt()
    fun recommendedPower(resistance: Float) =
        (minimumPower(resistance)+ maximumPower(resistance))/2
    fun maximumPower(resistance: Float) =
        (16f/resistance).toInt()

    fun minimumResistance(power: Int) =
        (3f/((sqrt(3f / power.toFloat() - 0.1f)))).getCorrectResistance()
    fun recommendedResistance(power: Int) =
        ((minimumResistance(power) + maximumResistance(power))/2).getCorrectResistance()
    fun maximumResistance(power: Int) =
        (16f/(power.toFloat())).getCorrectResistance()

    private fun Float.getCorrectResistance() = when{
        this < 0.01 -> 0.0
        this < 0.12 -> 0.1
        this < 0.18 -> 0.15
        this < 0.25 -> 0.2
        this < 0.35 -> 0.3
        this < 0.45 -> 0.4
        this < 0.55 -> 0.5
        this < 0.7 -> 0.6
        this < 0.9 -> 0.8
        this < 1.1 -> 1.0
        this < 1.5 -> 1.2
        else -> 0.0
    }.toFloat()


    fun buildChart(chart: LineChart, context: Context) {
        val xResistance = listOf(0.1f, 0.15f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.8f, 1.0f)

        val yMinimumPower = xResistance.map { minimumPower(it).toFloat() }
        val yRecommendedPower = xResistance.map { recommendedPower(it).toFloat() }
        val yMaximumPower = xResistance.map { maximumPower(it).toFloat() }

        val entriesYellow = xResistance.mapIndexed { index, x -> Entry(x, yMinimumPower[index]) }
        val entriesGreen = xResistance.mapIndexed { index, x -> Entry(x, yRecommendedPower[index]) }
        val entriesRed = xResistance.mapIndexed { index, x -> Entry(x, yMaximumPower[index]) }

        val lineDataSetYellow = LineDataSet(entriesYellow, context.getString(R.string.minimum_power))
        lineDataSetYellow.color = android.graphics.Color.YELLOW
        lineDataSetYellow.setDrawCircles(false)
        lineDataSetYellow.setDrawValues(false)
        lineDataSetYellow.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineDataSetGreen = LineDataSet(entriesGreen, context.getString(R.string.recommended_power))
        lineDataSetGreen.color = android.graphics.Color.GREEN
        lineDataSetGreen.setDrawCircles(false)
        lineDataSetGreen.setDrawValues(false)
        lineDataSetGreen.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineDataSetRed = LineDataSet(entriesRed, context.getString(R.string.maximum_power))
        lineDataSetRed.color = android.graphics.Color.RED
        lineDataSetRed.setDrawCircles(false)
        lineDataSetRed.setDrawValues(false)
        lineDataSetRed.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineData = LineData(lineDataSetRed, lineDataSetGreen, lineDataSetYellow)

        chart.data = lineData

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setGranularity(0.1f)
        xAxis.labelRotationAngle = 45f
        xAxis.axisMinimum = 0.1f
        xAxis.axisMaximum = 1.0f
        xAxis.setLabelCount(10, true)
        xAxis.setDrawLabels(true)

        val yAxis = chart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 200f
        yAxis.setLabelCount(6, true)

        chart.axisRight.isEnabled = false

        chart.axisLeft.axisMinimum = 0f
        chart.axisLeft.setDrawGridLines(true)
        chart.xAxis.axisMinimum = 0.1f


        chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        chart.legend.orientation = Legend.LegendOrientation.VERTICAL
        chart.legend.setDrawInside(false)


        chart.setDrawGridBackground(false)
        chart.invalidate()
    }
}