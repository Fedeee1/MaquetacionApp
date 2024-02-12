package com.example.maquetacionapp.ui.main

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.maquetacionapp.R
import com.example.maquetacionapp.commons.MAX_NUM_OF_CHARS_ELEMENTS
import com.example.maquetacionapp.commons.MAX_NUM_OF_ELEMENTS
import com.example.maquetacionapp.commons.MIN_NUM_OF_CHARS_ELEMENTS
import com.example.maquetacionapp.commons.MIN_NUM_OF_ELEMENTS
import com.example.maquetacionapp.data.Element
import com.example.maquetacionapp.data.User
import com.example.maquetacionapp.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import kotlin.random.Random
import kotlin.random.nextInt

class MainViewModel : ViewModel() {

    private var listUsers: MutableList<User> = mutableListOf()
    var listUsersFlow: Flow<List<User>> = flow {
        listUsers = UsersRepository().listUsers
        emit(listUsers)
    }

    private var listElemets: MutableList<Element> = mutableListOf()
    var listElementsFlow: Flow<List<Element>> = flow {
        listElemets.clear()
        val totalElemens = totalRandomElements()
        var counter = 0

        while (counter != totalElemens) {
            listElemets.add(
                Element(
                    R.drawable.ic_launcher_background.toDrawable(),
                    createTextRandom(MIN_NUM_OF_CHARS_ELEMENTS, MAX_NUM_OF_CHARS_ELEMENTS),
                    getNumber(counter)
                )
            )
            counter++
        }
        emit(listElemets)
    }

    fun addElementViewModel(): Element {
        return Element(
            R.drawable.ic_launcher_background.toDrawable(),
            createTextRandom(MIN_NUM_OF_CHARS_ELEMENTS, MAX_NUM_OF_CHARS_ELEMENTS),
            showTwoDecimals(createNumberRandom())
        )
    }

    fun createParagraphRandom(minLength: Int, maxLength: Int): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('/') + ('/') + ('/')

        val paragraphLength = Random.nextInt(minLength until maxLength)
        var paragraph = ""

        for (i in 0..paragraphLength) {
            val charsPosition = Random.nextInt(chars.size)
            paragraph += chars[charsPosition]
        }
        return paragraph.replace('/', ' ')
    }

    private fun createTextRandom(minLength: Int, maxLength: Int): String {
        val chars = ('a'..'z') + ('A'..'Z')

        val paragraphLength = Random.nextInt(minLength until maxLength)
        var paragraph = ""

        for (i in 0..paragraphLength) {
            val charsPosition = Random.nextInt(chars.size)
            paragraph += chars[charsPosition]
        }

        return paragraph.trim()
    }

    private fun createNumberRandom(): Double {
        val numbers = ('0'..'9') + ('0'..'9')

        var number = ""
        var counter = 0

        while (counter < 5) {
            if (counter == 2) {
                number += '.'
                counter++
            } else {
                number += numbers[Random.nextInt(numbers.size)]
                counter++
            }

        }
        return number.toDouble()
    }

    private fun getNumber(position: Int): String {
        val listOfNumbers = mutableListOf<Double>()
        listOfNumbers.add(13.1985)
        listOfNumbers.add(21.5467)
        listOfNumbers.add(44.7851)
        listOfNumbers.add(98.2154)
        listOfNumbers.add(78.9566)
        listOfNumbers.add(23.4432)
        listOfNumbers.add(55.0976)
        listOfNumbers.add(19.0632)
        listOfNumbers.add(37.5553)
        listOfNumbers.add(12.6590)

        return showTwoDecimals(listOfNumbers[position])
    }

    private fun totalRandomElements(): Int {
        return Random.nextInt(MIN_NUM_OF_ELEMENTS until MAX_NUM_OF_ELEMENTS)
    }

    private fun showTwoDecimals(number: Double): String {
        val formatDecimals = DecimalFormat("#.00")
        return formatDecimals.format(number)
    }
}