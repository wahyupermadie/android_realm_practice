package com.example.wahyupermadi.kotlinsubmission2.`interface`

import com.example.wahyupermadi.kotlinsubmission2.model.Matchs
import io.realm.Realm

interface MatchInterface {
    fun addStudent(realm: Realm, matchs: Matchs): Boolean
}