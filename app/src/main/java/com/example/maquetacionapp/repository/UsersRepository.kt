package com.example.maquetacionapp.repository

import com.example.maquetacionapp.data.User


data class UsersRepository(
    var listUsers: MutableList<User> = mutableListOf()
) {
    init {
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
    }
}


