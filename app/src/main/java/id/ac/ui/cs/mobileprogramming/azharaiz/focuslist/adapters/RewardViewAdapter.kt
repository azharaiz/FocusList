package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward

class RewardViewAdapter() :
    RecyclerView.Adapter<RewardViewAdapter.RewardViewHolder>() {

    private var rewardList = emptyList<Reward>()

    class RewardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rewardItemTitle: TextView = view.findViewById(R.id.rewardItemTitle)
        val rewardItemStatus: TextView = view.findViewById(R.id.rewardStatus)
        val rewardItemDescription: TextView = view.findViewById(R.id.rewardItemDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_reward_item, parent, false)
        return RewardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val currentItem = rewardList[position]
        holder.rewardItemTitle.text = currentItem.title
        holder.rewardItemDescription.text = currentItem.description
    }

    override fun getItemCount() = rewardList.size

    fun setData(rewards: List<Reward>) {
        this.rewardList = rewards
        notifyDataSetChanged()
    }
}