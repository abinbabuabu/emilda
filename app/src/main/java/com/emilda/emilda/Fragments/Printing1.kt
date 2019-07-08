package com.emilda.emilda.Fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.emilda.emilda.R
import kotlinx.android.synthetic.main.fragment_printing1.*
import java.io.File
import java.net.URI
import java.net.URISyntaxException

class Printing1 : Fragment() {

    private val REQUEST_CODE_DOC: Int = 343


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_printing1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        browse_pdf.setOnClickListener {
            getDocument()
        }

        next_print1.setOnClickListener {
            var flag = true
            if (color_mode.text.isEmpty()) {
                flag = false
                color_mode.error = "Required Field"
            }
            if (paper_size.text.isEmpty()) {
                flag = false
                paper_size.error = "Required Field"
            }
            if (paper_type.text.isEmpty()) {
                flag = false
                paper_type.error = "Required Field"
            }
            if (duplex.text.isEmpty()) {
                flag = false
                duplex.error = "Required Field"
            }
            if (document_binding.text.isEmpty()) {
                flag = false
                document_binding.error = "Required Field"
            }

            if (flag)
                findNavController().navigate(R.id.printing2)
        }
    }


    private fun getDocument() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
        activity?.startActivityForResult(Intent.createChooser(intent, "Pick "), REQUEST_CODE_DOC)
    }


}
