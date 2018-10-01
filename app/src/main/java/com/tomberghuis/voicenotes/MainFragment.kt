package com.tomberghuis.voicenotes

//import com.tomberghuis.voicenotes.R.id.recyclerview
//import androidx.recyclerview.widget.RecyclerView
//import com.tomberghuis.voicenotes.R.id.recyclerview
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

//                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        // should i use context!! ?????
        val adapter = NotesListAdapter(context!!)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context!!)


        val model = ViewModelProviders.of(activity!!).get(VoiceNotesViewModel::class.java)
        model.allNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })

        val bundle = Bundle()
//        bundle.putString("amount", amount)
        bundle.putBoolean("newNote", true)

        view.findViewById<FloatingActionButton>(R.id.new_note_action_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.new_note_action, bundle)
        )
    }
}
