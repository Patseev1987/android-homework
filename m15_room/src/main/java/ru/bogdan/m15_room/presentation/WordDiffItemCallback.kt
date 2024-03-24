package ru.bogdan.m15_room.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.bogdan.m15_room.domain.Word

class WordDiffItemCallback: DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(p0: Word, p1: Word): Boolean {
        return p0==p1
    }

    override fun areContentsTheSame(p0: Word, p1: Word): Boolean {
        return  p0==p1
    }
}