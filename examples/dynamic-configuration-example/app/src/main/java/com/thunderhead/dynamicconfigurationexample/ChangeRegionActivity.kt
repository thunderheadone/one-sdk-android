package com.thunderhead.dynamicconfigurationexample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_change_region.*

class ChangeRegionActivity : AppCompatActivity() {
    private val regionsList = ArrayList(OneSdkConfigurations.keys)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_region)

        title = getString(R.string.change_region_activity_title)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.oneBackgroundColor
                )
            )
        )

        regions_list_recycler_view.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager

            val listAdapter = ListAdapter(regionsList)
            listAdapter.onItemClick = {
                val intent = Intent()
                intent.putExtra("regionValue", it)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            val selected = getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            ).getString(getString(R.string.region_key), null)
            listAdapter.selectedRegion = selected
            adapter = listAdapter

            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    inner class ListAdapter(private val regionList: List<String>) :
        RecyclerView.Adapter<ListAdapter.RegionViewHolder>() {
        var onItemClick: ((String) -> Unit)? = null
        var selectedRegion: String? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return RegionViewHolder(inflater, parent)
        }

        override fun getItemCount() = regionList.size

        override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
            val region = regionList[position]
            holder.bind(holder, region, selectedRegion)
        }

        inner class RegionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.region_list_item, parent, false)) {

            private val regionTextView = itemView.findViewById<TextView>(R.id.textView)
            private val checkMarkImageView =
                itemView.findViewById<ImageView>(R.id.checkMarkImageView)

            fun bind(holder: RegionViewHolder, region: String, selectedRegion: String?) {
                holder.itemView.setOnClickListener {
                    onItemClick?.invoke(regionsList[adapterPosition])
                }
                regionTextView?.text = region
                checkMarkImageView.visibility =
                    if (region == selectedRegion) View.VISIBLE else View.INVISIBLE
            }
        }
    }
}
