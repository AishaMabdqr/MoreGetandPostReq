package com.example.moregetandpostreq

class GetNames {

    val name : List<Users>? = null

    class Users{

        var name: String? = null

        var location : String? = null

        constructor(  name : String?){
            this.name = name
        }
    }
}