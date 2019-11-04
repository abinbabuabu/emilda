package com.emilda.emildaapp.Dataclass

import androidx.recyclerview.widget.DiffUtil

data class portfolio(var imgurl: String, var title: String, var url: String) {
    constructor() : this("", "", "")

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<portfolio>() {
            override fun areItemsTheSame(oldItem: portfolio, newItem: portfolio): Boolean =
                oldItem.imgurl == newItem.imgurl

            override fun areContentsTheSame(oldItem: portfolio, newItem: portfolio): Boolean =
                oldItem.url == newItem.url
        }

    }
}


data class DeliveryDetails(var time: String, var date: String, var location: String, var others: String)


data class PrintRequest(var details: DeliveryDetails, var FileStorageUri: String)


data class PrintData(
    var printerId: Int,
    var title: String,
    var contentType: String,
    var content: String,
    var source: String
)

data class UserType(
    var firstName: String,
    var lastName: String,
    var email: String,
    var occupation: String,
    var credit: Int,
    var fullName: String
)


data class SupportType(
    var UserId:String,
    var FixType: String,
    var BrandName:String,
    var ModelName:String,
    var ProblemDesc:String,
    var DeliveryLocation:String,
    var Date:String,
    var Time:String
)

data class ProjectSupport(
    var UserId: String,
    var PhoneNUmber:String,
    var Desc:String
)