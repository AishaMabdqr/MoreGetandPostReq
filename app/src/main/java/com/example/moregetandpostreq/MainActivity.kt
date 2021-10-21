package com.example.moregetandpostreq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var eNames: EditText
    lateinit var bAdd: Button
    lateinit var bGet: Button
    lateinit var rvItems: RecyclerView
    lateinit var rvAdapter: RVAdapter
    lateinit var items : ArrayList<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eNames = findViewById(R.id.eNames)
        bAdd = findViewById(R.id.bAdd)
        bGet = findViewById(R.id.bGet)
        rvItems = findViewById(R.id.rvItems)

        items = ArrayList()
        rvAdapter = RVAdapter(items)
        rvItems.adapter = rvAdapter
        rvItems.layoutManager = LinearLayoutManager(this)


        bAdd.setOnClickListener {
            addNames()
            eNames.clearFocus()
            eNames.text.clear()
        }

        bGet.setOnClickListener {
            getNames()
        }

    }

    fun addNames() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.addNames(Names(eNames.text.toString(), "a"))
            ?.enqueue(object : Callback<Names> {
                override fun onResponse(call: Call<Names>, response: Response<Names>) {
                    Toast.makeText(this@MainActivity, "User Added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Names>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG)
                        .show()
                }

            })
    }

    fun getNames(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getNames()?.enqueue(object : Callback<List<GetNames.Users>>{
            override fun onResponse( call: Call<List<GetNames.Users>>, response: Response<List<GetNames.Users>>) {

                var name : String? = ""
                for (i in response.body()!!){
                    name = i.name
                    items.add(name!!)
                    rvAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<List<GetNames.Users>>, t: Throwable) {
                Log.d("Main", "Unable to get data!")
            }

        })
    }





}