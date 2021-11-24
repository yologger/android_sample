package com.example.recycler_view.activity.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.Person
import com.example.recycler_view.R
import com.example.recycler_view.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_item_with_button.*
import kotlinx.android.synthetic.main.with_button_item.view.*

class ItemWithButtonActivity : AppCompatActivity() {

    var people = listOf<Person>(
        Person("0", "Ramos"),
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

    private val itemTouchHelper by lazy {
        val itemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, 0) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val adapter = recyclerView.adapter as ItemWithButtonViewAdapter
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
                    if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
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

                override fun isLongPressDragEnabled(): Boolean {
                    return false
                }
            }

        ItemTouchHelper(itemTouchCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_with_button)
        this.initUI()
    }

    private fun initUI() {
        val adapter = ItemWithButtonViewAdapter(this, people)
        activity_item_with_button_rv.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_item_with_button_rv.addItemDecoration(
            DividerItemDecoration(this, R.drawable.line_divider, 10, 10)
        )
        activity_item_with_button_rv.layoutManager = layoutManager
        activity_item_with_button_rv.setHasFixedSize(true)
        itemTouchHelper.attachToRecyclerView(activity_item_with_button_rv)

        adapter.listener = object:
            ItemWithButtonViewAdapter.ClickListener {
            override fun onRemoveButtonClicked(position: Int) {
                adapter.removeItem(position)
            }

            override fun onLongPressed(position: Int, viewHolder: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        }
    }

    fun startDragging(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}



class ItemWithButtonViewAdapter
constructor(
    private val activity: ItemWithButtonActivity,
    private val people: MutableList<Person>
) : RecyclerView.Adapter<ItemWithButtonViewAdapter.ItemWithButtonViewHolder>() {

    fun removeItem(at: Int) {
        people.removeAt(at)
        notifyDataSetChanged()
    }
    interface ClickListener {
        fun onRemoveButtonClicked(position: Int)
        fun onLongPressed(position: Int, viewHolder: RecyclerView.ViewHolder)
    }

    var listener: ClickListener? = null

    inner class ItemWithButtonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            val name = itemView.findViewById<TextView>(R.id.with_button_item_tv_name)
            val nation = itemView.findViewById<TextView>(R.id.with_button_item_tv_nation)
            name.text = person.name
            nation.text = person.nation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWithButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.with_button_item, parent, false)
        val viewHolder = ItemWithButtonViewHolder(view)
//        viewHolder.itemView.with_button_item_btn_reorder.setOnTouchListener { view, event ->
//            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
//                activity.startDragging(viewHolder)
//            }
//            return@setOnTouchListener false
//        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ItemWithButtonViewHolder, position: Int) {
        if(listener != null) {
            holder.itemView.with_button_item_btn_clear.setOnTouchListener { view, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    listener?.onRemoveButtonClicked(position)
                }
                return@setOnTouchListener false
            }

            holder.itemView.with_button_item_btn_reorder.setOnLongClickListener {
                listener?.onLongPressed(position, holder)
                return@setOnLongClickListener false
            }
        }
        holder.bind(people[position])
    }

    fun moveItem(from: Int, to: Int) {
        val fromItem = people[from]
        people.removeAt(from)
        if (to < from) {
            people.add(to, fromItem)
        } else {
            people.add(to - 1, fromItem)
        }
        for (person in people) {
            Log.d("TEST", "title: ${person.name} ")
        }
        Log.d("TEST", "######################################")
    }
}

