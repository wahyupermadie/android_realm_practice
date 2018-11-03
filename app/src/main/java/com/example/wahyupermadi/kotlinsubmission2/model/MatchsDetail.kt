package com.example.wahyupermadi.kotlinsubmission2.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class MatchsDetail(
        @PrimaryKey var id: Long = 0,
        var strAwayFormation : String,
        var strHomeFormation : String,
        var idEvent : String,
        var strHomeTeam : String,
        var strAwayTeam : String,
        var intHomeShots : String,
        var intAwayShots : String,
        var strHomeLineupGoalkeeper : String,
        var strHomeLineupDefense: String,
        var strHomeLineupMidfield: String,
        var strHomeLineupForward: String,
        var strHomeLineupSubstitutes: String,
        var strAwayLineupGoalkeeper : String,
        var strAwayLineupDefense: String,
        var strAwayLineupMidfield: String,
        var strAwayLineupForward: String,
        var strAwayLineupSubstitutes: String,
        var idHomeTeam : String,
        var idAwayTeam : String,
        var intAwayScore: String,
        var intHomeScore: String,
        var dateEvent : String,
        var strAwayGoalDetails : String,
        var strHomeGoalDetails : String
)