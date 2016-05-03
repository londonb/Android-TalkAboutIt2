package com.epicodus.talkaboutit2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epicodus.talkaboutit2.R;
import com.epicodus.talkaboutit2.models.Category;
import com.epicodus.talkaboutit2.ui.CategoryDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/3/16.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = CategoryViewHolder.class.getSimpleName();

    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;

    private Context mContext;
    private ArrayList<Category> mCategories = new ArrayList<>();

    public CategoryViewHolder(final View itemView, final ArrayList<Category> categories) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mCategories = categories;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("category", Parcels.wrap(mCategories.get(itemPosition)));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getName());
    }
}
