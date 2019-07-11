package com.emilda.emilda.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.emilda.emilda.R
import com.emilda.emilda.Viewmodels.PrintingViewModel
import kotlinx.android.synthetic.main.fragment_printing1.*
import kotlinx.android.synthetic.main.plus_minus_button.*

class Printing1 : Fragment() {

    private val REQUEST_CODE_DOC: Int = 343
    lateinit var model: PrintingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_printing1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(PrintingViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        browse_pdf.setOnClickListener {
            getDocument()
        }

        nullCheckBeforeNext()
        setUpDropDowns()
        counter()

    }

    override fun onStart() {
        super.onStart()

        if (!model.FileName.isNullOrEmpty()) {
            browse_pdf.text = model.FileName
            browse_pdf.setBackgroundColor(ContextCompat.getColor(activity!!.applicationContext, R.color.yellow))
        }
    }


    private fun getDocument() {
        //TODO("Check For Storage Permission" )

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        activity?.startActivityForResult(Intent.createChooser(intent, "Pick "), REQUEST_CODE_DOC)
    }

    private fun nullCheckBeforeNext() {
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
            if (model.FileName.isNullOrEmpty()) {
                flag = false
                Toast.makeText(context, "Select a File", Toast.LENGTH_LONG).show()
            }

            if (flag) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.printing2)
            }
        }
    }


    private fun setUpDropDowns() {
        var colorValue: String
        val ColorModes = arrayOf("Black & White", "Color")
        val ColorAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, ColorModes)

//Color Mode
        color_mode.setAdapter(ColorAdapter)
        color_mode.setOnItemClickListener { adapterView, view, position, l ->
            colorValue = adapterView.getItemAtPosition(position).toString()
            color_mode.hint = colorValue
            color_mode.error = null
        }

//Paper Size
        var paperSize: String
        val paperSizes = arrayOf("A4", "A3")
        val PaperSizeAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, paperSizes)
        paper_size.setAdapter(PaperSizeAdapter)
        paper_size.setOnItemClickListener { adapterView, view, pos, l ->
            paperSize = adapterView.getItemAtPosition(pos).toString()
            paper_size.hint = paperSize
            paper_size.error = null
        }
//Paper Type
        var paperType: String
        val paperTypes = arrayOf("Normal", "Thick")
        val PaperTypeAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, paperTypes)
        paper_type.setAdapter(PaperTypeAdapter)
        paper_type.setOnItemClickListener { adapterView, view, pos, l ->
            paperType = adapterView.getItemAtPosition(pos).toString()
            paper_type.hint = paperType
            paper_type.error = null
        }
// Duplex Printing
        var duplexVal: String
        val duplexes = arrayOf("One Side", "Two Side")
        val duplexAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, duplexes)
        duplex.setAdapter(duplexAdapter)
        duplex.setOnItemClickListener { adapterView, view, pos, l ->
            duplexVal = adapterView.getItemAtPosition(pos).toString()
            duplex.hint = duplexVal
            duplex.error = null

        }

        var binding: String
        val Bindings = arrayOf("One Side", "Two Side")
        val bindingAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, Bindings)
        document_binding.setAdapter(bindingAdapter)
        document_binding.setOnItemClickListener { adapterView, view, pos, l ->
            binding = adapterView.getItemAtPosition(pos).toString()
            document_binding.hint = binding
            document_binding.error = null

        }

    }

    fun counter() {
        plus_btn.setOnClickListener {
            count_plusMinus.clearFocus()
            val count = count_plusMinus.text.toString()
            var countInt = count.toInt()
            countInt++
            count_plusMinus.setText(countInt.toString())
        }
        minus_btn.setOnClickListener {
            count_plusMinus.clearFocus()
            val count = count_plusMinus.text.toString()
            var countInt = count.toInt()
            if (countInt > 1)
                countInt--
            count_plusMinus.setText(countInt.toString())

        }
    }


}
