package com.example.maquetacionapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.maquetacionapp.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.random.nextInt

class MainViewModel(): ViewModel() {

    private var listUsers: MutableList<User> = mutableListOf()

    var listUsersFlow : Flow<List<User>> = flow {
        listUsers.add(User("https://i.pinimg.com/originals/64/81/22/6481225432795d8cdf48f0f85800cf66.jpg", "a", 1, "M", "d"))
        listUsers.add(User("https://d29jd5m3t61t9.cloudfront.net/static/images/comprofiler/gallery/registered/registered_m.png", "a", 1, "M", "d"))
        listUsers.add(User("https://d29jd5m3t61t9.cloudfront.net/static/images/comprofiler/gallery/registered/registered_m.png", "a", 1, "M", "d"))
        listUsers.add(User("https://d29jd5m3t61t9.cloudfront.net/static/images/comprofiler/gallery/registered/registered_m.png", "a", 1, "M", "d"))
        listUsers.add(User("https://d29jd5m3t61t9.cloudfront.net/static/images/comprofiler/gallery/registered/registered_m.png", "a", 1, "M", "d"))
        emit(listUsers)
    }

    fun createParagraphRandom(minLength: Int, maxLength: Int): String{
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + '/' + '/' + '/'

        var paragraph = ""
        var paragraphLength = Random.nextInt(minLength until maxLength)
        var space = Random.nextInt(chars.size)
        var counter = 0

        while (counter != paragraphLength){
            var charsPosition = Random.nextInt(chars.size)
            if (chars[charsPosition] == chars.last() || chars[charsPosition] == chars.last()-1 || chars[charsPosition] == chars.last()-2){
                paragraph += " "
                counter++
            } else {
                paragraph += chars[charsPosition]
                counter++
            }

        }
        return paragraph
    }

}