package com.firebaseexample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val users: CollectionReference = db.collection("users")
            val johnSnow = User("John", "Snow")
            val nedStark = User("Ned", "Stark")
            val us1 = User("Sam", "Tarli")
            val us2 = User("Brandon", "Stark")
            val us3 = User("Drakaris", "Targarien")
            val us4 = User("Drogo", "Vasiliev")
            val us5 = User("Tirion", "Lannister")
            users.add(johnSnow)
            users.add(nedStark)
            users.add(us1)
            users.add(us2)
            users.add(us3)
            users.add(us4)
            users.add(us5)
        }

        btn_user_list.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
