package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviusMatchFragment: Fragment(){
    lateinit var matchAdapter : MatchAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        val rootView = inflater.inflate(R.layout.fragment_prevmatch, container, false)
        recyclerView = rootView.findViewById(R.id.rv_prevmatch) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        getMatch()
        return rootView
    }
    private fun getMatch() {
        val progressBar = indeterminateProgressDialog("Hello! Please wait...")
        progressBar.show()

        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getPastMatch()
        call.enqueue(object : Callback<MatchsReponse> {
            override fun onFailure(call: Call<MatchsReponse>, t: Throwable) {
                toast("error "+t)
                progressBar.hide()
            }

            override fun onResponse(call: Call<MatchsReponse>, response: Response<MatchsReponse>) {
                var matchList: List<Matchs>? = response.body()?.events!!
                matchAdapter = MatchAdapter(activity!!.applicationContext, matchList){
                    getDetail(it.idEvent!!)
                }
                recyclerView.setAdapter(matchAdapter)

                progressBar.hide()

            }

        })
    }

    private fun getDetail(idEvent: String) {
        startActivity<DetailMatchActivity>("id" to "${idEvent}")
    }
    companion object {
        fun newInstance(): PreviusMatchFragment = PreviusMatchFragment()
    }
}