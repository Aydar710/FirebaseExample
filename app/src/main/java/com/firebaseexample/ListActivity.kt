package com.firebaseexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    var adapter : UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)

        adapter = UserListAdapter()
        recycler_users.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_activity, menu)

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = "Search recipe"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) return true
                searchData(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) return true
                searchData(query)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    fun searchData(query : String){
        db.collection("users").whereEqualTo("firstName", query)
            .get()
            .addOnCompleteListener{
                val userList = arrayListOf<User>()
                it.result?.let {result ->
                    for(doc in result){
                        userList.add(
                            User(doc.getString("firstName"), doc.getString("lastName"))
                        )
                    }
                }
                adapter?.submitList(userList)
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed load users", Toast.LENGTH_SHORT).show()
            }
    }
}
