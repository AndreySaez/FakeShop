package com.example.fakeshop.productlist.presentation.view.filters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fakeshop.appComponent
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.price.PriceSort
import com.example.fakeshop.productlist.presentation.viewModel.FiltersAction
import com.example.fakeshop.productlist.presentation.viewModel.FiltersOneTimeEvent
import com.example.fakeshop.productlist.presentation.viewModel.FiltersViewModel
import com.example.fakeshop.productlist.presentation.viewModel.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FiltersFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<FiltersViewModel> { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onAction(FiltersAction.SetInitialFilters(getCurrentCategory(), getCurrentSort()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (view as? ComposeView)?.setContent {
            Filters(filtersViewModel = viewModel)
        }

        viewModel.oneTimeEvents.onEach {
            when (it) {
                is FiltersOneTimeEvent.SubmitResults -> submitResultsAndFinish(it.category, it.sort)
            }
        }.launchIn(lifecycleScope)
    }

    private fun submitResultsAndFinish(category: Category?, sort: PriceSort?) {
        val requestCode = getRequestCode() ?: return
        setFragmentResult(
            requestCode,
            bundleOf(
                CATEGORY_KEY to category,
                SORT_KEY to sort
            )
        )
        dismiss()
    }

    private fun getCurrentCategory(): Category? = arguments?.getParcelable(CATEGORY_KEY)
    private fun getCurrentSort(): PriceSort? = arguments?.getParcelable(SORT_KEY)
    private fun getRequestCode(): String? = arguments?.getString(REQ_CODE_KEY)

    companion object {
        fun create(
            currentCategory: Category?,
            currentSort: PriceSort?,
            requestCode: String
        ) = FiltersFragment().apply {
            arguments = bundleOf(
                CATEGORY_KEY to currentCategory,
                SORT_KEY to currentSort,
                REQ_CODE_KEY to requestCode
            )
        }

        const val CATEGORY_KEY = "category"
        const val SORT_KEY = "sorting"
        private const val REQ_CODE_KEY = "RequestCode"
    }
}