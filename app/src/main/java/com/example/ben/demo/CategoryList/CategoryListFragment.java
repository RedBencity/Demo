package com.example.ben.demo.CategoryList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ben.demo.R;


/**
 * @Description 图书分类左栏
 * @author hongbencheng
 * @version 1.0.0 2015-12-8
 * @reviewer
 */
public class CategoryListFragment extends Fragment {
	private boolean[] categoryCheck;
	private ListView category_list;
	private Callbacks callbacks;
	private Activity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_category_list, null);
		category_list = (ListView) view.findViewById(R.id.category_list);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		String[] category = new String[] { "潮流女装", "时尚男装", "数码装备", "家用电器", "手机数码", "电脑办公", "运动户外", "居家生活", "食品生鲜", "个户化妆", "珠宝饰品", "玩具乐器", "箱包配件", "日用百货", "家具材建" };
		categoryCheck = new boolean[category.length];
		CategoryAdapter categoryAdapter = new CategoryAdapter(category);
		category_list.setAdapter(categoryAdapter);
	}

	public interface Callbacks {
		public void onItemSelected(int position, String category);
	}

	class CategoryAdapter extends BaseAdapter {
		private String[] categoryStrings;

		public CategoryAdapter(String[] categoryStrings) {
			this.categoryStrings = categoryStrings;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return categoryStrings.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return categoryStrings[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.category_list_item, null);
			} else {

			}
			callbacks = (Callbacks) activity;
			final TextView categoryText;
			categoryText = (TextView) convertView.findViewById(R.id.category_text);
			categoryText.setText(getItem(position).toString());
			if (categoryCheck[position]) {
				categoryText.setTextColor(getResources().getColor(R.color.head_bg_green));
				notifyDataSetChanged();
			} else {
				categoryText.setTextColor(getResources().getColor(R.color.black));
				notifyDataSetChanged();
			}

			categoryText.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					for (int i = 0; i < categoryStrings.length; i++) {
						if (i != position) {
							categoryCheck[i] = false;
						} else if (i == position) {
							categoryCheck[i] = true;

							callbacks.onItemSelected(position, categoryText.getText().toString());

							notifyDataSetChanged();
						}
					}

				}
			});
			return convertView;

		}

	}

}
