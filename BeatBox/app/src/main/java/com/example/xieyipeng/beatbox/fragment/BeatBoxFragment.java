package com.example.xieyipeng.beatbox.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.xieyipeng.beatbox.R;
import com.example.xieyipeng.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.xieyipeng.beatbox.databinding.ListItemSoundBinding;
import com.example.xieyipeng.beatbox.tools.BeatBox;
import com.example.xieyipeng.beatbox.tools.Sound;
import com.example.xieyipeng.beatbox.tools.SoundViewModel;

import java.util.List;


/**
 * Created by xieyipeng on 2018/9/20.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox beatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
        binding.recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recycleView.setAdapter(new SoundAdapter(beatBox.getSoundList()));
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 解决手机旋转问题
        setRetainInstance(true);

        beatBox = new BeatBox(getActivity());
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> soundList;

        public SoundAdapter(List<Sound> sounds) {
            soundList = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = soundList.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return soundList.size();
        }
    }

    private class SoundHolder extends RecyclerView.ViewHolder {

        private ListItemSoundBinding binding;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setViewModel(new SoundViewModel(beatBox));
        }

        public void bind(Sound sound) {
            binding.getViewModel().setSound(sound);
            binding.executePendingBindings();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBox.release();
    }
}
