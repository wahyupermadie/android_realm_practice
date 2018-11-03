package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.DetailMatchActivity
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.adapter.MatchAdapter
import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import com.example.wahyupermadi.kotlinsubmission2.model.Matchs
import com.example.wahyupermadi.kotlinsubmission2.model.MatchsReponse
import io.realm.Realm
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.jetbrains.anko.support.v4.startActivity





class NextMatchFragment : Fragment(){
    lateinit var matchAdapter : MatchAdapter
    lateinit var recyclerView: RecyclerView
    var realm = Realm.getDefaultInstance()
    var matchs : MutableList<Matchs> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_nextmatch, container, false)
        recyclerView = rootView.findViewById(R.id.rv_nextmatch) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        getMatch()
        return rootView

    }

    private fun getMatch() {
        val progressBar = indeterminateProgressDialog("Hello! Please wait...")
        progressBar.show()

        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getNextMatch()
        call.enqueue(object : Callback<MatchsReponse>{
            override fun onFailure(call: Call<MatchsReponse>, t: Throwable) {
                toast("error "+t)
                progressBar.hide()
            }

            override fun onResponse(call: Call<MatchsReponse>, response: Response<MatchsReponse>) {
                val matchList: List<Matchs>? = response.body()?.events!!
                matchAdapter = MatchAdapter(activity!!.applicationContext, matchList){
                    getDetail(it.idEvent!!)
                }
                recyclerView.setAdapter(matchAdapter)

                matchs.clear()
                matchs.addAll(response.body()?.events!!)

                for((index, match) in matchs.withIndex()){
                    realm.beginTransaction()

                    // increatement index
                    val maxID = realm.where(Matchs::class.java).max("_ID")
                    val nextID: Int
                    if (maxID == null) {
                        nextID = 1
                    } else {
                        nextID = maxID.toInt() + 1
                    }
                    val obj = realm.createObject(Matchs::class.java, nextID)

                    // insert new value

                    obj.idEvent = match.idEvent
                    obj.strHomeTeam = match.strHomeTeam
                    obj.strAwayTeam = match.strAwayTeam
                    obj.intHomeScore = match.intHomeScore
                    obj.intAwayScore = match.intAwayScore
                    obj.dateEvent = match.dateEvent

                    realm.commitTransaction()
                }

                var data = mutableListOf(String())
                matchs = realm.where(Matchs::class.java).findAll()
                for(i in matchs){
                    Log.d("DataMatch", matchs.toString())
                }
                progressBar.hide()

            }

        })
    }

    private fun getDetail(idEvent: String) {
        startActivity<DetailMatchActivity>("id" to "${idEvent}")
    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }
}