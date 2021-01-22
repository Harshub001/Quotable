package com.task.quotable.quotablebyrxjava.tagsbyrxjava

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.R
import com.task.quotable.data.datasource.TagsResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tags.*

class TagsFragmentRxJava: Fragment() {

    lateinit var tagsProgressBar: ProgressBar

    private val tagsClient by lazy {
        TagsApiServiceRxJava.create()
    }

    var disposable: Disposable? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_tags, container, false)
            tagsProgressBar = view.findViewById(R.id.tagsProgressBar)
            showTags()
            return view
    }

    private fun showTags() {
        disposable = tagsClient.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { resultTags -> setUpRecycler(resultTags) },
                        { error -> error.message?.let { Log.e("ERROR", it) } }
                )
    }

   private fun setUpRecycler(TagsList: List<TagsResult>) {
        tagsRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        tagsRecyclerView.layoutManager = layoutManager
        tagsRecyclerView.adapter = TagsAdapterRxJava(TagsList)
        tagsProgressBar.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}

