package com.example.gitrootdreamslessonshometasks

data class BitcoinResponse(val data: Data?)


data class Data(
    var id:String,
    var symbol:String ,
    var currencySymbol:String,
    var rateUsd:String
)
