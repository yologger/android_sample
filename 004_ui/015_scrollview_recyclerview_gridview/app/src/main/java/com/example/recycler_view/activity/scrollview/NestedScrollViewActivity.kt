package com.example.recycler_view.activity.scrollview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.Person
import com.example.recycler_view.R
import kotlinx.android.synthetic.main.activity_nested_scroll_view.*

class NestedScrollViewActivity : AppCompatActivity() {

    var people = arrayListOf<Person>(
        Person("Ronaldo", "Portugal"),
        Person("Kane", "England"),
        Person("Rooney", "England"),
        Person("Ramos", "Spain"),
        Person("Ronaldo", "Portugal"),
        Person("Kane", "England"),
        Person("Rooney", "England"),
        Person("Ramos", "Spain"),
        Person("Ronaldo", "Portugal"),
        Person("Kane", "England"),
        Person("Rooney", "England"),
        Person("Ramos", "Spain"),
        Person("Ronaldo", "Portugal"),
        Person("Kane", "England"),
        Person("Rooney", "England"),
        Person("Ramos", "Spain"),
        Person("Benzema", "France")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll_view)
        initUI()
    }

    private fun initUI() {
        val adapter = RecyclerViewAdapter(this, people)
        activity_nested_scroll_view_rv.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_nested_scroll_view_rv.layoutManager = layoutManager
    }

    class RecyclerViewAdapter
    constructor(
        private val context: Context,
        private val people: ArrayList<Person>
    ): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(person: Person, context: Context) {
                val name = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_name)
                val nation = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_nation)
                name.text = person.name
                nation.text = person.nation
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.vertical_recycler_view_item, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return people.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.bind(people[position], context)
        }
    }
}