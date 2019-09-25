package mobile.github.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import mobile.github.R
import mobile.github.adapter.GithubAdapter.OnGithubAdapterListener
import mobile.github.models.User
import mobile.github.viewmodel.UserListViewModel

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
class UserListView : BaseView() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(UserListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userList.observe(this, Observer { userList ->
            clearAndAdd(userList)
        })

        viewModel.loadingState.observe(this, Observer { isLoading ->
            hideOrShowLoader(isLoading)
        })

        adapter.listener = object : OnGithubAdapterListener{
            override fun onSelectedItem(source: Any) {
                if(source is User){
                    val bundle = bundleOf("nickname" to source.login)

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadUsers()
    }

    override fun onQuery(query: String) {
        viewModel.loadUsers(query)
    }

    override fun placeholderQuery(): String {
        return "Search for users"
    }

}