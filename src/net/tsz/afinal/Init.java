package net.tsz.afinal;
/**
 * Copyright (c) 2012-2013, fengcunhan  (fengcunhan@gmail.com).
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
import java.lang.reflect.Field;

import net.tsz.afinal.annotation.view.EventListener;
import net.tsz.afinal.annotation.view.Select;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.util.Arrays;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;

class Init {
	private static final String TAG = "Init";

	public static void init(Activity activity) {
		Field[] fields = activity.getClass().getDeclaredFields();
		if (!Arrays.isEmpty(fields)) {
			for (Field field : fields) {
				ViewInject viewInject = field.getAnnotation(ViewInject.class);
				if (null != viewInject) {
					field.setAccessible(true);
					int id = viewInject.id();
					if (id != 0) {
						initView(activity, field, id);
					}
					String clickMethod = viewInject.click();
					if (!TextUtils.isEmpty(clickMethod))
						setViewClickListener(activity, field, clickMethod);

					String longClickMethod = viewInject.longClick();
					if (!TextUtils.isEmpty(longClickMethod))
						setViewLongClickListener(activity, field,
								longClickMethod);

					String itemClickMethod = viewInject.itemClick();
					if (!TextUtils.isEmpty(itemClickMethod))
						setItemClickListener(activity, field, itemClickMethod);

					String itemLongClickMethod = viewInject.itemLongClick();
					if (!TextUtils.isEmpty(itemLongClickMethod))
						setItemLongClickListener(activity, field,
								itemLongClickMethod);

					Select select = viewInject.select();
					if (!TextUtils.isEmpty(select.selected()))
						setViewSelectListener(activity, field,
								select.selected(), select.noSelected());
				}
			}
		}
	}

	private static void initView(Activity activity, Field field, int id) {
		try {
			field.set(activity, activity.findViewById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setViewClickListener(Activity activity, Field field,
			String clickMethod) {
		try {
			Object obj = field.get(activity);
			if (obj instanceof View) {
				((View) obj).setOnClickListener(new EventListener(activity)
						.click(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setViewLongClickListener(Activity activity,
			Field field, String clickMethod) {
		try {
			Object obj = field.get(activity);
			if (obj instanceof View) {
				((View) obj).setOnLongClickListener(new EventListener(activity)
						.longClick(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setItemClickListener(Activity activity, Field field,
			String itemClickMethod) {
		try {
			Object obj = field.get(activity);
			if (obj instanceof AbsListView) {
				((AbsListView) obj).setOnItemClickListener(new EventListener(
						activity).itemClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setItemLongClickListener(Activity activity,
			Field field, String itemClickMethod) {
		try {
			Object obj = field.get(activity);
			if (obj instanceof AbsListView) {
				((AbsListView) obj)
						.setOnItemLongClickListener(new EventListener(activity)
								.itemLongClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setViewSelectListener(Activity activity, Field field,
			String select, String noSelect) {
		try {
			Object obj = field.get(activity);
			if (obj instanceof View) {
				((AbsListView) obj)
						.setOnItemSelectedListener(new EventListener(activity)
								.select(select).noSelect(noSelect));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
