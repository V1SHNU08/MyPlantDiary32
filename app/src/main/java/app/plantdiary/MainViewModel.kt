package app.plantdiary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.plantdiary.dto.Plant
import app.plantdiary.service.IPlantService
import app.plantdiary.service.PlantService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MainViewModel(var plantService : IPlantService =  PlantService()) : ViewModel() {

    var plants : MutableLiveData<List<Plant>> = MutableLiveData<List<Plant>>()

    private lateinit var firestore : FirebaseFirestore

    fun fetchPlants() {
        viewModelScope.launch {
            var innerPlants = plantService.fetchPlants()
            plants.postValue(innerPlants)
        }

    }
}