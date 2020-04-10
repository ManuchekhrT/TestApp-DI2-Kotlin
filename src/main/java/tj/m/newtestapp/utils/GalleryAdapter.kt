package tj.m.newtestapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import tj.m.newtestapp.R
import tj.m.newtestapp.data.api.model.images.Image
import tj.m.newtestapp.networking.ApiClient


class GalleryAdapter(
    private val context: Context,
    val postsList: List<Image>,
    val listener: OnPostClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnPostClickListener {
        fun onPostClick(image: Image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            PostViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_progressbar, parent, false
            )
            ProgressViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            holder.position1 = position
            val post = postsList[position]

            holder.postTitle.text = post.title

            val imgUrl =
                if (post.isAlbum) ApiClient.getBackgroundImageUrl(post.cover) else post.link

            val myOptions = RequestOptions()
                .placeholder(R.drawable.ic_loading)
                .diskCacheStrategy(DiskCacheStrategy.DATA)

            Glide.with(context)
                .load(imgUrl)
                .apply(myOptions)
                .into(holder.postImage)

        } else if (holder is ProgressViewHolder) {
            val layoutParams =
                holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = true
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemCount(): Int = postsList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position < postsList.size) VIEW_ITEM
        else VIEW_PROGRESS
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView
        var postImage: ImageView
        var position1 = 0

        init {
            with(itemView) {
                postTitle = findViewById(R.id.post_col_title)
                postImage = findViewById(R.id.post_col_img)

                setOnClickListener {
                    listener.onPostClick(postsList[position1])
                }

            }
        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            with(itemView) {
                progressBar = findViewById(R.id.progressbar)
            }
        }
    }

    companion object {
        const val VIEW_ITEM = 1
        const val VIEW_PROGRESS = 0
    }

}