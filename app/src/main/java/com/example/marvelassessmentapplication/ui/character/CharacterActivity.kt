package com.example.marvelassessmentapplication.ui.character


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.marvelassessmentapplication.R
import com.example.marvelassessmentapplication.databinding.ActivityCharacterBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private val viewModelCharacter : CharacterViewModel by viewModels()
    private var id : Int = 0
    private lateinit var binding: ActivityCharacterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent!=null){
            id = intent.getIntExtra("id",0)
            viewModelCharacter.getCharacterByIdValue(id.toString())
            CoroutineScope(Dispatchers.Main).launch {
                viewModelCharacter._characterValue.collect {
                    when{
                        it.isLoading ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() ->{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CharacterActivity,"Unexpected Error",Toast.LENGTH_LONG).show()
                        }
                        it.characterDetail.isNotEmpty()->{
                            binding.progressBar.visibility = View.GONE
                            it.characterDetail.map { character ->
                                val url = "${character.thumbnail}/landscape_medium.${character.thumbnailExt}"
                                Picasso.get().load(url).placeholder(R.drawable.image5).into(binding.appCompatImageView)
                                binding.name.text = character.name
                                binding.series.text = character.series.toString()
                                Log.d("description",character.description)
                                binding.comics.text = character.comics.toString()
                                binding.events.text = character.events.toString()
                                binding.stories.text=character.stories.toString()
                            }
                        }
                    }
                }
            }
        }
    }
}