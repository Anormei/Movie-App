package com.mkodo.training.stevenw.movieapp.screens.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.databinding.DialogErrorBinding

class ErrorDialog() : DialogFragment() {

    private var description: String? = null
    private var _binding: DialogErrorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        description = arguments?.getString("description")

        _binding = DialogErrorBinding.inflate(inflater, container, false)
        binding.dialogErrorTextDescription.text = description

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogErrorButton.setOnClickListener { close() }
    }

    private fun close() {
        dismissAllowingStateLoss()
    }

    companion object {
        const val TAG = "errorDialog"

        fun newInstance(description: String) = ErrorDialog().apply{
            arguments = Bundle().apply{
                putString("description", description)
            }
        }
    }

}