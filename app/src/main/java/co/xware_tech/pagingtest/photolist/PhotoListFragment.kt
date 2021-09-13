package co.xware_tech.pagingtest.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import co.xware_tech.pagingtest.R
import co.xware_tech.pagingtest.databinding.FragmentHomeBinding
import co.xware_tech.pagingtest.databinding.FragmentPhotoListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotoListFragment:Fragment() {
    private lateinit var binding:FragmentPhotoListBinding
    private val viewModel by viewModels<PhotoListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPhotoListBinding.inflate(inflater,container,false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var i=0
        viewModel.testValue.observe(viewLifecycleOwner,{
            i=it
        })
        val adapter=PhotoListRecyclerAdapter()
        binding.rvPhoto.adapter=adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photoListLive?.collectLatest {
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener { loadState ->

            binding.btnReload.isVisible = loadState.source.refresh is LoadState.Error


            binding.rvPhoto.isVisible = loadState.source.refresh is LoadState.NotLoading
            if (loadState.source.refresh is LoadState.Loading) {
                binding.lavLoadState.setAnimation(R.raw.cube_loader)
                binding.lavLoadState.visibility = View.VISIBLE
                binding.lavLoadState.playAnimation()
            } else {
                binding.lavLoadState.cancelAnimation()
                binding.lavLoadState.visibility = View.INVISIBLE
            }
        }
        binding.btnReload.setOnClickListener {
            adapter.refresh()
        }
    }

}