package com.example.vefa_can_beytorun_odev5

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlResult {

    fun xmlCatalog(currencyName: String) : MutableList<Currency>{


        val arr = mutableListOf<Currency>()
        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc:Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        var date = doc.getElementsByTag("Tarih_Date").attr("Tarih")
        val element = doc.select("Currency:contains($currencyName)").firstOrNull()

        if (element != null){
            val name = element.getElementsByTag("Isim").text()
            val forexBuying = element.getElementsByTag("ForexBuying").text()
            val forexSelling = element.getElementsByTag("ForexSelling").text()
            val banknoteBuying = element.getElementsByTag("BanknoteBuying").text()
            val banknoteSelling = element.getElementsByTag("BanknoteSelling").text()
            val unit = element.getElementsByTag("Unit").text()

            val currency = Currency(name, forexBuying, forexSelling, banknoteBuying, banknoteSelling,date,unit)
            arr.add(currency)
        }

        return arr
    }



}