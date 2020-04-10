package tj.m.newtestapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import tj.m.newtestapp.R
import tj.m.newtestapp.data.api.model.tags.Tag
import tj.m.newtestapp.data.api.model.tags.TagsRoot
import tj.m.newtestapp.ui.gallery.GalleryFragment
import tj.m.newtestapp.utils.TagsAdapter

class MainFragment : Fragment(), TagsAdapter.OnTagClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var tagsAdapter: TagsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        with(rootView) {
            recyclerView = findViewById(R.id.content_main_rv)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getTags().observe(this, Observer {
              prepareTags(it)

        })

    }

    private fun prepareTags(it: TagsRoot) {
        val tags = it.data.tags
        tagsAdapter = TagsAdapter(requireContext(), tags, this)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = tagsAdapter
    }

    override fun onTagClick(tag: Tag) {
        val bundle = bundleOf()
        bundle.putString(GalleryFragment.TAG_NAME_PARAM, tag.name)
        bundle.putString(GalleryFragment.TAG_DISP_NAME_PARAM, tag.display_name)
        callGalleryFragment(bundle)
    }

    private fun callGalleryFragment(bundle: Bundle) {
        val fragment = GalleryFragment()
        fragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameContainer,  fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}
