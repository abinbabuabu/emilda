package com.emilda.emilda.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.emilda.emilda.R
import com.emilda.emilda.Viewmodels.PrintingViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class UploadingStatus : Fragment() {
    lateinit var model: PrintingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uploading_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(PrintingViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


    }

    override fun onStart() {
        super.onStart()
        doAsync {
            model.UploadPdf(requireContext())
            uiThread {
                Log.d("xz",it.toString())
            }
        }


    }


}
