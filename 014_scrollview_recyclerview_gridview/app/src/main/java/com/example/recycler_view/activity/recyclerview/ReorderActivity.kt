package com.example.recycler_view.activity.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.Person
import com.example.recycler_view.R
import com.example.recycler_view.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_reorder.*

class ReorderActivity : AppCompatActivity() {

    private val itemTouchHelper by lazy {
        val itemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val adapter = recyclerView.adapter as ReorderableRecyclerViewAdapter
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    adapter.moveItem(from, to)
                    adapter.notifyItemMoved(from, to)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

                override fun onSelectedChanged(
                    viewHolder: RecyclerView.ViewHolder?,
                    actionState: Int
                ) {
                    super.onSelectedChanged(viewHolder, actionState)
                    if (actionState == ACTION_STATE_DRAG) {
                        viewHolder?.itemView?.alpha = 0.5f
                    }
                }

                override fun clearView(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) {
                    super.clearView(recyclerView, viewHolder)
                    viewHolder?.itemView?.alpha = 1.0f
                }
            }

        ItemTouchHelper(itemTouchCallback)
    }

    var people = listOf<Person>(
        Person("1", "Ronaldo"),
        Person("2", "Messi"),
        Person("3", "Kane"),
        Person("4", "Spain"),
        Person("5", "Portugal"),
        Person("6", "England"),
        Person("7", "England"),
        Person("8", "Spain"),
        Person("9", "Portugal"),
        Person("10", "England")
    ).toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reorder)
        this.initUI()
    }

    private fun initUI() {
        val adapter =
            ReorderableRecyclerViewAdapter(
                this,
                people
            )
        activity_reorder_rv.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_reorder_rv.addItemDecoration(
            DividerItemDecoration(this, R.drawable.line_divider, 10, 10)
        )
        activity_reorder_rv.layoutManager = layoutManager
        activity_reorder_rv.setHasFixedSize(true)
        itemTouchHelper.attachToRecyclerView(activity_reorder_rv)
    }
}

class ReorderableRecyclerViewAdapter
constructor(
    private val context: Context,
    private val people: MutableList<Person>
) : RecyclerView.Adapter<ReorderableRecyclerViewAdapter.ReorderableRecyclerViewHolder>() {

    inner class ReorderableRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person, context: Context) {
            val name = itemView.findViewById<TextView>(R.id.reorderable_recycler_view_item_tv_name)
            val nation =
                itemView.findViewById<TextView>(R.id.reorderable_recycler_view_item_tv_nation)
            name.text = person.name
            nation.text = person.nation
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReorderableRecyclerViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.reorderable_recycler_view_item, parent, false)
        val viewHolder = ReorderableRecyclerViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ReorderableRecyclerViewHolder, position: Int) {
        holder.bind(people[position], context)
    }

    fun moveItem(from: Int, to: Int) {
        val fromItem = people[from]
        people.removeAt(from)
        if (to < from) {
            people.add(to, fromItem)
        } else {
            people.add(to - 1, fromItem)
        }
    }
}