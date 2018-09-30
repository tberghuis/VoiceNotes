package com.tomberghuis.voicenotes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(false)





        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        // should i use context!! ?????
        val adapter = NotesListAdapter(context!!)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context!!)


        val model = ViewModelProviders.of(activity!!).get(VoiceNotesViewModel::class.java!!)
        model.allNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })




        view.findViewById<FloatingActionButton>(R.id.new_note_action_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.new_note_action, null)
        )
    }
}
