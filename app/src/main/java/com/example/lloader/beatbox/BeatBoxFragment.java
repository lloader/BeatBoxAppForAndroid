package com.example.lloader.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lloader.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.lloader.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

/**
 * Created by Alexander Garkavenko
 */

public class BeatBoxFragment extends Fragment {
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    private BeatBox mBeatBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding fragmentBeatBoxBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);
        fragmentBeatBoxBinding.beatBoxRecyclerView
                .setLayoutManager(new GridLayoutManager(getContext(), 3));
        fragmentBeatBoxBinding.beatBoxRecyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return fragmentBeatBoxBinding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        public SoundHolder(final ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new ViewModel(mBeatBox));

        }

        public void bind(final Sound sound) {
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSoundList;

        public SoundAdapter(List<Sound> sounds) {
            mSoundList = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            final Sound sound = mSoundList.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSoundList.size();
        }
    }
}
