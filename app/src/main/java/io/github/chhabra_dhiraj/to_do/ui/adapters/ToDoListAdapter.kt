package io.github.chhabra_dhiraj.to_do.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.chhabra_dhiraj.to_do.databinding.ToDoRecyclerViewItemBinding
import io.github.chhabra_dhiraj.to_do.domain.ToDo

class ToDoListAdapter(private val toDoListener: ToDoListener) :
    ListAdapter<ToDo, ToDoListAdapter.ViewHolder>(
        ToDosDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, toDoListener)
    }

    class ViewHolder(private val binding: ToDoRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ToDo,
            toDoListener: ToDoListener
        ) {
            binding.toDo = item
            binding.toDoListener = toDoListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ToDoRecyclerViewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ToDosDiffCallback : DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(
        oldItem: ToDo,
        newItem: ToDo
    ): Boolean {
        return oldItem.toDoId == newItem.toDoId
    }

    override fun areContentsTheSame(
        oldItem: ToDo,
        newItem: ToDo
    ): Boolean {
        return oldItem == newItem
    }
}

class ToDoListener(
    val completeListener: (toDoId: Int) -> Unit,
    val deleteListener: (toDo: ToDo) -> Unit,
    val undoCompleteListener: (toDoId: Int) -> Unit
) {
    fun onCompleteClick(toDo: ToDo) = completeListener(toDo.toDoId)
    fun onDeleteClick(toDo: ToDo) = deleteListener(toDo)
    fun onUndoCompleteClick(toDo: ToDo) = undoCompleteListener(toDo.toDoId)
}