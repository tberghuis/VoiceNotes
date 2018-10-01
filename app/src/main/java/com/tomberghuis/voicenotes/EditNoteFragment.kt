package com.tomberghuis.voicenotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tomberghuis.voicenotes.EditNoteFragmentArgs.fromBundle

class EditNoteFragment : Fragment() {

    private lateinit var editNoteView: EditText

    private lateinit var voiceNotesViewModel: VoiceNotesViewModel

    private val newNote by lazy {
        fromBundle(arguments).newNote
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        voiceNotesViewModel = ViewModelProviders.of(activity!!).get(VoiceNotesViewModel::class.java)


//        val newNoteString = arguments?.getString("newNote")

        val newNoteBoolean = arguments?.getBoolean("newNote")

//        Log.d("AAA","newNoteString $newNoteString")
        Log.d("AAA","newNoteBoolean $newNoteBoolean")
        Log.d("AAA","newNote $newNote")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.edit_note, container, false)
    }



    override fun onPause() {
        super.onPause()

        // TODO

        Log.d("AAA","onpause $newNote")

        // insert if newNote
        // did not blend as far as i know, start with a room sample
        if(newNote){
            Log.d("AAA","onpause newnote")
            voiceNotesViewModel.insert(Note(null,"will it blend",System.currentTimeMillis()))
            Log.d("AAA","onpause newnote")
        }

        Log.d("AAA","onpause2 $newNote")
        voiceNotesViewModel.insert(Note(null,"does this work",System.currentTimeMillis()))

    }

}
