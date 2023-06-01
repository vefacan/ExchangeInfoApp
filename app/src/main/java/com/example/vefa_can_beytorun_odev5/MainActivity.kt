package com.example.vefa_can_beytorun_odev5

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dovizler = resources.getStringArray(R.array.dovizler)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, dovizler)
        tiklananDovizKuru.setAdapter(arrayAdapter)

        Thread {
            val xml = XmlResult()
            val arr = xml.xmlCatalog(dovizler[0])
            runOnUiThread {
                if (arr.isNotEmpty()) {
                    birim.setText("Unit: " + arr[0].unit)
                    date.setText("Tarih: " + arr[0].date)
                    txtDovizAlisFiyati.setText(arr[0].forexBuying + " TL")
                    txtDovizSatisFiyati.setText(arr[0].forexSelling + " TL")
                    txtBankaAlisFiyati.setText(arr[0].banknoteBuying + " TL")
                    txtBankaSatisFiyati.setText(arr[0].banknoteSelling + " TL")
                }
            }
        }.start()

        tiklananDovizKuru.setOnItemClickListener { parent, view, position, id ->
            val secilenDoviz = parent.getItemAtPosition(position).toString()
            Thread {
                val xml = XmlResult()
                val arr = xml.xmlCatalog(secilenDoviz)
                runOnUiThread {
                    if (arr.isNotEmpty()) {

                        birim.setText("Birim: " + arr[0].unit)
                        date.setText("Güncel Tarih: " + arr[0].date)
                        txtDovizAlisFiyati.setText(arr[0].forexBuying + " TL")
                        txtDovizSatisFiyati.setText(arr[0].forexSelling + " TL")
                        txtBankaAlisFiyati.setText(arr[0].banknoteBuying + " TL")
                        txtBankaSatisFiyati.setText(arr[0].banknoteSelling + " TL")
                    }
                }
            }.start()
        }
    }

    override fun onResume() {
        super.onResume()
        val dovizler = resources.getStringArray(R.array.dovizler)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, dovizler)
        tiklananDovizKuru.setAdapter(arrayAdapter)
    }

}
