package com.task.quotable.bycouroutines.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.task.quotable.R
import com.task.quotable.bycouroutines.ui.activity.MainActivity


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view?.findViewById<CardView>(R.id.quotes_card_view)?.setOnClickListener(View.OnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_quotesFragment)
        })

        view?.findViewById<CardView>(R.id.authors_card_view)?.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_authorFragment)
        })

        view?.findViewById<CardView>(R.id.tags_card_view)?.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_tagsFragment)
        })

        return view
    }

}