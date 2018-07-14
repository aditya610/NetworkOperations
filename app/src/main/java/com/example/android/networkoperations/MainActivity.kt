package com.example.android.networkoperations

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnsearch.setOnClickListener({
            Download().execute(tvurl.text.toString())
        })
    }


    inner class Download:AsyncTask<String,Unit,String>(){
        override fun doInBackground(vararg params: String?): String {
          if (params.size<1)
              return "no url found"
try {


            val url=URL(params[0])
            val connection=url.openConnection() as HttpURLConnection
            val br=BufferedReader(InputStreamReader(connection.inputStream))
            val sb=StringBuilder()
            var buf=br.readLine()
            while (buf!=null){
                sb.append(buf)
                buf=br.readLine()
            }
            return sb.toString()}
catch (e:IOException){
    return "error reading data"
}
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        tvinfo.text=result
        }
    }
}
