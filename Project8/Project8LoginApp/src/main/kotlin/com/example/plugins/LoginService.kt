package com.example.plugins

object LoginService {

    private var idCounter: Int = 0
    private var users: MutableMap<String, User> = mutableMapOf()

    fun addUser(user: User) : Boolean {
        if(!users.contains(user.login)){
            user.id = idCounter++
            users[user.login] = user
            return true
        }
        return false
    }

    fun isCorrectData(login: String, password: String) : Boolean {
        val user = users[login]
        return user != null && password == user.password
    }

}