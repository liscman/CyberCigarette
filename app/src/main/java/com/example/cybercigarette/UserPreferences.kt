package com.example.cybercigarette

import android.content.Context
import android.content.SharedPreferences

/**
 * It contains user preferences and has functions to save and load
 * values as [Boolean], [Int], [Float], [Long] and [String]
 */
class UserPreferences (context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("CCPreferences", Context.MODE_PRIVATE)

    //TODO Remove example
    var example: Int
        get() = getInt("example", 0)
        set(value) = setInt("example", value)


    /** [Boolean] getter from preferences by [key]
     *
     * If preference not exists, returns [defaultValue]
     */
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    /** Setting preference by [key] as [Boolean] to [value] */
    private fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    /** [Int] getter from preferences by [key]
     *
     * If preference not exists, returns [defaultValue]
     */
    private fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    /** Setting preference by [key] as [Int] to [value] */

    private fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    /** [Float] getter from preferences by [key]
     *
     * If preference not exists, returns [defaultValue]
     */
    private fun getFloat(key: String, defaultValue: Float = 0F): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    /** Setting preference by [key] as [Float] to [value] */
    private fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    /** [Long] getter from preferences by [key]
     *
     * If preference not exists, returns [defaultValue]
     */
    private fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    /** Setting preference by [key] as [Long] to [value] */
    private fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    /** [String] getter from preferences by [key]
     *
     * If preference not exists, returns [defaultValue]
     */
    private fun getString(key: String, defaultValue: String = "ERROR Value not found"): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    /** Setting preference by [key] as [String] to [value] */
    private fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}