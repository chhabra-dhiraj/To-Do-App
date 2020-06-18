package io.github.chhabra_dhiraj.to_do.utils

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.chhabra_dhiraj.to_do.R
import io.github.chhabra_dhiraj.to_do.domain.ToDo
import io.github.chhabra_dhiraj.to_do.ui.adapters.ToDoListAdapter

@BindingAdapter("toDoListAvailable")
fun TextView.isToDoListAvailable(toDos: List<ToDo>?) {
    toDos?.let {
        visibility = if (toDos.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("toDoList")
fun RecyclerView.setToDoList(toDos: List<ToDo>?) {
    toDos?.let {
        visibility = if (toDos.isNotEmpty()) {
            (adapter as ToDoListAdapter).submitList(it)
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("strikeThrough")
fun TextView.setStrikeThroughVisibility(toDo: ToDo?) {
    toDo?.let {
        paintFlags = if (toDo.completed) {
            (paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
        } else {
            paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

@BindingAdapter("toDoVisibility")
fun ImageView.setToDoVisibility(toDo: ToDo?) {
    toDo?.let {
        visibility = if (toDo.completed) {
            if (id == R.id.imv_complete_to_do) {
                View.GONE
            } else {
                View.VISIBLE
            }
        } else {
            if (id == R.id.imv_complete_to_do) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}