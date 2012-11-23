package net.tsz.afinal.view;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalBitmap.OnImageDownloadListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CachedImageView extends ImageView {
	private FinalBitmap mFinalBitmap;

	public CachedImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CachedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CachedImageView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mFinalBitmap = new FinalBitmap(context);
		mFinalBitmap.init();
	}

	public void setImageUrl(String url) {
		setImage(url, 0, 0);
	}

	public void setImageUrl(String url, final Integer fallbackResource) {
		setImage(url, fallbackResource, 0);
	}

	public void setImageUrl(String url, final Integer fallbackResource,
			final Integer loadingResource) {
		setImage(url, fallbackResource, loadingResource);
	}

	private void setImage(String url, final Integer fallbackResource,
			final Integer loadingResource) {
		if (loadingResource > 0) {
			setImageResource(loadingResource);
		}
		mFinalBitmap.downloadImage(new OnImageDownloadListener() {
			@Override
			public void imageDownloaded(boolean isSuccess, Bitmap bm) {
				if (isSuccess) {
					setImageBitmap(bm);
				} else {
					if (fallbackResource > 0) {
						setImageResource(fallbackResource);
					}
				}
			}
		}, url);
	}
}
