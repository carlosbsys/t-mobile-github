package mobile.github.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.base_view.*
import mobile.github.R
import mobile.github.adapter.GithubAdapter

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
abstract class BaseView : Fragment(){

    val adapter by lazy{
        GithubAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.base_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        base_view_recycler_view.adapter = adapter
        base_view_recycler_view.layoutManager = LinearLayoutManager(context)
        base_view_recycler_view.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = placeholderQuery()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                onQuery(newText)

                return true
            }
        })
    }

    fun clearAndAdd(items: List<Any>){
        adapter.dataModel.clear()

        adapter.dataModel.addAll(items)

        adapter.notifyDataSetChanged()
    }

    fun hideOrShowLoader(isLoading: Boolean){
        base_view_loading_view.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    abstract fun onQuery(query: String)

    abstract fun placeholderQuery() : String
}