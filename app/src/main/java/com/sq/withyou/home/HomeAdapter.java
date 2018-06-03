package com.sq.withyou.home;

import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sq.domain.entity.HomeArticleEntity;
import com.sq.lib_common.utils.UiUtil;
import com.sq.lib_common.utils.Utils;
import com.sq.withyou.App;
import com.sq.withyou.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javakam on 2018/6/3.
 */
public class HomeAdapter extends BaseQuickAdapter<HomeArticleEntity, HomeAdapter.ArticleViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeArticleEntity> data) {
        super(layoutResId, data);
    }

    public HomeAdapter(@Nullable List<HomeArticleEntity> data) {
        super(data);
    }

    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(ArticleViewHolder helper, HomeArticleEntity item) {
//        ((TextView) helper.getView(R.id.tweetName)).setText(item.getAuthor());
//        ((TextView) helper.getView(R.id.tweetText)).setText(item.getTitle());
//        ((TextView) helper.getView(R.id.tweetDate)).setText(item.getNiceDate());
        helper.tweetName.setText(item.getAuthor());
        helper.tweetText.setText(item.getTitle());
        helper.tweetDate.setText(item.getNiceDate());

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                UiUtil.toastShort(App.getApp(), "事件触发了 landscapes and nedes");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Utils.getApp().getResources().getColor(R.color.teal_400));
                ds.setUnderlineText(true);
            }
        };
    }

    protected class ArticleViewHolder extends BaseViewHolder {
        @BindView(R.id.tweetName)
        TextView tweetName;
        @BindView(R.id.tweetText)
        TextView tweetText;
        @BindView(R.id.tweetDate)
        TextView tweetDate;

        public ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
