package com.ram.test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ram.test.R
import com.ram.test.model.RecyclerViewData
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items = mutableListOf<RecyclerViewData>()


    fun setListData(data: ArrayList<RecyclerViewData>) {
        this.items = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        var inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var txTitle = view.tvTitle
        var txHeadLine = view.tvHeadline
        var txDescription = view.tvDescription
        var txSeeMore = view.tvSeeMore
        var image = view.imageThumb

        fun bind(listData: RecyclerViewData) {
            txTitle.text = listData.name
            txHeadLine.text = listData.headline
            txDescription.text = listData.description


            val url = listData.thumb

            Glide.with(image)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(image)

            //To show/hide large content
            txSeeMore.setOnClickListener {
                if (txSeeMore.text.toString().equals("More")) {
                    txDescription.maxLines = Int.MAX_VALUE
                    txSeeMore.text = "Less.."
                } else {
                    txDescription.maxLines = 3
                    txSeeMore.text = "More"
                }
            }


        }
    }


}