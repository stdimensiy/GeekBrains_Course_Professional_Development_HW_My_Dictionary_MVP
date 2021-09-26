package ru.vdv.myapp.mydictionary.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.vdv.myapp.mydictionary.databinding.SearchDialogFragmentBinding
import ru.vdv.myapp.mydictionary.view.common.OnSearchClickListener

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: SearchDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //в текущий момент времени реализация данного метода не предусмотрена
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (count == 0) {
                binding.searchButtonTextview.visibility = View.GONE
                binding.clearTextImageview.visibility = View.GONE
            } else {
                binding.searchButtonTextview.visibility = View.VISIBLE
                binding.clearTextImageview.visibility = View.VISIBLE
            }
        }

        override fun afterTextChanged(s: Editable?) {
            //в текущий момент времени реализация данного метода не предусмотрена
        }
    }

    companion object {
        const val TAG = "SearchDialogFragment"
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)
        binding.clearTextImageview.visibility = View.GONE
        binding.searchButtonTextview.visibility = View.GONE
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButtonTextview.visibility = View.GONE
        }
    }
}