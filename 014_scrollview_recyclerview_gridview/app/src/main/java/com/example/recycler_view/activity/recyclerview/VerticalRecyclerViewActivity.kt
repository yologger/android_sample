package com.example.recycler_view.activity.recyclerview

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
import com.example.recycler_view.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_vertical_recycler_view.*

class VerticalRecyclerViewActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_vertical_recycler_view)
        this.initUI()
    }

    private fun initUI() {
        val adapter =
            VerticalRecyclerViewAdapter(
                this,
                people
            )
        activity_vertical_recycler_view_rv.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_vertical_recycler_view_rv.addItemDecoration(
            DividerItemDecoration(
                this,
                R.drawable.line_divider,
                80,
                80
            )
        )
        activity_vertical_recycler_view_rv.layoutManager = layoutManager
        activity_vertical_recycler_view_rv.setHasFixedSize(true)
    }
}


class VerticalRecyclerViewAdapter
constructor(
    private val context: Context,
    private val people: ArrayList<Person>
) : RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewHolder>() {

    inner class VerticalRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person, context: Context) {
            val name = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_name)
            val nation = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_nation)
            name.text = person.name
            nation.text = person.nation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.vertical_recycler_view_item, parent, false)
        return VerticalRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: VerticalRecyclerViewHolder, position: Int) {
        holder.bind(people[position], context)
    }
}