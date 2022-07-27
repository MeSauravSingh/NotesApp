package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.Adapter
import com.example.notesapp.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity(),Adapter.OnItemClickListener {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = Adapter(this)
        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity2)
        }
        val list = mutableListOf<Data>()
        list.add(
            Data(1,
                "SAURAV NOTES",
                "This is my first testing description."

            )
        )

        list.add(
            Data(1,
                "Gaurav Notes",
                "It is my second testing notes"

            )
        )
        list.add(
            Data(1,
                "Suraj Notes",
                "Hey! It is my third testing notes"

            )
        )
        myAdapter.submitList(list)

    }

    override fun onItemClickListener(myData: Data, position: Int) {
        Toast.makeText( applicationContext,"$position", Toast.LENGTH_SHORT).show()
    }
}