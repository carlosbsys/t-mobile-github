package mobile.github.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import mobile.github.models.Repo
import mobile.github.viewmodel.ProfileViewModel

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-25.
 */
class ProfileView : BaseView(){

    private var _nickname: String? = ""
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _nickname = arguments?.get("nickname") as String?

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userDetail.observe(this, Observer { userDetail ->
            clearAndAdd(arrayListOf(userDetail))
        })

        viewModel.repos.observe(this, Observer {repos->
            handleReload(repos)
        })

        viewModel.loadingState.observe(this, Observer { isLoading ->
            hideOrShowLoader(isLoading)
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getProfileInfo(_nickname)
    }

    private fun handleReload(repos: List<Repo>){
        if(adapter.dataModel.size > 1){
            val size = adapter.dataModel.size

            for (i in 1 until size){
                adapter.dataModel.removeAt(1)
            }

            adapter.notifyItemRangeRemoved(1, size)

            handleReload(repos)
        }else{
            val startSize = adapter.dataModel.size

            adapter.dataModel.addAll(repos)

            val finalSize = adapter.dataModel.size

            adapter.notifyItemRangeInserted(startSize, finalSize)
        }
    }

    override fun placeholderQuery(): String {
        return "Search for repos"
    }

    override fun onQuery(query: String) {
        viewModel.filterRepos(query)
    }

}