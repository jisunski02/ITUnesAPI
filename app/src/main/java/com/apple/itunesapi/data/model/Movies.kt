package com.apple.itunesapi.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "movies"
//    , indices = [Index(value = ["trackName"], unique = true)]
)
data class Movies(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("trackPrice")
    val trackPrice: Double?,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,
    @SerializedName("longDescription")
    val longDescription: String?,

): Serializable