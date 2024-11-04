package uk.ac.tees.mad.w9624019.cinimaapp.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uk.ac.tees.mad.w9624019.cinimaapp.api.ApiCall
import uk.ac.tees.mad.w9624019.cinimaapp.data.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PlanetsViewModel() : ViewModel() {

    private var _planetsDataFlow = MutableStateFlow<MutableList<Planet>>(mutableStateListOf())

    val planetsDataFlow: StateFlow<List<Planet>>
        get() = _planetsDataFlow

    val smallestDiameter: Int
        get() = if(_planetsDataFlow.value.isEmpty()) 0 else _planetsDataFlow.value.minOf { it.diameter }

    val largestDiameter: Int
        get() = if(_planetsDataFlow.value.isEmpty()) 0 else _planetsDataFlow.value.maxOf { it.diameter }

    init {
        fetchPlanets()
    }

    fun fetchPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiCall().getPlanets(
                onSuccess = { starWarsData ->
                    _planetsDataFlow.value.addAll(starWarsData.results.sortedBy { it.diameter })
                }
            )
        }
    }
}