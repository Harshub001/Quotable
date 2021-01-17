package com.task.quotable.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.*
import com.task.quotable.adapter.TagListAdapter
import com.task.quotable.data.datasource.TagsResult
import com.task.quotable.service.TagsApiService
import com.task.quotable.service.TagsRetrofitBuilder
import kotlinx.android.synthetic.main.fragment_tags.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tags, container, false)
        val request = TagsRetrofitBuilder.buildService(TagsApiService::class.java)
        val call = request.getTags()

        call.enqueue(object : Callback <ArrayList<TagsResult>>{
            override fun onResponse(call: Call<ArrayList<TagsResult>>, response: Response<ArrayList<TagsResult>>) {
                if (response.isSuccessful){
                    progressLayout.visibility=View.GONE
                    progressBar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = TagListAdapter(response?.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<TagsResult>>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        return view
    }

}
