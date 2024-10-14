package com.adityat.crypzee_cryptotrackingapp.MainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.adityat.crypzee_cryptotrackingapp.Viewmodel.MainViewModel

@Composable
fun CoinDescription(coinId: String?, viewModel: MainViewModel) {
    // Fetch coin details when the coinId changes
    LaunchedEffect(coinId) {
        coinId?.let {
            viewModel.fetchCoinDetails(it)
        }
    }

    // Access the coin details and loading state
    val coinDetails by viewModel.coinDetails // Use by keyword to create a state reference
    val isLoadingDetails by viewModel.isLoadingCoinDetails

    if (isLoadingDetails) {
        Text(text = "Loading...")
    } else {
        coinDetails?.let { coin ->
            Column {
                Text(text = "Name: ${coin.name}") // Access other properties similarly
                Text(text = "Icon: ${coin.image}")
                Text(text = "Price: ${coin.current_price}")
                Text(text = "Symbol: ${coin.symbol}")
                Text(text = "Rank: ${coin.market_cap_rank}")
                Text(text = "Market Cap: ${coin.market_cap}")
            }
        } ?: run {
            Text(text = "Error loading coin details")
        }
    }
}
