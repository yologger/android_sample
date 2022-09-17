package com.example.recycler_view.activity.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.Person
import com.example.recycler_view.R
import com.example.recycler_view.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_on_click_item.*

class OnClickItemActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_on_click_item)
        this.initUI()
    }

    private fun initUI() {
        val adapter =
            RecyclerViewAdapter(
                this,
                people
            )
        adapter.setOnItemClickListener(object:
            RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("YOLO", "ON CLICK AT: ${position}")
            }
        })

        activity_on_click_item_rv.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_on_click_item_rv.addItemDecoration(
            DividerItemDecoration(
                this,
                R.drawable.line_divider,
                80,
                80
            )
        )
        activity_on_click_item_rv.layoutManager = layoutManager
        activity_on_click_item_rv.setHasFixedSize(true)
    }
}

class RecyclerViewAdapter
constructor(
    private val context: Context,
    private val people: ArrayList<Person>
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: Person, context: Context) {
            val name = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_name)
            val nation = itemView.findViewById<TextView>(R.id.vertical_recycler_view_item_tv_nation)
            name.text = person.name
            nation.text = person.nation

            itemView.setOnClickListener { view ->
                var position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(view, position)
                }
            }
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