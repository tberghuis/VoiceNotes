package com.tomberghuis.voicenotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // should i use context!! ?????
        val adapter = NotesListAdapter(context!!)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context!!)


        val model = ViewModelProviders.of(activity!!).get(VoiceNotesViewModel::class.java)
        model.allNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })

//        val bundle = Bundle()
//        bundle.putBoolean("newNote", true)
//        view.findViewById<FloatingActionButton>(R.id.new_note_action_bt)?.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.new_note_action, bundle)
//        )

        // argument newNote is true by default
//        view.findViewById<FloatingActionButton>(R.id.new_note_action_bt)?.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.new_note_action, null)
//        )


        // need to rewrite so model.editNoteId is set to null before navigating
        view.findViewById<FloatingActionButton>(R.id.new_note_action_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.new_note_action, null)
        )
    }

}
