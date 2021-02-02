package com.task.quotable.bycouroutines.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.*
import com.task.quotable.bycouroutines.adapter.TagsListAdapter
import com.task.quotable.data.datasource.TagsResult
import com.task.quotable.bycouroutines.service.TagsApiService
import com.task.quotable.bycouroutines.service.TagsRetrofitBuilder
import kotlinx.android.synthetic.main.fragment_tags.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val tagsView = inflater.inflate(R.layout.fragment_tags, container, false)
        val tagsRequest = TagsRetrofitBuilder.buildService(TagsApiService::class.java)
        val tagsCall = tagsRequest.getTags()

        tagsCall.enqueue(object : Callback <ArrayList<TagsResult>>{
            override fun onResponse(call: Call<ArrayList<TagsResult>>, response: Response<ArrayList<TagsResult>>) {
                if (response.isSuccessful){
                    tagsProgressBar.visibility=View.GONE
                    tagsRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = TagsListAdapter(response?.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<TagsResult>>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
        return tagsView
    }
}
