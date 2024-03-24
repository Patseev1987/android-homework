package ru.bogdan.m15_room.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.bogdan.m15_room.R
import ru.bogdan.m15_room.domain.Word


class WordAdapter : ListAdapter<Word, WordAdapter.WordHolder>(WordDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WordHolder = WordHolder.inflateFrom(parent)


    override fun onBindViewHolder(p0: WordHolder, p1: Int) {
        val word = getItem(p1)
        p0.bind(word)
    }

    class WordHolder(rootView: CardView) : ViewHolder(rootView) {

       private val textViewWord = rootView.findViewById<TextView>(R.id.tw_word)
       private val textViewCounter = rootView.findViewById<TextView>(R.id.tw_counter)

        fun bind(word: Word) {
            textViewWord.text = word.word
            textViewCounter.text = word.counter.toString()
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): WordHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.card_for_word, parent, false) as CardView
                return WordHolder(view)
            }
        }


    }
}