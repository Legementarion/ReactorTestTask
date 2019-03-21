package com.lego.reactortesttask.flow.giphy

import android.os.Bundle
import android.view.View
import com.lego.reactortesttask.R
import com.lego.reactortesttask.base.BaseFragment
import com.lego.reactortesttask.utils.onQueryChanged
import com.lego.reactortesttask.databinding.*
import com.lego.reactortesttask.utils.Paginator
import kotlinx.android.synthetic.main.fragment_giphy.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GiphyFragment : BaseFragment<FragmentGiphyBinding>() {

    companion object {
        const val TAG = "GiphyFragment"
    }

    override val viewModel: GiphyViewModel by viewModel()
    override fun getLayout(): Int = R.layout.fragment_giphy
    private var paginator: Paginator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.executePendingBindings()
        searchView.queryHint = getString(R.string.query_hint)
        searchView.onQueryChanged {
            viewModel.search(it, paginator)
        }
        searchView.onActionViewExpanded()
        viewModel.errorDelegate = {
            toast(it)
        }

        paginator = Paginator(recyclerView) { viewModel.uploadMore(paginator) }
    }
}
