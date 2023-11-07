package com.chunkingz.walmartlogin

import java.io.Serializable

class User(val firstName: String, private val lastName: String, val userName: String, val password: String): Serializable {
    override fun toString(): String {
        return "User(firstName=$firstName, lastName=$lastName, emailID=$userName)"
    }
}
