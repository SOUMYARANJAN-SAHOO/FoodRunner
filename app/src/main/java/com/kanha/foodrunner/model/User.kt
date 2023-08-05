package com.kanha.foodrunner.model

class User {
    var name: String? = null
    private var phoneNumber: String? = null
    private var email: String? = null
    private var address: String? = null
    private var uid: String? = null

    constructor()

    constructor(name: String, phoneNumber: String, email: String, address: String, uid: String? ){
        this.name = name
        this.phoneNumber = phoneNumber
        this.email = email
        this.address = address
        this.uid = uid
    }
}