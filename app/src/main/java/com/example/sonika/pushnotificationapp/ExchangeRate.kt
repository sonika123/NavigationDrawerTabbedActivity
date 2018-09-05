package com.example.sonika.pushnotificationapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName









class ExchangeRate {

    @SerializedName("ExchangeRateId")
    @Expose
    var exchangeRateId: Int? = null
    @SerializedName("CountryId")
    @Expose
    var countryId: Int? = null
    @SerializedName("Code")
    @Expose
    var code: String? = null
    @SerializedName("ExchangeRate")
    @Expose
    var exchangeRate: String? = null
    @SerializedName("CountryCode3")
    @Expose
    var countryCode3: String? = null
    @SerializedName("Co untryName")
    @Expose
    var countryName: String? = null
    @SerializedName("CurrencyCode")
    @Expose
    var currencyCode: String? = null
    @SerializedName("CurrencyName")
    @Expose
    var currencyName: String? = null
    @SerializedName("FlagCode")
    @Expose
    var flagCode: String? = null
    @SerializedName("LastWeekRates")
    @Expose
    var lastWeekRates: List<LastWeekRate>? = null

}

class LastWeekRate {

    @SerializedName("Date")
    @Expose
    var date: String? = null
    @SerializedName("Rate")
    @Expose
    var rate: String? = null
}

class Item(var type: LayoutType?) {
    enum class LayoutType {
        LAYOUT_ONE, LAYOUT_TWO
    }
}


