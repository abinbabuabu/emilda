import com.emilda.emilda.Utils.BottomAppBarCutCornersTopEdge
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