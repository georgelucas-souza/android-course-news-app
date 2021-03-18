package com.example.newsapp

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var news: News

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        news = savedInstanceState?.getParcelable("NEWS") ?: News(
            "BMW expects half of sales to be electric cars by 2030",
            "German carmaker forecast on Wednesday a big rise in pre-tax profit for this year",
            "https://www.irishtimes.com/image-creator/?id=1.4512833&origw=1440",
            "BMW expects at least half of its sales to be zero emission vehicles by 2030, setting a more conservative target than some rivals in the race to embrace cleaner driving.\\r\\nIn the short term, the German… [+2712 chars]",
            "The Irish Times",
            "The Irish Times",
            "2021-03-17T11:30:10Z",
            "https://www.irishtimes.com/business/manufacturing/bmw-expects-half-of-sales-to-be-electric-cars-by-2030-1.4512834"
        )

        if (savedInstanceState?.getParcelable<News>("NEWS") != null) {
            news = savedInstanceState.getParcelable<News>("NEWS") as News
            Log.i("save_instance", "RESTORED")
        } else {
            news = News(
                "BMW expects half of sales to be electric cars by 2030",
                "German carmaker forecast on Wednesday a big rise in pre-tax profit for this year",
                "https://www.irishtimes.com/image-creator/?id=1.4512833&origw=1440",
                "BMW expects at least half of its sales to be zero emission vehicles by 2030, setting a more conservative target than some rivals in the race to embrace cleaner driving.\\r\\nIn the short term, the German… [+2712 chars]",
                "The Irish Times",
                "The Irish Times",
                "2021-03-17T11:30:10Z",
                "https://www.irishtimes.com/business/manufacturing/bmw-expects-half-of-sales-to-be-electric-cars-by-2030-1.4512834"
            )
            Log.i("save_instance", "Created as NEW")
        }


        //modo antigo
        //val imgView = findViewById<ImageView>(R.id.imgView)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newsTitle.text = news.title
        binding.description.text = news.description
        binding.content.text = news.content
        binding.author.text = news.title
        binding.author.text = Html.fromHtml(
            getString(R.string.txt_author_source, news.author, news.source),
            FROM_HTML_MODE_LEGACY
        )



        Glide
            .with(this)
            .load(news.imageUrl)
            .centerCrop()
            .into(binding.imgView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable("NEWS", news)

    }
}