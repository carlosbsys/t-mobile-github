package mobile.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import mobile.github.models.Repo
import mobile.github.models.UserDetail
import mobile.github.repository.ProfileRepository


class ProfileViewModel : ViewModel(){
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    private val _userDetail = MutableLiveData<UserDetail> ()
    val userDetail : LiveData<UserDetail> = _userDetail

    private val _repos = MutableLiveData<List<Repo>> ()
    val repos : LiveData<List<Repo>> = _repos

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState : LiveData<Boolean> = _loadingState

    private lateinit var _dataSource : List<Repo>
    private var _currentKeyword = ""
    private var _currentNickname = ""

    fun getProfileInfo(user: String?){
        if(user != null) {
            scope.launch {
                _loadingState.postValue(true)

                val userDetail = ProfileRepository.getProfile(user)
                _userDetail.postValue(userDetail)

                val repos = ProfileRepository.getRepos(user)
                _repos.postValue(repos)

                _dataSource = repos

                _currentNickname = user

                _loadingState.postValue(false)
            }
        }
    }

    fun filterRepos(newText: String) {
        if (_currentKeyword != newText){
            _currentKeyword = newText
            if (_currentKeyword.isBlank() || _currentKeyword.isEmpty()){
                _repos.postValue(_dataSource)
            }
            scope.launch {
                _loadingState.postValue(true)

                val filterRepos = ProfileRepository.filterRepos(_currentNickname, _currentKeyword)

                _repos.postValue(filterRepos)

                _loadingState.postValue(false)
            }
        }
    }
}