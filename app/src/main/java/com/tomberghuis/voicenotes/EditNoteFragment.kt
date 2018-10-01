package com.tomberghuis.voicenotes

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class EditNoteFragment : Fragment() {

    private lateinit var editNoteView: EditText
    
    private lateinit var voiceNotesViewModel: VoiceNotesViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        voiceNotesViewModel = ViewModelProviders.of(activity!!).get(VoiceNotesViewModel::class.java!!)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.edit_note, container, false)
    }



    override fun onPause() {
        super.onPause()

        // check VM if note exists

        // insert


    }

}
