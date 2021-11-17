package com.example.firstlesson

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.firstlesson.databinding.FragmentAddNewsDialogBinding

class AddNewsDialog : DialogFragment() {

    var listener: Listener? = null

    interface Listener {

        fun okClicked(title: String, desc: String, pos: String)
    }

    private lateinit var binding: FragmentAddNewsDialogBinding

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog = AlertDialog.Builder(requireContext())
        .setView(FragmentAddNewsDialogBinding.inflate(layoutInflater).let {
            binding = it
            it.root
        })
        .setPositiveButton("OK") { _, _ ->
            val title = binding.etTitle.text?.toString()
            val desc = binding.etDesc.text?.toString()
            val pos = binding.etPosition.text?.toString() ?: ""
            if (title != null) {
                if (desc != null) {
                    Log.e("TAGG", "$title $desc $pos")
                    listener?.okClicked(title, desc, pos)
                }
            }

        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()

        }.create()

    companion object {

        fun show(
            fragmentManager: FragmentManager,
            listener: Listener

        ) {
            AddNewsDialog().apply {
                this.listener = listener
                show(fragmentManager, AddNewsDialog::class.java.name)
            }
        }
    }
}
