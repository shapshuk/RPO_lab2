package com.example.lab2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.databinding.RssItemBinding
import com.example.lab2.retrofit.RssItem
import org.jsoup.Jsoup

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    private var rssItems: MutableList<RssItem> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RssItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rss_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.binding.title.text = rssItems[pos].title
        holder.binding.description.text =  Jsoup.parse(rssItems[pos].description).text()
        holder.binding.timeFromPublication.text = rssItems[pos].pubDate

        holder.binding.readButton.setOnClickListener {
            (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WebViewFragment(rssItems[pos].link!!))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() : Int = rssItems.size

    fun updateRssItems(newRssItems: List<RssItem>) {
        rssItems.clear()
        rssItems.addAll(newRssItems)
        notifyDataSetChanged()
    }
}