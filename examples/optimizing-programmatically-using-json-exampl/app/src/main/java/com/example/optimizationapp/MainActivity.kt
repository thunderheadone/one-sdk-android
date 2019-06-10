package com.example.optimizationapp

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thunderhead.One
import com.thunderhead.android.infrastructure.server.responses.BaseResponse
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create the tabs pager adapter
        val fragmentAdapter = ActivityTabsPager(supportFragmentManager)

        // get the view pager view from the layout and set the pager adapter
        viewPager.adapter = fragmentAdapter

        // get the tab layout view from the layout and set the view pager view
        tabLayout.setupWithViewPager(viewPager)
    }

    class ActivityTabsPager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment =
            if (position == 0) FirstFragment() else SecondFragment()

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence =
            if (position == 0) "First Tab" else "Second Tab"
    }
}

/**
 * First tab fragment
 */
class FirstFragment : Fragment() {

    private val viewAdapter by lazy { FirstFragmentRecyclerViewAdapter() }
    private val viewManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_first, container, false)

        // get optimization response from SDK
        One.getInstance(context?.applicationContext)?.let { thunderhead ->
            thunderhead.registerInteractionCallback(
                "/FirstFragment-recycler_view_one"
            ) { _, response ->
                run {
                    thunderhead.processResponse(response)
                    parseData(response)
                }
            }
        }

        return parent.findViewById<RecyclerView>(R.id.recycler_view_one).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    // parse out optimization url
    private fun parseData(response: BaseResponse) {
        val actions = response.optimizations
            .map { op -> op.data.toByteArray(Charsets.UTF_8) }
            .map { data -> Base64.decode(data, Base64.DEFAULT) }
            .map { bytes -> JSONObject(String(bytes, Charsets.UTF_8)) }
            .filter { json -> json.has("actions") }
            .map { json -> json.getJSONArray("actions").get(0) as JSONObject }

        actions.filter { it.getString("name").contains("banner")  }
            .map { it.getJSONObject("asset").getJSONArray("responses").get(0) as JSONObject }
            .forEach{ response -> setSentiment(response, true) }

        actions.filter { it.getString("name").contains("card")  }
            .map { it.getJSONObject("asset").getJSONArray("responses").get(0) as JSONObject }
            .forEach{ response -> setSentiment(response, false) }

        actions.filter { it.getString("name").contains("banner") }
            .map { it.getJSONObject("asset").getString("content") }
            .map { content -> JSONObject(Html.fromHtml(content).toString()) }
            .forEach { contentJson -> performUpdate(contentJson, true) }

        actions.filter { it.getString("name").contains("card") }
            .map { it.getJSONObject("asset").getString("content") }
            .map { content -> JSONObject(Html.fromHtml(content).toString()) }
            .forEach { contentJson -> performUpdate(contentJson, false) }
    }

    private fun setSentiment(json: JSONObject, isBanner: Boolean) {
        val sentiment = json.getString("sentiment")
        if (isBanner) viewAdapter.bannerSentiment = sentiment else viewAdapter.cardSentiment = sentiment;
    }

    private fun performUpdate(json: JSONObject, isBanner: Boolean) {
        val url = json.getString("image")
        if (isBanner) viewAdapter.bannerUrl = url else viewAdapter.cardUrl = url
        viewAdapter.notifyDataSetChanged()
    }

    class FirstFragmentRecyclerViewAdapter :
        RecyclerView.Adapter<FirstFragmentRecyclerViewAdapter.FirstFragmentViewHolder>() {

        private val imageList = arrayListOf(
            R.drawable.product_1a,
            R.drawable.product_2a,
            R.drawable.product_3a
        )

        var bannerUrl = ""
        var cardUrl = ""
        var bannerSentiment = ""
        var cardSentiment = ""

        class FirstFragmentViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FirstFragmentViewHolder {

            val image = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.list_image_cell
                    , parent, false
                ) as ImageView
            return FirstFragmentViewHolder(image)
        }

        // if optimized image is available, show it. Otherwise, show stock image
        override fun onBindViewHolder(holder: FirstFragmentViewHolder, position: Int) {
            if (position == 0 && bannerUrl.isNotEmpty()) {
                Glide.with(holder.image.context).load(bannerUrl).into(holder.image)
            } else if (position == 1 && cardUrl.isNotEmpty()) {
                Glide.with(holder.image.context).load(cardUrl).into(holder.image)
            } else {
                holder.image.setImageDrawable(ContextCompat.getDrawable(holder.image.context, imageList[position]))
            }

            // setup click listener to send response code
            if (position == 0) {
                holder.image.setOnClickListener {
                    sendResponseCode(it.context, bannerSentiment)
                }
            } else if (position == 1) {
                holder.image.setOnClickListener {
                    sendResponseCode(it.context, cardSentiment)
                }
            }
        }

        private fun sendResponseCode(context: Context?, code: String) {
            One.getInstance(context?.applicationContext)?.let { thunderhead ->
                thunderhead.sendResponseCode(
                    code, "/FirstFragment-recycler_view_one"
                )
            }
        }

        override fun getItemCount() = imageList.size
    }
}

/**
 * Second fragment
 */
class SecondFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private val viewManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_second, container, false)

        viewAdapter = SecondFragmentRecyclerViewAdapter(resources.getStringArray(R.array.list_items))

        return parent.findViewById<RecyclerView>(R.id.recycler_view_two).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class SecondFragmentRecyclerViewAdapter(private val dataset: Array<String>) :
        RecyclerView.Adapter<SecondFragmentRecyclerViewAdapter.SecondFragmentViewHolder>() {

        class SecondFragmentViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SecondFragmentViewHolder {

            val textView = LayoutInflater.from(parent.context)
                .inflate(
                    android.R.layout.simple_list_item_1
                    , parent, false
                ) as TextView
            return SecondFragmentViewHolder(textView)
        }

        override fun onBindViewHolder(holder: SecondFragmentViewHolder, position: Int) {
            holder.textView.text = dataset[position]
            holder.textView.textSize = 25f
            holder.textView.setOnClickListener {
                Toast.makeText(it.context, dataset[position], Toast.LENGTH_LONG).show()
            }
        }

        override fun getItemCount() = dataset.size
    }

}
