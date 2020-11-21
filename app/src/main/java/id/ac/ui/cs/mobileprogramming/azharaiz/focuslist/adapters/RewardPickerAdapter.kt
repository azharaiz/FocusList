package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.RewardPickerItemBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.RewardStatusListener

class RewardPickerAdapter(val rewardStatusListener: RewardStatusListener) :
    RecyclerView.Adapter<RewardPickerAdapter.RewardPickerHolder>() {

    private var rewardList = emptyList<Reward>()

    class RewardPickerHolder(
        rewardPickerItemBinding: RewardPickerItemBinding,
        val rewardStatusListener: RewardStatusListener
    ) :
        RecyclerView.ViewHolder(rewardPickerItemBinding.root) {

        private var binding: RewardPickerItemBinding = rewardPickerItemBinding

        fun bind(reward: Reward) {
            binding.reward = reward
            binding.executePendingBindings()
        }

        fun navigation(reward: Reward) {
            itemView.setOnClickListener {
                rewardStatusListener.onRewardClicked(reward)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardPickerHolder {
        val view = LayoutInflater.from(parent.context)
        val itemBinding =
            RewardPickerItemBinding.inflate(view, parent, false)
        return RewardPickerHolder(itemBinding, rewardStatusListener)
    }

    override fun onBindViewHolder(holder: RewardPickerHolder, position: Int) {
        val currentItem = rewardList[position]
        holder.bind(currentItem)
        holder.navigation(currentItem)
    }

    override fun getItemCount() = rewardList.size

    fun setData(rewards: List<Reward>) {
        this.rewardList = rewards
        notifyDataSetChanged()
    }
}