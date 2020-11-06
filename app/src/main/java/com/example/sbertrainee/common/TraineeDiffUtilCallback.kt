package com.example.sbertrainee.common

import androidx.recyclerview.widget.DiffUtil
import com.example.sbertrainee.model.TraineesCell

class TraineeDiffUtilCallback(
    private val oldList: List<TraineesCell>,
    private val newList: List<TraineesCell>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTraineesCell = oldList[oldItemPosition]
        val newTraineesCell = newList[newItemPosition]
        return oldTraineesCell.id == newTraineesCell.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTraineesCell = oldList[oldItemPosition]
        val newTraineesCell = newList[newItemPosition]
        return oldTraineesCell == newTraineesCell
    }
}