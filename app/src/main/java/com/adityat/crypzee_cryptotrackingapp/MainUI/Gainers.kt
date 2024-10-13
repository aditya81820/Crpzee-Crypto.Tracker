package com.adityat.crypzee_cryptotrackingapp.MainUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adityat.crypzee_cryptotrackingapp.Viewmodel.MainViewModel

@Composable
fun Gainer(controller: NavController, padding: PaddingValues, viewModel: MainViewModel) {
    val coinList by viewModel.gainercoinlist
    val isLoading by viewModel.isLoadinggainer

    Column(modifier = Modifier.padding(padding)) {
        // Show loading indicator if data is loading
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color =  Color.White
                )

            }
        } else {
            // Show coin list when not loading
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp,end=20.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("CRYPTO COIN", fontSize = 10.sp)
                Text("LAST PRICE", fontSize = 10.sp)
                Text("24H CHANGE", fontSize = 10.sp)
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(coinList, key = { it.id }) { coin ->
                    CoinItem(coin)
                    Row(modifier = Modifier.padding(start =12.dp, end = 12.dp)) {
                        Divider()
                    }
                }
            }
        }
    }
}


