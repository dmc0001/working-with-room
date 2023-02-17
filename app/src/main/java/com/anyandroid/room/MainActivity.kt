package com.anyandroid.room

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var insertButton: Button
    private lateinit var getButton: Button
    private lateinit var editTextTitle: EditText
    private lateinit var editTextBody: EditText
    private lateinit var postsAdapter: RecyclerAdapter
    private lateinit var postsDataBase: PostsDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextTitle = findViewById(R.id.editTextTextPersonName)
        editTextBody = findViewById(R.id.editTextTextPersonName2)
        insertButton = findViewById(R.id.button)
        getButton = findViewById(R.id.button2)
        postsRecyclerView = findViewById(R.id.recyclerView)
        postsAdapter = RecyclerAdapter(emptyList(), this)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postsAdapter
        postsDataBase = PostsDataBase.getInstance(this)

        getData()
        insertButton.setOnClickListener {
            Toast.makeText(this, "Inserting", Toast.LENGTH_SHORT).show()
            insertData()
            getData()
        }

        getButton.setOnClickListener {
            getData()
        }

    }

    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getData() {
        Toast.makeText(this, "Getting data", Toast.LENGTH_SHORT).show()
        postsDataBase.postsDao.getPosts()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Post>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "dmc 69 onSubscribe: ")
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(t: List<Post>) {
                    postsAdapter.setList(t)
                    postsAdapter.notifyDataSetChanged()
                    Log.d(TAG, "dmc 69 onSuccess: setList ")
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG, "dmc 69 onError: ${e.message} ")
                }
            })
    }


    private fun insertData() {
        postsDataBase.postsDao.insertPost(
            Post(
                User( "dmc",1 ),
                editTextTitle.text.toString(),
                editTextBody.text.toString()
            )
        )
            .subscribeOn(Schedulers.computation())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "dmc 69 onSubscribe: ")
                }

                override fun onComplete() {
                    Log.d(TAG, "dmc 69 onComplete: ")
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG, "dmc 69 onError: ${e.message} ")
                }
            })

    }

    override fun onPostClick(position: Int) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(position: Int) {
        postsDataBase.postsDao.deletePost(postsAdapter.postsList[position])
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}

                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        getData()
    }
}




