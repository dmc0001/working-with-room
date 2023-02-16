package com.anyandroid.room

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var insertButton: Button
    private lateinit var getButton: Button
    private lateinit var editTextTitle: EditText
    private lateinit var editTextBody: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextTitle = findViewById(R.id.editTextTextPersonName)
        editTextBody = findViewById(R.id.editTextTextPersonName2)
        insertButton = findViewById(R.id.button)
        getButton = findViewById(R.id.button2)
        postsRecyclerView = findViewById(R.id.recyclerView)
        val postsAdapter = RecyclerAdapter(emptyList())
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postsAdapter
        val postsDataBase = PostsDataBase.getInstance(this)

        insertButton.setOnClickListener {
            postsDataBase.postsDao.insertPost(
                Post(
                    0,
                    editTextTitle.text.toString(),
                    editTextBody.text.toString(),
                )
            )
                .subscribeOn(Schedulers.computation())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        Toast.makeText(this@MainActivity, "Inserted", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        getButton.setOnClickListener {
            postsDataBase.postsDao.getPosts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Post>> {
                    override fun onSubscribe(d: Disposable) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(t: List<Post>) {

                        postsAdapter.setList(t)
                        postsAdapter.notifyDataSetChanged()
                        Log.d(TAG, "dmc 69 onSuccess: setList ")
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
        }

      /*  postsDataBase.postsDao.insertPost(Post(0, "Title", "Body"))
            .subscribeOn(Schedulers.computation())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        postsDataBase.postsDao.getPosts()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Post>> {
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onSuccess(t: List<Post>) {
                    postsAdapter.setList(t)
                    postsAdapter.notifyDataSetChanged()
                    Log.d(TAG, "dmc 69 onSuccess: setList ")
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })*/

    }
}




