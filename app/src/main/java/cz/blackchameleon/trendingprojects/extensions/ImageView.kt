package cz.blackchameleon.trendingprojects.extensions

import android.widget.ImageView
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import cz.blackchameleon.trendingprojects.R

/**
 * Extension function that sets image into image view
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
fun ImageView.setImage(imagePath: String?, placeholder: Int = R.color.background_placeholder) {
    val transformation: Transformation = RoundedTransformationBuilder()
        .borderColor(resources.getColor(R.color.placeholder_stroke))
        .borderWidthDp(1f)
        .cornerRadiusDp(10f)
        .oval(false)
        .build()

    Picasso.get()
        .load(imagePath)
        .fit()
        .centerCrop()
        .transform(transformation)
        .placeholder(placeholder)
        .into(this)
}