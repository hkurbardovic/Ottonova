package com.hkurbardovic.ottonova.adapters

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hkurbardovic.ottonova.network.models.HealthPrompt
import com.hkurbardovic.ottonova.network.models.TimelineEvent
import java.util.*

@BindingAdapter("timelineEvent")
fun bindTimelineEvent(view: TextView, timelineEvent: TimelineEvent?) {
    if (timelineEvent == null) return
    view.text = String.format(
        Locale.getDefault(),
        "uuid: %s\ntimestamp: %s\ndisplayCategory: %s\ntitle: %s\ndescription: %s\ncategory: %s",
        timelineEvent.uuid,
        timelineEvent.timestamp,
        timelineEvent.displayCategory,
        timelineEvent.title,
        timelineEvent.description,
        timelineEvent.category
    )
}

@BindingAdapter("selectedHealthPromptStyleAndVisibility")
fun bindSelectedHealthPromptStyleAndVisibility(view: ViewGroup, healthPrompt: HealthPrompt?) {
    val healthPromptMessage = healthPrompt?.message
    val healthPromptUrl = healthPrompt?.style?.image

    if ((healthPromptMessage == null || healthPromptMessage.isEmpty()) && (healthPromptUrl == null || healthPromptUrl.isEmpty())) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }

    val primaryColor = healthPrompt?.style?.primaryColor ?: return
    val color = Color.parseColor(primaryColor)
    view.setBackgroundColor(color)
}

@BindingAdapter("selectedHealthPromptStyleAndVisibility")
fun bindSelectedHealthPromptStyleAndVisibility(view: TextView, healthPrompt: HealthPrompt?) {
    if (healthPrompt?.message != null && healthPrompt.message.isNotEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }

    val secondaryColor = healthPrompt?.style?.secondaryColor ?: return
    val color = Color.parseColor(secondaryColor)
    view.setTextColor(color)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, url: String?) {
    if (url == null) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
        Glide.with(view.context).load(url).into(view)
    }
}