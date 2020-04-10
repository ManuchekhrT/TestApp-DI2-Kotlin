package tj.m.newtestapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tj.m.newtestapp.R
import tj.m.newtestapp.data.api.model.gallery.GalleriesTagRoot
import tj.m.newtestapp.data.api.model.images.Image
import tj.m.newtestapp.utils.GalleryAdapter


class GalleryFragment : Fragment(), GalleryAdapter.OnPostClickListener {

    companion object {
        const val TAG_NAME_PARAM = "param_tag_name"
        const val TAG_DISP_NAME_PARAM = "param_tag_disp"
    }

    private var currentPage = 0
    private var tagTitle: String = ""
    private var loading = false

    private lateinit var adapter: GalleryAdapter
    private lateinit var viewModel: GalleryViewModel
    private val postsList: ArrayList<Image> = ArrayList()
    private lateinit var  recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tagTitle = arguments?.getString(TAG_NAME_PARAM)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = GalleryAdapter(requireContext(), postsList, this)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        with(root) {
            recyclerView = findViewById(R.id.content_galley_posts_rv)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        loadPosts(currentPage)
        preparePosts()

    }

    private fun preparePosts() {
        val layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems =
                        layoutManager.findFirstVisibleItemPositions(null)
                    val lastVisibleItem =
                        pastVisibleItems[0].coerceAtLeast(pastVisibleItems[1])

                    if (!loading) {
                        if ((visibleItemCount + lastVisibleItem) >= totalItemCount) {
                            loadPosts(currentPage)
                        }
                    }

                }
            }
        })
    }


    private fun loadPosts(page: Int) {
        loading = true
        viewModel.getPostsFromTag(tagTitle, "top", "week", page).observe(viewLifecycleOwner, Observer {
            loading = false
            loadPosts(it)
        })
    }

    private fun loadPosts(it: GalleriesTagRoot) {
        val posts = it.data.items
        if (posts != null) {
            currentPage++
            postsList.addAll(posts)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onPostClick(image: Image) {
    }

}
