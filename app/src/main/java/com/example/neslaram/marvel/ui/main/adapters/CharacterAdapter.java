package com.example.neslaram.marvel.ui.main.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.data.model.Character;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {


    private static final String TAG = CharacterAdapter.class.getSimpleName();
    private List<Character> characters;
    private OnItemClickListener<Character> mItemClickListener;


    public CharacterAdapter(List<Character> characters, OnItemClickListener<Character> itemClickListener) {
        this.characters = characters;
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();

        holder.setCharacter(characters.get(index));
        holder.bindViewHolder();
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void addItems(List<Character> characters) {
        int size = this.characters.size();
        this.characters.addAll(characters);
        notifyItemRangeInserted(size, this.characters.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.imgAvatar)
        ImageView imgCover;
        @Bind(R.id.txtName)
        TextView txtName;

        private Character character;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ((CardView) itemView).setPreventCornerOverlap(false);

            itemView.setOnClickListener(this);
        }

        public void bindViewHolder() {
            txtName.setText(character.getName());
            imgCover.setImageResource(R.mipmap.ic_launcher);
            imgCover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            String imgPath = character.getThumbnail();
            if (!imgPath.isEmpty()) {
                Glide.with(imgCover.getContext())
                        .load(imgPath)
                        .crossFade()
                        .centerCrop()
                        .into(imgCover);
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClicked(getAdapterPosition(), character);
        }

        public void setCharacter(Character character) {
            this.character = character;
        }


    }
}