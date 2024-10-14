package com.adityat.crypzee_cryptotrackingapp.Viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityat.crypzee_cryptotrackingapp.Data.CoinData
import com.adityat.crypzee_cryptotrackingapp.Screen
import com.adityat.crypzee_cryptotrackingapp.coinsService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MainViewModel : ViewModel() {

    // To track login status
    private val _isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn


    // For Bottom Navigation
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreens.Home)
    val currentScreen: MutableState<Screen> get() = _currentScreen
    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    // For Top Screen Navigation
    private val _currenttopScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreens.topScreens.MarketCap)
    val currenttopScreen: MutableState<Screen> get() = _currenttopScreen
    fun setCurrenttopScreen(screen: Screen) {
        _currenttopScreen.value = screen
    }

    // API and Data Handling for coin
    val coinList: MutableState<List<CoinData>> = mutableStateOf(emptyList())
    val isLoading = mutableStateOf(true)
    val errorMessage = mutableStateOf<String?>(null)

    init {
        fetchCoins()
        fetchGainerCoins()
        fetchLosersCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch {
            isLoading.value = true
            delay(2000)
            try {
                val coins = coinsService.getMarketData()
                coinList.value = coins
                errorMessage.value = null // Clear error if successful
            } catch (e: Exception) {
                errorMessage.value = "Error loading data: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    //Data handling for get top Gainers
    val gainercoinlist: MutableState<List<CoinData>> = mutableStateOf(emptyList())
    val isLoadinggainer = mutableStateOf(true)
    val errorMessagegainer = mutableStateOf<String?>(null)

    private fun fetchGainerCoins() {
        viewModelScope.launch {
            isLoading.value = true
            delay(2000)
            try {
                val gainercoin = coinsService.getTopGainers()
                    .filter { (it.price_change_percentage_24h ?: 0.0) > 0 }  // Only positive percentage gainers
                    .sortedByDescending { it.price_change_percentage_24h }
                gainercoinlist.value = gainercoin
                errorMessagegainer.value = null // Clear error if successful
            } catch (e: Exception) {
                errorMessagegainer.value = "Error loading data: ${e.message}"
            } finally {
                isLoadinggainer.value = false
            }
        }
    }

    //Data handling for get top losers
    val loserscoinlist: MutableState<List<CoinData>> = mutableStateOf(emptyList())
    val isLoadingloser= mutableStateOf(true)
    val errorMessageloser = mutableStateOf<String?>(null)

    private fun fetchLosersCoins() {
        viewModelScope.launch {
            isLoading.value = true
            delay(2000)
            try {
                val losercoin = coinsService.getTopGainers()
                    .filter { (it.price_change_percentage_24h ?: 0.0) < 0 }  // Only positive percentage gainers
                    .sortedBy { it.price_change_percentage_24h }
                loserscoinlist.value = losercoin
                errorMessageloser.value = null // Clear error if successful
            } catch (e: Exception) {
                errorMessageloser.value = "Error loading data: ${e.message}"
            } finally {
                isLoadingloser.value = false
            }
        }
    }




    //Data handling of Coin chart
    private val _coinPriceData: MutableState<List<Pair<Long, Double>>> = mutableStateOf(emptyList())
    val coinPriceData: State<List<Pair<Long, Double>>> get() = _coinPriceData

    fun fetchCoinMarketChart(coinId: String) {
        viewModelScope.launch {
            try {
                val chartData = coinsService.getCoinMarketChart(coinId)
                // Map the price data to a list of pairs (timestamp, price)
                _coinPriceData.value = chartData.prices.map { Pair(it[0].toLong(), it[1]) }
            } catch (e: HttpException) {
                // Handle error
            }
        }
    }
}
