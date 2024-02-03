package com.example.maquetacionapp.ui.main

import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModel
import com.example.maquetacionapp.R
import com.example.maquetacionapp.commons.MAX_NUM_OF_ELEMENTS
import com.example.maquetacionapp.commons.MIN_NUM_OF_ELEMENTS
import com.example.maquetacionapp.data.Element
import com.example.maquetacionapp.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import kotlin.random.Random
import kotlin.random.nextInt

class MainViewModel() : ViewModel() {

    private var listUsers: MutableList<User> = mutableListOf()
    var listUsersFlow: Flow<List<User>> = flow {
        listUsers.add(
            User(
                "https://cdn-icons-png.flaticon.com/512/7312/7312416.png",
                "Gustavo",
                24,
                "M",
                "Estudiante de medicina"
            )
        )
        listUsers.add(
            User(
                "https://cdn-icons-png.flaticon.com/512/5026/5026324.png",
                "Carlos",
                35,
                "M",
                "Diseñador gráfico"
            )
        )
        listUsers.add(
            User(
                "https://cdn1.iconfinder.com/data/icons/website-internet/48/website_-_female_business-1024.png",
                "Marta",
                18,
                "F",
                "Estudiante de arquitectura"
            )
        )
        listUsers.add(
            User(
                "https://glassstreetclinic.com.au/wp-content/uploads/2021/09/3440841_business_male_man_office_people_icon.png",
                "Federico",
                27,
                "M",
                "Aprendiendo Android"
            )
        )
        listUsers.add(
            User(
                "https://cdn-icons-png.flaticon.com/512/9039/9039556.png",
                "Rodrigo",
                56,
                "M",
                "Profesor en la universidad"
            )
        )
        emit(listUsers)
    }

    private var listElemets: MutableList<Element> = mutableListOf()
    var listElementsFlow: Flow<List<Element>> = flow {
        var totalElemens = totalRandomElements()
        var counter = 0

        while (counter != totalElemens) {
            listElemets.add(
                Element(
                    R.drawable.ic_launcher_background.toDrawable(),
                    createParagraphRandom(10,120),
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
            createParagraphRandom(10, 120),
            showTwoDecimals(createNumberRandom())
        )
    }
    fun createParagraphRandom(minLength: Int, maxLength: Int): String {
        val chars = ('a'..'z') + ('A'..'Z') + '/' + '/' + '/'

        var paragraph = ""
        val paragraphLength = Random.nextInt(minLength until   maxLength)
        var counter = 0

        while (counter < paragraphLength+1) {

            var charsPosition = Random.nextInt(chars.size)
            if (chars[charsPosition] == chars.last() || chars[charsPosition] == chars.last() - 1 || chars[charsPosition] == chars.last() - 2) {
                paragraph += " "
            } else {
                paragraph += chars[charsPosition]
                counter++
            }
        }

        return paragraph
    }

    fun createNumberRandom(): Double {
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

    fun getNumber(position: Int): String {
        var listOfNumbers = mutableListOf<Double>()
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

    fun totalRandomElements(): Int {
        return Random.nextInt(MIN_NUM_OF_ELEMENTS until MAX_NUM_OF_ELEMENTS)
    }

    fun showTwoDecimals(number: Double): String {
        var formatDecimals = DecimalFormat("#.00")
        return formatDecimals.format(number)
    }

}