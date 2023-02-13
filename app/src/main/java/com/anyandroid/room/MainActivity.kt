package com.anyandroid.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val postsAdapter = RecyclerAdapter(ArrayList())
        postsRecyclerView.adapter = postsAdapter
        val postsDataBase = PostsDataBase.getInstance(this).also {
           /* it.postsDao.insertPost(Post(2, "Haithem", "coding with Haithem")).subscribeOn(
                Schedulers.computation()
            ).subscribe(object : CompletableObserver {

                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })*/
           /* it.postsDao.getPosts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Post>> {
                    override fun onSubscribe(d: Disposable) {
                        Toast.makeText(this@MainActivity, "onSubscribe", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccess(t: List<Post>) {
                        postsAdapter.setList(t as ArrayList<Post>)
                        postsAdapter.notifyDataSetChanged()
                    }


                    override fun onError(e: Throwable) {
                        Toast.makeText(this@MainActivity, "onError", Toast.LENGTH_SHORT).show()
                    }
                })*/

        }
        insertButton.setOnClickListener {
            postsDataBase.postsDao.insertPost(
                Post(
                    2,
                    editTextTitle.text.toString(),
                    editTextBody.text.toString()
                )
            ).subscribeOn(
                Schedulers.computation()
            ).subscribe(object : CompletableObserver {

                override fun onSubscribe(d: Disposable) {
                    Toast.makeText(this@MainActivity, "onSubscribe", Toast.LENGTH_SHORT).show()
                }

                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "onComplete", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "onError", Toast.LENGTH_SHORT).show()
                }
            })
        }
        getButton.setOnClickListener {
            postsDataBase.postsDao.getPosts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Post>> {
                    override fun onSubscribe(d: Disposable) {
                        Toast.makeText(this@MainActivity, "onSubscribe", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(t: List<Post>) {
                        postsAdapter.setList(t as ArrayList<Post>)
                        postsAdapter.notifyDataSetChanged()
                    }
                })
        }

    }
}




