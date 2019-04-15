package com.firebaseexample

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_user.*

class UserListAdapter : ListAdapter<User, UserListAdapter.UserHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: User) {
            txt_first_name.text = user.firstName
            txt_last_name.text = user.lastName
        }

    }
}