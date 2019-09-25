package mobile.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import mobile.github.models.User
import mobile.github.repository.UserListRepository

class UserListViewModel : ViewModel(){

    private val job = Job()
    private var currentKeyword = "NOTHING"
    private val scope = CoroutineScope(job + Dispatchers.IO)

    private val _userList = MutableLiveData<List<User>> ()
    val userList : LiveData<List<User>> = _userList

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState : LiveData<Boolean> = _loadingState

    init {
        _userList.postValue(ArrayList())
    }

    fun loadUsers(keyword :String = ""){
        scope.launch {
            if (currentKeyword != keyword) {
                _loadingState.postValue(true)

                val userList = UserListRepository.requestUserList(keyword)

                _userList.postValue(userList)

                _loadingState.postValue(false)
            }
        }
    }
}