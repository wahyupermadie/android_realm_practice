package com.example.wahyupermadi.kotlinsubmission2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.model.Matchs
import kotlinx.android.synthetic.main.nextmatch_list.view.*

class MatchAdapter (private val context: Context, private val matchs: List<Matchs>?, private val listener: (Matchs) -> Unit) : RecyclerView.Adapter<MatchsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MatchsViewHolder(LayoutInflater.from(context).inflate(R.layout.nextmatch_list, parent, false))


    override fun getItemCount(): Int {
        return matchs!!.size
    }

    override fun onBindViewHolder(holder: MatchsViewHolder, position: Int) {
        holder.bindItem(matchs!![position], listener)
    }
}

class MatchsViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun bindItem(nextMatch: Matchs, listener: (Matchs) -> Unit) {
        itemView.nm_date.text = nextMatch.dateEvent
        itemView.nm_awayteam.text = nextMatch.strAwayTeam
        itemView.nm_hometeam.text = nextMatch.strHomeTeam
        itemView.nm_awayscore.text = nextMatch.intAwayScore
        itemView.nm_homescore.text = nextMatch.intHomeScore

        itemView.setOnClickListener{
            listener(nextMatch)
        }

    }
}
