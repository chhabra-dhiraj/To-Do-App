package io.github.chhabra_dhiraj.to_do.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.chhabra_dhiraj.to_do.R
import io.github.chhabra_dhiraj.to_do.databinding.ActivityMainBinding
import io.github.chhabra_dhiraj.to_do.ui.adapters.ToDoListAdapter
import io.github.chhabra_dhiraj.to_do.ui.adapters.ToDoListener
import io.github.chhabra_dhiraj.to_do.ui.fragments.NewToDoDialogFragment
import io.github.chhabra_dhiraj.to_do.viewmodels.ToDoViewModel


class MainActivity : AppCompatActivity() {

    private val toDoViewModel: ToDoViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.lifecycleOwner = this

        binding.viewModel = toDoViewModel

        binding.rvToDo.adapter = ToDoListAdapter(
            ToDoListener(
                // For Complete Button
                { toDoId ->
                    toDoViewModel.updateCompleted(true, toDoId)
                },
                // For Delete Button
                { toDo ->
                    toDoViewModel.deleteToDo(toDo)
                },
                // For Undo Complete Button
                { toDoId ->
                    toDoViewModel.updateCompleted(false, toDoId)
                })
        )

        setSupportActionBar(binding.toolbarToDo)

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvToDo.context,
            LinearLayoutManager.VERTICAL
        )
        binding.rvToDo.addItemDecoration(dividerItemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_new_to_do_menu_item -> {
            handleAdd()
            true
        }
        R.id.app_bar_search_menu_item -> {
            handleSearch()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun handleAdd() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment = NewToDoDialogFragment() //here MyDialog is my custom dialog
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private fun handleSearch() {

    }

}