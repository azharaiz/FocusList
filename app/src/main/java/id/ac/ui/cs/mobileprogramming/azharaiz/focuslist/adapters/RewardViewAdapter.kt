package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardItemBinding

//import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.RewardListFragmentDirections

class RewardViewAdapter() :
    RecyclerView.Adapter<RewardViewAdapter.RewardViewHolder>() {

    private var rewardList = emptyList<Reward>()

    class RewardViewHolder(fragmentRewardItemBinding: FragmentRewardItemBinding) :
        RecyclerView.ViewHolder(fragmentRewardItemBinding.root) {

        private var binding: FragmentRewardItemBinding = fragmentRewardItemBinding

        fun bind(reward: Reward) {
            binding.reward = reward
            binding.executePendingBindings()
        }

//        fun navigation(reward: Reward) {
//            itemView.setOnClickListener {
//                val action = RewardListFragmentDirections
//                    .actionRewardListFragmentToRewardUpdateFragment(reward)
//                itemView.findNavController().navigate(action)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view = LayoutInflater.from(parent.context)
        val itemBinding =
            FragmentRewardItemBinding.inflate(view, parent, false)
        return RewardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val currentItem = rewardList[position]
        holder.bind(currentItem)
//        holder.navigation(currentItem)
    }

    override fun getItemCount() = rewardList.size

    fun setData(rewards: List<Reward>) {
        this.rewardList = rewards
        notifyDataSetChanged()
    }
}