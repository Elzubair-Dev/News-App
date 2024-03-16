package com.example.newsapp.common.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Anakiwa
import com.example.newsapp.ui.theme.BusinessColor
import com.example.newsapp.ui.theme.Carnation
import com.example.newsapp.ui.theme.EntertainmentColor
import com.example.newsapp.ui.theme.GeneralColor
import com.example.newsapp.ui.theme.GrannySmithApple
import com.example.newsapp.ui.theme.GreenMist
import com.example.newsapp.ui.theme.HealthColor
import com.example.newsapp.ui.theme.HummingBird
import com.example.newsapp.ui.theme.MandysPink
import com.example.newsapp.ui.theme.MoonRaker
import com.example.newsapp.ui.theme.MossGreen
import com.example.newsapp.ui.theme.Norway
import com.example.newsapp.ui.theme.PeachOrange
import com.example.newsapp.ui.theme.Perano
import com.example.newsapp.ui.theme.Perfume
import com.example.newsapp.ui.theme.PigeonPost
import com.example.newsapp.ui.theme.Portica
import com.example.newsapp.ui.theme.Primrose
import com.example.newsapp.ui.theme.Rajah
import com.example.newsapp.ui.theme.ScienceColor
import com.example.newsapp.ui.theme.SeaPink
import com.example.newsapp.ui.theme.SportsColor
import com.example.newsapp.ui.theme.TechnologyColor
import com.example.newsapp.ui.theme.Tiara
import com.example.newsapp.ui.theme.Wewak
import com.example.newsapp.ui.theme.Zombie
import java.io.ByteArrayOutputStream

object Common {
    val Countries = listOf(
        Country(
            name = "United kingdom",
            code = "gb",
            flag = R.drawable.united_kingdom
        ),
        Country(
            name = "United states",
            code = "us",
            flag = R.drawable.united_states
        ),
        Country(
            name = "Nigeria",
            code = "ng",
            flag = R.drawable.nigeria
        ),
        Country(
            name = "Canada",
            code = "ca",
            flag = R.drawable.canada
        ),
        Country(
            name = "Australia",
            code = "au",
            flag = R.drawable.australia
        ),
        Country(
            name = "South Africa",
            code = "za",
            flag = R.drawable.south_africa
        ),
        Country(
            name = "Ireland",
            code = "ie",
            flag = R.drawable.ireland
        )
    )

    val Categories = listOf(
        Category(
            title = "Sports",
            mainTag = "football",
            iconID = R.drawable.sports,
            color = SportsColor.copy(0.5f)
        ),
        Category(
            title = "Business",
            mainTag = "stock exchange",
            iconID = R.drawable.business,
            color = BusinessColor.copy(0.5f)
        ),
        Category(
            title = "Entertainment",
            mainTag = "movie",
            iconID = R.drawable.entertainment,
            color = EntertainmentColor.copy(0.5f)
        ),
        Category(
            title = "Health",
            mainTag = "medicine",
            iconID = R.drawable.health,
            color = HealthColor.copy(0.5f)
        ),
        Category(
            title = "Science",
            mainTag = "discovery",
            iconID = R.drawable.science,
            color = ScienceColor.copy(0.5f)
        ),
        Category(
            title = "Technology",
            mainTag = "ai",
            iconID = R.drawable.technology,
            color = TechnologyColor.copy(0.5f)
        ),
        Category(
            title = "General",
            mainTag = "android",
            iconID = R.drawable.general,
            color = GeneralColor.copy(0.5f)
        )
    )

    val SportsTags = listOf(
        Tag(
            name = "Football",
            color = Wewak.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Basketball",
            color = Zombie.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Baseball",
            color = HummingBird.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Tennis",
            color = GrannySmithApple.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Volleyball",
            color = Perano.copy(alpha = 0.35f)
        ),
        Tag(
            name = "UFC",
            color = Norway.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Boxing",
            color = SeaPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Olympics",
            color = Perfume.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Cycling",
            color = MoonRaker.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Bodybuilding",
            color = PeachOrange.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Formula 1",
            color = Carnation.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Rally race",
            color = Rajah.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Moto gb",
            color = Portica.copy(alpha = 0.35f)
        )
    )

    val BusinessTags = listOf(
        Tag(
            name = "Currency",
            color = Zombie.copy(0.35f)
        ),
        Tag(
            name = "Wealth",
            color = PeachOrange.copy(0.35f)
        ),
        Tag(
            name = "Profit",
            color = MossGreen.copy(0.35f)
        ),
        Tag(
            name = "Interest",
            color = SeaPink.copy(0.35f)
        ),
        Tag(
            name = "Stock exchange",
            color = Tiara.copy(0.35f)
        )
    )

    val HealthTags = listOf(
        Tag(
            name = "Medicine",
            color = HummingBird.copy(0.35f)
        ),
        Tag(
            name = "Cancer",
            color = MoonRaker.copy(0.35f)
        ),
        Tag(
            name = "Protein",
            color = GreenMist.copy(0.35f)
        ),
        Tag(
            name = "Psychology",
            color = Perfume.copy(0.35f)
        ),
        Tag(
            name = "Loneliness",
            color = Wewak.copy(0.35f)
        ),
        Tag(
            name = "Heart Attack",
            color = Carnation.copy(0.35f)
        ),
        Tag(
            name = "Food",
            color = GrannySmithApple.copy(0.35f)
        ),
        Tag(
            name = "Healthy",
            color = Anakiwa.copy(0.35f)
        ),
        Tag(
            name = "Prevention",
            color = Norway.copy(0.35f)
        )
    )

    val ScienceTags = listOf(
        Tag(
            name = "Mars",
            color = Zombie.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Space",
            color = GrannySmithApple.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Discovery",
            color = Perano.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Earth",
            color = SeaPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Scientist",
            color = MoonRaker.copy(alpha = 0.35f)
        ),
        Tag(
            name = "NASA",
            color = Carnation.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Geography",
            color = Portica.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Nat Geo",
            color = Tiara.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Documentary",
            color = PigeonPost.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Scientists",
            color = Anakiwa.copy(alpha = 0.35f)
        )
    )

    val EntertainmentTags = listOf(
        Tag(
            name = "Movie",
            color = Carnation.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Oscars",
            color = Zombie.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Actor",
            color = Wewak.copy(alpha = 0.35f)
        ),
        Tag(
            name = "WWE",
            color = HummingBird.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Followers",
            color = GrannySmithApple.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Social media",
            color = Perano.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Tik Tok",
            color = SeaPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Instagram",
            color = Perfume.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Facebook",
            color = PeachOrange.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Twitter",
            color = Anakiwa.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Anime",
            color = Rajah.copy(alpha = 0.35f)
        ),
        Tag(
            name = "A play",
            color = Portica.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Interview",
            color = GreenMist.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Scope with Raya",
            color = Tiara.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Got Talent",
            color = MandysPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Theater",
            color = PigeonPost.copy(alpha = 0.35f)
        )
    )

    val TechnologyTags = listOf(
        Tag(
            name = "Apple",
            color = Primrose.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Developer",
            color = GrannySmithApple.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Smart Watch",
            color = PigeonPost.copy(alpha = 0.35f)
        ),
        Tag(
            name = "IOT",
            color = MandysPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "App",
            color = GreenMist.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Android",
            color = Portica.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Google",
            color = Rajah.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Cryptography",
            color = PeachOrange.copy(alpha = 0.35f)
        ),
        Tag(
            name = "AI",
            color = MoonRaker.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Phone",
            color = SeaPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Tablet",
            color = Perfume.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Computer",
            color = Norway.copy(alpha = 0.25f)
        ),
        Tag(
            name = "Processor",
            color = Perano.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Pc",
            color = HummingBird.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Redmi",
            color = Zombie.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Laptop",
            color = Wewak.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Amazon",
            color = MossGreen.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Galaxy",
            color = Anakiwa.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Web",
            color = Norway.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Version",
            color = PigeonPost.copy(alpha = 0.35f)
        )
    )

    val GeneralTags = listOf(
        Tag(
            name = "Android",
            color = Wewak.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Football",
            color = Zombie.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Documentary",
            color = HummingBird.copy(alpha = 0.35f)
        ),
        Tag(
            name = "UFC",
            color = Portica.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Stock exchange",
            color = Perano.copy(alpha = 0.2f)
        ), Tag(
            name = "Google",
            color = Norway.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Nat Geo",
            color = SeaPink.copy(alpha = 0.2f)
        ),
        Tag(
            name = "AI",
            color = Perfume.copy(alpha = 0.2f)
        ),
        Tag(
            name = "Profit",
            color = MoonRaker.copy(alpha = 0.3f)
        ),
        Tag(
            name = "NASA",
            color = PeachOrange.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Amazon",
            color = Carnation.copy(alpha = 0.2f)
        ),
        Tag(
            name = "Galaxy",
            color = Rajah.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Developer",
            color = GrannySmithApple.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Medicine",
            color = GreenMist.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Processor",
            color = Tiara.copy(alpha = 0.35f)
        ), Tag(
            name = "Prevention",
            color = MandysPink.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Discovery",
            color = PigeonPost.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Design",
            color = Primrose.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Engine",
            color = Anakiwa.copy(alpha = 0.35f)
        ),
        Tag(
            name = "Pixel",
            color = MossGreen.copy(alpha = 0.35f)
        )
    )

    fun Int.toEmoji(): String {
        return String(Character.toChars(this))
    }

    fun String.toCategory(): Category {
        return when (this) {
            Categories[0].title.lowercase() -> {
                Category(
                    title = "Sports",
                    mainTag = "football",
                    iconID = R.drawable.sports,
                    color = SportsColor
                )
            }

            Categories[1].title.lowercase() -> {
                Category(
                    title = "Business",
                    mainTag = "stock exchange",
                    iconID = R.drawable.round_work_24,
                    color = BusinessColor
                )
            }

            Categories[2].title.lowercase() -> {
                Category(
                    title = "Entertainment",
                    mainTag = "movie",
                    iconID = R.drawable.entertainment,
                    color = EntertainmentColor
                )
            }

            Categories[3].title.lowercase() -> {
                Category(
                    title = "Health",
                    mainTag = "medicine",
                    iconID = R.drawable.health,
                    color = HealthColor
                )
            }

            Categories[4].title.lowercase() -> {
                Category(
                    title = "Science",
                    mainTag = "discovery",
                    iconID = R.drawable.science,
                    color = ScienceColor
                )
            }

            Categories[5].title.lowercase() -> {
                Category(
                    title = "Technology",
                    mainTag = "ai",
                    iconID = R.drawable.technology,
                    color = TechnologyColor
                )
            }

            else -> {
                Category(
                    title = "General",
                    mainTag = "android",
                    iconID = R.drawable.baseline_blur_on_24,
                    color = GeneralColor
                )
            }
        }
    }

    fun String.codeToCountry(): Country {
        return when (this) {
            Countries[0].code -> {
                Country(
                    name = "United kingdom",
                    code = "gb",
                    flag = R.drawable.united_kingdom
                )
            }

            Countries[1].code -> {
                Country(
                    name = "United states",
                    code = "us",
                    flag = R.drawable.united_states
                )
            }

            Countries[2].code -> {
                Country(
                    name = "Nigeria",
                    code = "ng",
                    flag = R.drawable.nigeria
                )
            }

            Countries[3].code -> {
                Country(
                    name = "Canada",
                    code = "ca",
                    flag = R.drawable.canada
                )
            }

            Countries[4].code -> {
                Country(
                    name = "Australia",
                    code = "au",
                    flag = R.drawable.australia
                )
            }

            Countries[5].code -> {
                Country(
                    name = "South Africa",
                    code = "za",
                    flag = R.drawable.south_africa
                )
            }

            else -> {
                Country(
                    name = "Ireland",
                    code = "ie",
                    flag = R.drawable.ireland
                )
            }
        }
    }

    fun Modifier.shimmerEffect(): Modifier = composed {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

        val transition = rememberInfiniteTransition(label = "transition")

        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Restart
            ), label = "infinite"
        )

        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.onSurface,
                    MaterialTheme.colorScheme.surface
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width, size.height.toFloat())
            )
        )
            .onGloballyPositioned {
                size = it.size
            }
    }

    fun Bitmap.encodeToString(): String {

        val byteArrayOutputStream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byte = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byte, Base64.DEFAULT)

    }

    fun String?.toBitmap(): Bitmap? {
        return if (this != null) {
            val imageBytes = Base64.decode(this, 0)
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        } else {
            null
        }
    }

}