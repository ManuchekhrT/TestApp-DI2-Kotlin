package tj.m.newtestapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tj.m.newtestapp.R
import tj.m.newtestapp.data.api.model.tags.Tag
import tj.m.newtestapp.networking.ApiClient


class TagsAdapter(
    private val context: Context,
    val tagList: List<Tag>,
    val listener: OnTagClickListener
) : RecyclerView.Adapter<TagsAdapter.TagHolder>() {

    interface OnTagClickListener {
        fun onTagClick(tag: Tag)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)


        return TagHolder(itemView)
    }

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.mPosition = position
        val tag = tagList[position]
        holder.tagNameTv.text = tag.display_name
        Glide.with(context)
            .load(ApiClient.getBackgroundImageUrl(tag.background_hash))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.tagBackgroundIv)

    }

    inner class TagHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tagNameTv: TextView
        var tagBackgroundIv: ImageView
        var mPosition = 0

        init {
            with(itemView) {
                tagNameTv = findViewById(R.id.tag_col_name)
                tagBackgroundIv = findViewById(R.id.tag_col_background)
                setOnClickListener {
                    listener.onTagClick(tagList[mPosition])
                }
            }
        }

    }
}