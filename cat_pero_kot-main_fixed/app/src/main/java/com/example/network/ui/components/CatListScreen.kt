package com.example.network.ui.components

import CatCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.network.ui.viewmodel.CatViewModel

@Composable
fun CatListScreen(
    viewModel: CatViewModel = hiltViewModel()
) {
    val catList by viewModel.cats.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == catList.lastIndex) {
                    viewModel.fetchCats()
                }
            }
    }

    LazyColumn(state = listState) {
        items(catList) { cat ->
            CatCard(imageUrl = cat.url, contentDescription = "Cat image")
        }
    }
}