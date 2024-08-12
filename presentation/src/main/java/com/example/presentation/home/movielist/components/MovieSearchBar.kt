package com.example.presentation.home.movielist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.dimensions
import com.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchBar(
    query: String,
    onQueryChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    active: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(MaterialTheme.dimensions.paddingMedium).fillMaxWidth()
    ) {
        SearchBar(
            leadingIcon = { Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_icon_content_description),
                modifier = Modifier.size(MaterialTheme.dimensions.iconDefaultSize)
            ) },
            placeholder = { Box(contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.search_bar_place_holder)
                )
            }
            },
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = active,
            onActiveChange = onActiveChange
        ){}
    }
}