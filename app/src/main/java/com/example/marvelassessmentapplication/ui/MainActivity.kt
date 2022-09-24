package com.example.marvelassessmentapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelassessmentapplication.databinding.ActivityMainBinding
import com.example.marvelassessmentapplication.domain.model.CharacterModel
import com.example.marvelassessmentapplication.ui.charactersList.CharactersViewModel
import com.example.marvelassessmentapplication.utils.CharacterListAdapter
import com.example.marvelassessmentapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    var valueRepeat = 3
    var paginatedValue = 0
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var layoutManager : GridLayoutManager
    private val viewModel : CharactersViewModel by viewModels()
    var list = arrayListOf<CharacterModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.characterRecyclerView
        layoutManager = GridLayoutManager(this,2)
        recyclerViewCharacters()
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1)
                {
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callAPI()
                }
            }
        })
        Log.d("tag", Constants.timeStamp)
    }
    private fun callAPI(){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(valueRepeat){
                viewModel._marvelValue.collect{value->
                    when {
                        value.isLoading -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                        value.error.isNotBlank() -> {
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            Toast.makeText(this@MainActivity, value.error, Toast.LENGTH_LONG).show()
                        }
                        value.charactersList.isNotEmpty() -> {
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            adapter.setData(value.charactersList as ArrayList<CharacterModel>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewCharacters() {
        recyclerView = binding.characterRecyclerView
        adapter = CharacterListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllCharactersData(paginatedValue)
        callAPI()
    }
}