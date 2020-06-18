package io.github.chhabra_dhiraj.to_do.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import io.github.chhabra_dhiraj.to_do.R
import io.github.chhabra_dhiraj.to_do.databinding.FragmentNewToDoDialogBinding
import io.github.chhabra_dhiraj.to_do.viewmodels.ToDoViewModel


class NewToDoDialogFragment : DialogFragment() {

    private val toDoViewModel: ToDoViewModel by viewModels()

    private var _binding: FragmentNewToDoDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewToDoDialogBinding.inflate(inflater, container, false)
        binding.viewModel = toDoViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonAddNewToDo.setOnClickListener {
            val text = binding.tilNewToDo.editText?.text.toString()
            if (!TextUtils.isEmpty(text)) {
                toDoViewModel.insertToDo(text)
                dismiss()
            } else {
                binding.tilNewToDo.error =
                    requireActivity().resources.getString(R.string.add_new_to_do_error_message)
                binding.tilNewToDo.isErrorEnabled = true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}