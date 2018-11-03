package com.example.wahyupermadi.kotlinsubmission2.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Matchs : RealmObject() {
    @PrimaryKey var _ID: Int? = 1
    var idEvent: String? = null
    var strHomeTeam: String? = null
    var strAwayTeam: String? = null
    var intHomeScore: String? = null
    var intAwayScore: String? = null
    var dateEvent: String? = null
}

