/**
 * Copyright (c) 2012-2013, fengcunhan  fengcunhan@gmial.com .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		mFinalBitmap = FinalBitmap.create(context);
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
	@Override
	public boolean isEnabled() {
		return false;
	}
}
