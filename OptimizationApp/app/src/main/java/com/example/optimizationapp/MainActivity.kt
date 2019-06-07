package com.example.optimizationapp

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

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    FirstFragment()
                }
                else -> {
                    return SecondFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }


        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "First Tab"
                else -> {
                    return "Second Tab"
                }
            }
        }
    }
}

/**
 * First tab fragment
 */
class FirstFragment : Fragment() {

    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_first, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter()

        // get optimization response from SDK
        val one = One.getInstance(context?.applicationContext)
        one.registerInteractionCallback(
            "/FirstFragment"
        ) { _, response ->
            run {
                one.processResponse(response)
                parseData(response)
            }
        }

        parent.findViewById<RecyclerView>(R.id.recycler_view_one).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return parent
    }

    // parse out optimization url
    private fun parseData(response: BaseResponse) {
        if (response.optimizations.isNotEmpty()) {
            for (optimization in response.optimizations) {
                val data = optimization.data.toByteArray(Charsets.UTF_8)
                val bytes = Base64.decode(data, Base64.DEFAULT)
                val jsonObject = JSONObject(String(bytes, Charsets.UTF_8))
                if (jsonObject.has("actions")) {
                    val action = jsonObject.getJSONArray("actions").get(0) as JSONObject
                    if (action.getString("name").contains("banner")) {
                        val content = action.getJSONObject("asset").getString("content")
                        val contentJson = JSONObject(Html.fromHtml(content).toString())
                        viewAdapter.bannerUrl = contentJson.getString("image")
                        Log.e("url", viewAdapter.bannerUrl)
                        viewAdapter.notifyDataSetChanged()
                    } else if (action.getString("name").contains("card")) {
                        val content = action.getJSONObject("asset").getString("content")
                        val contentJson = JSONObject(Html.fromHtml(content).toString())
                        viewAdapter.cardUrl = contentJson.getString("image")
                        viewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        private val imageList = arrayListOf(
            R.drawable.product_1a,
            R.drawable.product_2a,
            R.drawable.product_3a
        )

        var bannerUrl = ""
        var cardUrl = ""

        class MyViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {

            val image = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.list_image_cell
                    , parent, false
                ) as ImageView
            return MyViewHolder(image)
        }

        // if optimized image is available, show it. Otherwise, show stock image
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            if (position == 0 && bannerUrl.isNotEmpty()) {
                Glide.with(holder.image.context).load(bannerUrl).into(holder.image)
            } else if (position == 1 && cardUrl.isNotEmpty()) {
                Glide.with(holder.image.context).load(cardUrl).into(holder.image)
            } else {
                holder.image.setImageDrawable(ContextCompat.getDrawable(holder.image.context, imageList[position]))
            }
            holder.image.setOnClickListener {
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
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_second, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter(resources.getStringArray(R.array.list_items))

        parent.findViewById<RecyclerView>(R.id.recycler_view_two).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return parent
    }

    class MyAdapter(private val dataset: Array<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {

            val textView = LayoutInflater.from(parent.context)
                .inflate(
                    android.R.layout.simple_list_item_1
                    , parent, false
                ) as TextView
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = dataset[position]
            holder.textView.textSize = 25f
            holder.textView.setOnClickListener {
                Toast.makeText(it.context, dataset[position], Toast.LENGTH_LONG).show()
            }
        }

        override fun getItemCount() = dataset.size
    }

}
