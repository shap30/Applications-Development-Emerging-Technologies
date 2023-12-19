package com.example.appdev_act6

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() , AdapterView.OnItemClickListener{

    private var gridView: GridView? = null
    private var arrayList: ArrayList<item>? = null
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.my_grid_view)
        arrayList = ArrayList()
        arrayList = setDataList()
        adapter = Adapter(applicationContext, arrayList!!)
        gridView?.adapter = adapter as com.example.appdev_act6.Adapter
        gridView?.onItemClickListener = this
    }

    private fun setDataList(): ArrayList<item> {

        var arrayList: ArrayList<item> = ArrayList()

        arrayList.add(item(R.drawable.yoichi, "YOICHI ISAGI #11"))
        arrayList.add(item(R.drawable.bachira, "MEGURU BACHIRAN #8"))
        arrayList.add(item(R.drawable.rin, "RIN ITOSHI #10"))
        arrayList.add(item(R.drawable.baro, "SHOEI BARO #13"))
        arrayList.add(item(R.drawable.nagi, "SEISHIRO NAGI #7"))
        arrayList.add(item(R.drawable.gin, "GIN GAGAMARU #1"))

        arrayList.add(item(R.drawable.reo, "REO MIKAGE #14"))
        arrayList.add(item(R.drawable.karasu, "TABITO KARASU #6"))
        arrayList.add(item(R.drawable.kenyu, "KENYU YUKIMIYA #5"))
        arrayList.add(item(R.drawable.aryu, "JYUBEI ARYU #2"))
        arrayList.add(item(R.drawable.hiori, "YO HIORI 16"))
        arrayList.add(item(R.drawable.chigiri, "HYOMA CHIGIRI #4"))
        arrayList.add(item(R.drawable.niko, "IKKI NIKO #3"))
        arrayList.add(item(R.drawable.otoya, "EITA OTOYA #9"))

        return arrayList

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var item: item = arrayList!!.get(position)
        Toast.makeText(applicationContext, item.name,  Toast.LENGTH_LONG).show()
    }
}







