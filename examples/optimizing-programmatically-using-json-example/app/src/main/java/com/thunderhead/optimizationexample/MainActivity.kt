package com.thunderhead.optimizationexample

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
import com.thunderhead.android.api.*
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.interactions.OneResponseCode
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneResponse
import com.thunderhead.android.api.responsetypes.OneSDKError
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URI

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
        oneConfigureMessaging {
            enabled = false
        }

        // Requires calling `removeAutomaticInteractionCallback` when destroyed, typically used in
        // in the Activity `onDestroy` or Fragment `onDestroyView` lifecycle method.
        setAutomaticInteractionCallback {
            onSuccess { response ->
                response?.let {
                    Log.d("optimization-example", "Received automatic callback for Interaction: ${it.interactionPath}")
                    it.process()
                }
            }

            onError {
                Log.e("optimization-example", it.errorMessage)
            }

            onFailure {
                Log.e("optimization-example", it.errorMessage)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeAutomaticInteractionCallback()
    }

    class ActivityTabsPager(fm: FragmentManager) : FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
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
    private val scope = CoroutineScope(Dispatchers.IO)
    private val viewAdapter by lazy { FirstFragmentRecyclerViewAdapter() }
    private val viewManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parent = inflater.inflate(R.layout.fragment_first, container, false)

        // Requires calling `removeAutomaticInteractionCallback` when destroyed, typically used in
        // in the Activity `onDestroy` or Fragment `onDestroyView` lifecycle method.
        setAutomaticInteractionCallback {
            onSuccess { response ->
                response?.let {
                    Log.d("optimization-example", "Received automatic callback for Interaction: ${it.interactionPath}")
                    it.process()
                    parseData(it)
                }
            }

            onError {
                Log.e("optimization-example", it.errorMessage)
            }

            onFailure {
                Log.e("optimization-example", it.errorMessage)
            }
        }

        return parent.findViewById<RecyclerView>(R.id.recycler_view_one).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeAutomaticInteractionCallback()
    }

    // parse out optimization array
    private fun parseData(response: OneResponse) {
        val actions = response.optimizations?.let {
            it.asSequence().map { op -> op.data.toByteArray(Charsets.UTF_8) }
                .map { data -> Base64.decode(data, Base64.DEFAULT) }
                .map { bytes -> JSONObject(String(bytes, Charsets.UTF_8)) }
                .filter { json -> json.has("actions") }
                .map { json -> json.getJSONArray("actions").get(0) as JSONObject }.toList()
        }

        actions?.run {
            this.filter { it.getString("name").contains("banner") }
                .map { it.getJSONObject("asset").getJSONArray("responses").get(0) as JSONObject }
                .forEach { response -> setResponseCode(response, true) }
        }

        actions?.run {
            this.filter { it.getString("name").contains("card") }
                .map { it.getJSONObject("asset").getJSONArray("responses").get(0) as JSONObject }
                .forEach { response -> setResponseCode(response, false) }
        }
    }

    private fun setResponseCode(json: JSONObject, isBanner: Boolean) {
        val responseCode = json.getString("code")
        if (isBanner) {
            viewAdapter.bannerResponseCode = responseCode
        } else {
            viewAdapter.cardResponseCode = responseCode
        }
    }

    private fun updateContent(json: JSONObject, isBanner: Boolean) {
        val url = json.getString("image")
        if (isBanner) viewAdapter.bannerUrl = url else viewAdapter.cardUrl = url

        activity?.runOnUiThread {
            viewAdapter.notifyDataSetChanged()
        }
    }

    class FirstFragmentRecyclerViewAdapter :
        RecyclerView.Adapter<FirstFragmentRecyclerViewAdapter.FirstFragmentViewHolder>() {

        private val imageList = arrayListOf(
            R.drawable.product_1a,
            R.drawable.product_2a,
            R.drawable.product_3a
        )
        private val scope = CoroutineScope(Dispatchers.IO)
        var bannerUrl = ""
        var cardUrl = ""
        var bannerResponseCode = ""
        var cardResponseCode = ""

        class FirstFragmentViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FirstFragmentViewHolder {

            val image = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.list_image_cell, parent, false
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
                holder.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.image.context,
                        imageList[position]
                    )
                )
            }

            // setup click listener to send response code
            if (position == 0) {
                holder.image.setOnClickListener {
                    scope.launch {
                        sendResponseCode(bannerResponseCode)
                    }
                }
            } else if (position == 1) {
                holder.image.setOnClickListener {
                    scope.launch {
                        sendResponseCode(cardResponseCode)
                    }
                }
            }
        }

        private suspend fun sendResponseCode(code: String) {
            try {
                oneSendResponseCode(throwErrors = true) {
                    interactionPath = OneInteractionPath(URI.create("/FirstFragment-recycler_view_one"))
                    responseCode = OneResponseCode(code)
                }
            } catch (error: OneSDKError) {
                Log.e("optimization-example", "SDK Error: ${error.errorMessage}")
            } catch (error: OneAPIError) {
                Log.e("optimization-example", "Api Error: ${error.errorMessage}")
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parent = inflater.inflate(R.layout.fragment_second, container, false)

        viewAdapter =
            SecondFragmentRecyclerViewAdapter(resources.getStringArray(R.array.list_items))

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
                    android.R.layout.simple_list_item_1, parent, false
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
