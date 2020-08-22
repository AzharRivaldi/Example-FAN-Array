package com.azhar.examplefan

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity() {

    var mainAdapter: MainAdapter? = null
    var progressDialog: ProgressDialog? = null
    var modelMain: MutableList<ModelMain> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Mohon Tunggu")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Sedang menampilkan data")

        rvMain.setLayoutManager(LinearLayoutManager(this))
        rvMain.setHasFixedSize(true)

        data
    }

    private val data: Unit
        private get() {
            progressDialog!!.show()
            AndroidNetworking.get("https://raw.githubusercontent.com/Oclemy/SampleJSON/338d9585/spacecrafts.json")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONArray(object : JSONArrayRequestListener {
                        override fun onResponse(response: JSONArray) {
                            for (i in 0 until response.length()) {
                                try {
                                    progressDialog!!.dismiss()
                                    val data = ModelMain()
                                    val jsonObject = response.getJSONObject(i)
                                    data.name = jsonObject.getString("name")
                                    data.propellant = jsonObject.getString("propellant")
                                    data.destination = jsonObject.getString("destination")
                                    modelMain.add(data)
                                    mainAdapter = MainAdapter(modelMain)
                                    rvMain!!.adapter = mainAdapter
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                    Toast.makeText(this@MainActivity, "gagal menampilkan data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onError(error: ANError) {
                            progressDialog!!.dismiss()
                            Toast.makeText(this@MainActivity, "tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                        }
                    })
        }
}