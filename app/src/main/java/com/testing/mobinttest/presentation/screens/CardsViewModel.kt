package com.testing.mobinttest.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.testing.mobinttest.data.remote.response.Company

class CardsViewModel(pager: Pager<Int, Company>) : ViewModel() {
    val companyPagingFlow = pager.flow.cachedIn(viewModelScope)
}