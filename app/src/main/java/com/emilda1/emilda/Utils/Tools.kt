import com.emilda1.emilda.R
import com.emilda1.emilda.Utils.BottomAppBarCutCornersTopEdge
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable

fun ResolvePosition(position: Int): String {
    return when (position) {
        0 -> {
            "Website Development"
        }
        1 -> {
            "Mobile App Development"
        }
        2 -> {
            "Graphic Design"
        }
        3 -> {
            "Printing Service"
        }
        4 -> {
            "Digital Marketing"
        }
        5 -> {
            "Desktop Support"
        }
        else -> {
            "Error"
        }
    }
}

fun ResolveUrl(position: Int): String {
    return when (position) {
        0 -> {
            "webdevelopment"
        }
        1 -> {
            "appdevelopment"
        }
        2 -> {
            "graphicdesign"
        }
        3 -> {
            "printing"
        }
        4 -> {
            "digitalmarketing"
        }
        5 -> {
            "desktopsupport"
        }
        else -> {
            "Error"
        }
    }
}

fun ResolveDetails(position: Int): String {
    return when (position) {
        0 -> {
            "Services Available (11)"
        }
        1 -> {
            "Services Available (12)"
        }
        2 -> {
            "Services Available (21)"
        }
        3 -> {
            "Services Available (6)"
        }
        4 -> {
            "Services Available (6)"
        }
        else -> {
            "Services Available (9)"
        }

    }
}

fun ResolveWorksList(position: Int): List<String> {
    return when (position) {
        0 -> {
            listOf(
                "Bootstrap",
                "Angular",
                "WordPress",
                "E-Commerce Website",
                "Content Management System",
                "Basic HTML/CSS"
            )
        }
        1 -> {
            listOf(
                "Hybrid App Development in Ionic",
                "Hybrid App Development in Angular",
                "Native App's in iOS",
                "Native App's in Android",
                "Web App's in Java Script",
                "Web App's in CSS"
            )
        }
        2 -> {
            listOf(
                "Visual Identity",
                "Marketing and Advertising",
                "User Interface Graphic Designing",
                "Art and Illustration",
                "Publication Graphic Design",
                "Packaging Graphic Designing",
                "Motion Graphic Designing",
                "Environmental Graphic Design"
            )
        }
        4 -> {
            listOf(
                "Website Marketing",
                "Search Engine Optimisation",
                "Content Marketing",
                "Social Media Marketing",
                "PPC Advertising",
                "Email Marketing"
            )
        }
        else -> {
            listOf("")
        }
    }
}

fun getText1(position: Int): List<String> {
    return when (position) {
        0 -> {
            listOf(
                "Initiate the project and brief",
                "Connect & Discuss",
                "Design & Develop with collaboration",
                "Test and Deploy"
            )
        }

        1 -> {
            listOf(
                "Initiate the project and brief",
                "Connect & Discuss",
                "Design & Develop with collaboration",
                "Test and Deploy"
            )
        }

        2 -> {
            listOf("Attract Visitors", "Convert Leads", "Close Customers", "Delight Promoters")
        }

        4 -> {
            listOf("Definition Phase", "Creation Phase", "Feedback Phase", "Delivery Phase")
        }

        else -> {
            listOf("")
        }
    }
}

fun getText2(position: Int): List<String> {
    return when (position) {

        0 -> {
            listOf(
                "Start a project by clicking below and we'll get back to you.",
                "Let us discuss by call, WhatsApp or Duo. Let us know what time is convenient for you.",
                "We will now design and develop the app while keeping you updated at every step.",
                "The final phase consists of testing for any errors and deployment of the app."
            )
        }
        1 -> {
            listOf(
                "Start a project by clicking below and we'll get back to you.",
                "Let us discuss by call, WhatsApp or Duo. Let us know what time is convenient for you.",
                "We will now design and develop the website while keeping you updated at every step.",
                "The final phase consists of testing for any errors and deployment of the app."
            )
        }
        2 -> {
            listOf(
                "Your company's website is the cornerstone of your digital marketing strategy.",
                "The goal of digital marketing is to attract, engage and convert your leads.",
                "We will now design and develop the website while keeping you updated at every step.",
                "The final phase consists of testing for any errors and deployment of the app."
            )
        }
        4 -> {
            listOf(
                "Creative brief, graphic design research, and Brainstorming/Mood Boarding.",
                "Sketching, Design Building, and refining. The creativity and teamwork comes together.",
                "Presenting and Revisions are made on the project in this phase.",
                "The final delivery of the project is done in this phase."
            )
        }
        else -> {
            listOf()
        }
    }
}

fun getSmallIcons(position: Int): List<Int> {
    return when (position) {
        0 -> {
            listOf(R.drawable.ic_mob1, R.drawable.ic_mob2, R.drawable.ic_mob3, R.drawable.ic_mob4)
        }
        1 -> {
            listOf(R.drawable.ic_mob1, R.drawable.ic_mob2, R.drawable.ic_mob3, R.drawable.ic_mob4)
        }
        2 -> {
            listOf(R.drawable.ic_mob1, R.drawable.ic_mob2, R.drawable.ic_mob3, R.drawable.ic_mob4)
        }
        4 -> {
            listOf(R.drawable.ic_mob1, R.drawable.ic_mob2, R.drawable.ic_mob3, R.drawable.ic_mob4)
        }
        else -> {
            listOf(-1)
        }
    }


}


fun setUpBottomAppBarShapeAppearance(
    fab: FloatingActionButton,
    bar: BottomAppBar
) {
    val fabShapeAppearanceModel = fab.shapeAppearance
    val cutCornersFab =
        fabShapeAppearanceModel.bottomLeftCorner is CutCornerTreatment && fabShapeAppearanceModel.bottomRightCorner is CutCornerTreatment

    val topEdge = if (cutCornersFab)
        BottomAppBarCutCornersTopEdge(
            bar.fabCradleMargin,
            bar.fabCradleRoundedCornerRadius,
            bar.cradleVerticalOffset
        )
    else
        BottomAppBarTopEdgeTreatment(
            bar.fabCradleMargin,
            bar.fabCradleRoundedCornerRadius,
            bar.cradleVerticalOffset
        )


    val babBackground = bar.background as MaterialShapeDrawable
    babBackground.shapeAppearanceModel.topEdge = topEdge
    babBackground.invalidateSelf()
}
