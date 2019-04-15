package com.firebaseexample

import android.support.v7.util.DiffUtil

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldUser: User, newUser: User): Boolean =
        oldUser == newUser

    override fun areContentsTheSame(oldUser: User, newUser: User): Boolean =
        oldUser.firstName.equals(newUser.firstName) &&
                oldUser.lastName.equals(newUser.lastName)
}
