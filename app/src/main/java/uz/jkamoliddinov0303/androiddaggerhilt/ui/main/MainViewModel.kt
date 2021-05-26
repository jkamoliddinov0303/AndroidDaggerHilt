package uz.jkamoliddinov0303.androiddaggerhilt.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.jkamoliddinov0303.androiddaggerhilt.db.User
import uz.jkamoliddinov0303.androiddaggerhilt.repository.UserRepository

class MainViewModel @ViewModelInject constructor(private val repository: UserRepository) :
    ViewModel() {
    val getAllUsers: LiveData<List<User>> = repository.getAllUserData

    fun insert(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.deleteUSer(user)
    }
}