package com.tammidev.clickerduckz.clickerduckz

import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.resource_item.*


class ResourceAdapter : ListAdapter<ResourceViewState, Holder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ResourceViewState> = object : DiffUtil.ItemCallback<ResourceViewState>() {
            override fun areItemsTheSame(oldItem: ResourceViewState?, newItem: ResourceViewState?) = oldItem?.equals(newItem) == true
            override fun areContentsTheSame(oldItem: ResourceViewState?, newItem: ResourceViewState?) = oldItem?.equals(newItem) == true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(LayoutInflater.from(parent.context).inflate(R.layout.resource_item, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}

class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: ResourceViewState) {
        itemLabel.text = item.count
        itemImage.setBackgroundResource(item.drawableRes)
        (itemImage.background as AnimationDrawable).start()
        itemLevelProgress.progress = item.levelProgress
        itemButton.isEnabled = item.enableButton
        itemButton.text = item.buttonLabel
        itemButton.setOnClickListener {
            item.onClick()
            val animation = ObjectAnimator.ofInt(itemProgress, "progress", 0, 100)
            animation.duration = item.produceTime
            animation.interpolator = DecelerateInterpolator()
            animation.start()
        }
    }
}