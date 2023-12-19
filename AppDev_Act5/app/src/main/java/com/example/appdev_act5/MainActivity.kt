package com.example.appdev_act5

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdev_act5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var adapter: Adapter? = null
    private var list: ArrayList<Info>? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)

        binding!!.recyclerView.setHasFixedSize(true)
        list = ArrayList()
        list!!.add(Info(R.drawable.yuji, "YUJI ITADORI"))
        list!!.add(Info(R.drawable.megumi, "MEGUMI FUSHIGURO"))
        list!!.add(Info(R.drawable.nobara, "NOBARA KUGISAKI"))
        list!!.add(Info(R.drawable.maki, "MAKI ZENIN"))
        list!!.add(Info(R.drawable.toge, "TOGE INUMAKI"))
        list!!.add(Info(R.drawable.panda, "PANDA PEAK"))
        list!!.add(Info(R.drawable.yuta, "YUTA OKKOTSU"))
        list!!.add(Info(R.drawable.kinji, "KINJI HAKARI"))
        list!!.add(Info(R.drawable.aoi, "AOI TODO"))
        list!!.add(Info(R.drawable.toji, "TOJI FUSHIGURO"))

        binding!!.recyclerView.setHasFixedSize(true)

        adapter = Adapter(this, list!!)
        binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}