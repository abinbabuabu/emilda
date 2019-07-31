package com.emilda.emildaapp.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emilda.emildaapp.BasicAuthentication
import com.emilda.emildaapp.Dataclass.PrintData
import com.emilda.emildaapp.Interfaces.APIService
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.PrintingViewModel
import kotlinx.android.synthetic.main.fragment_uploading_status.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


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

        doAsync {
            model.UploadPdf(requireContext())
        }
        model.UploadProgress.observe(requireActivity(), Observer<Int> {
            progress_upload.text = it.toString()
        })

        model.StorageUrl.observe(requireActivity(), Observer {
            if (it != null) {
                Log.d("print", it.toString())
                print(CreatePrintData(it.toString(),model.FileName!!))
            }
        })

    }


    private fun print(printData: PrintData) {
        val BASE_URL = "https://api.printnode.com"
        val USERNAME = "xAckyuoEzl72VkDUvceYylNL_n0A3GR26G6d96aU_n4"
        val HEADER_NAME = "Content-Type"
        val HEADER_VALUE = "application/json"


       /* val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY */


        val apiClient =
            OkHttpClient().newBuilder()
                .addInterceptor(BasicAuthentication(USERNAME, ""))
                //.addInterceptor(logging)
                .build()


        val builder = Retrofit.Builder()
            .client(apiClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())


        val retrofit = builder.build()

        val client = retrofit.create(APIService::class.java)
        val call = client.print(printData)

        call.enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("xy", t.message.toString())
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                Log.d("xy", response.code().toString())
            }

        })
    }

    private fun CreatePrintData(uri: String,fileName:String): PrintData {
        val CONTENT_TYPE = "pdf_uri"
        val printData = PrintData(69044946, fileName, CONTENT_TYPE, uri, "api documentation")
        return printData
    }



}
