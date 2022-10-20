package com.xolary.overwatchaimtrainigresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xolary.overwatchaimtrainigresults.databinding.FragmentItemListBinding

class ItemListFragment: Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResultsViewModel by activityViewModels {
        ResultsViewModelFactory(
            (activity?.application as ResultsApplication).database.itemDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListAdapter {
            val action =
                ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.Id)

            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.floatingActionButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }
    }
}