package com.softvision.trivia

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}