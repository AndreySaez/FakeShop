package com.example.fakeshop.productlist.presentation.view.productslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakeshop.R
import com.example.fakeshop.appComponent
import com.example.fakeshop.productDetails.presentation.Navigator
import com.example.fakeshop.productDetails.presentation.ProductDetailsFragment
import com.example.fakeshop.productlist.domain.list.Category
import com.example.fakeshop.productlist.domain.list.PriceSort
import com.example.fakeshop.productlist.domain.list.Product
import com.example.fakeshop.productlist.presentation.view.filters.FiltersFragment
import com.example.fakeshop.productlist.presentation.viewModel.ProductAction
import com.example.fakeshop.productlist.presentation.viewModel.ProductListViewModel
import com.example.fakeshop.productlist.presentation.viewModel.ProductsListEvents
import com.example.fakeshop.productlist.presentation.viewModel.ProductsListState
import com.example.fakeshop.productlist.presentation.viewModel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProductsListFragment : Fragment() {
    private val productListViewModel by viewModels<ProductListViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(FILTER_REQ_CODE) { requestKey, bundle ->
            productListViewModel.onAction(
                ProductAction.ChangeFilters(
                    bundle.getParcelable(FiltersFragment.CATEGORY_KEY),
                    bundle.getParcelable(FiltersFragment.SORT_KEY) ?: PriceSort.DEFAULT
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val views = Views(view)

        views.errorButton.setOnClickListener {
            productListViewModel.onAction(ProductAction.Reload)
        }

        views.filtersButton.setOnClickListener {
            productListViewModel.onAction(ProductAction.OnFiltersClick)
        }

        val productsAdapter = ProductsListAdapter {
            Navigator.openProduct(it)
        }
        val gridLayoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (productsAdapter.getItemViewType(position)) {
                        LoadingViewHolder.VIEW_TYPE -> 2
                        else -> 1
                    }
                }
            }
        }
        views.recycler.apply {
            adapter = productsAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && !recyclerView.isComputingLayout) {
                        productListViewModel.onAction(ProductAction.OnScrollToEnd)
                    }
                }
            })
        }

        productListViewModel.state.onEach { state ->
            when (state) {
                is ProductsListState.ProductsList -> {
                    views.emptyListText.isVisible = false
                    views.errorGroup.isVisible = false
                    views.recycler.isVisible = true
                    views.progressBar.isVisible = false
                    productsAdapter.bindProductList(
                        state.products,
                        showLoading = state.hasMoreItems
                    )
                }

                is ProductsListState.IsEmpty -> {
                    views.emptyListText.isVisible = true
                    views.errorGroup.isVisible = false
                    views.recycler.isVisible = false
                    views.progressBar.isVisible = false
                }

                is ProductsListState.IsError -> {
                    views.emptyListText.isVisible = false
                    views.errorGroup.isVisible = true
                    views.progressBar.isVisible = false
                    views.recycler.isVisible = false
                }

                ProductsListState.IsLoading -> {
                    views.emptyListText.isVisible = false
                    views.errorGroup.isVisible = false
                    views.recycler.isVisible = false
                    views.progressBar.isVisible = true
                    gridLayoutManager.scrollToPosition(0)
                }
            }

        }
            .flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)

        productListViewModel.oneTimeEvents
            .onEach {
                when (it) {
                    is ProductsListEvents.OpenFilters -> openFilters(it.category, it.sort)
                }
            }.launchIn(lifecycleScope)

        Navigator.state
            .onEach {
                if (it.product != null) {
                    navigationOnProduct(it.product)
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun navigationOnProduct(product: Product) {
        val productDetails = ProductDetailsFragment.newInstance(product)
        parentFragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.main, productDetails)
            .commit()
    }

    private fun openFilters(category: Category?, sort: PriceSort) {
        FiltersFragment.create(category, sort, FILTER_REQ_CODE).show(parentFragmentManager, "tag")
    }

    class Views(view: View) {
        val emptyListText: TextView = view.findViewById(R.id.empty_list_text)
        val errorGroup: View = view.findViewById(R.id.error_group)
        val errorButton: Button = view.findViewById(R.id.error_button)
        val recycler: RecyclerView = view.findViewById(R.id.details_recycler_view)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val filtersButton: View = view.findViewById(R.id.filters_title)
    }

    companion object {
        const val FILTER_REQ_CODE = "FiltersReqCode"
    }

}