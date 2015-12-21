package com.example.ben.demo.CategoryList;

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
 * @Description 图书分类右栏
 * @author hongbencheng
 * @version 1.0.0 2015-12-8
 * @reviewer
 */
public class SpecificCategoryFragment extends Fragment {
	private ListView specific_category;
	private String category = "";
	private String[] specificCategoryStrings;
	private SpecificCategoryAdapter specificCategoryAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_specific_category, null);
		specific_category = (ListView) view.findViewById(R.id.specific_category_list);
		specificCategoryStrings = new String[] { "潮流女装", "时尚男装", "数码装备", "家用电器", "手机数码", "电脑办公", "运动户外" };

		specificCategoryAdapter = new SpecificCategoryAdapter(specificCategoryStrings);
		specific_category.setAdapter(specificCategoryAdapter);
		return view;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		if (category.equals("潮流女装")) {
			specificCategoryStrings = new String[] { "潮流女装", "时尚男装", "数码装备", "家用电器", "手机数码", "电脑办公", "运动户外" };
		} else if(category.equals("时尚男装")){
			specificCategoryStrings = new String[] { "居家生活", "食品生鲜", "个户化妆", "珠宝饰品", "玩具乐器", "箱包配件", "日用百货", "家具材建" };
		}else{
			specificCategoryStrings = new String[] {category,"食品生鲜", "个户化妆", "珠宝饰品"};
		}
		specificCategoryAdapter.setCategoryStrings(specificCategoryStrings);
		specificCategoryAdapter.notifyDataSetChanged();
	}

	class SpecificCategoryAdapter extends BaseAdapter {
		private String[] categoryStrings;

		public SpecificCategoryAdapter(String[] categoryStrings) {
			this.categoryStrings = categoryStrings;
		}

		public String[] getCategoryStrings() {
			return categoryStrings;
		}

		public void setCategoryStrings(String[] categoryStrings) {
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
			TextView categoryText;
			categoryText = (TextView) convertView.findViewById(R.id.category_text);
			categoryText.setText(getItem(position).toString());

			categoryText.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			return convertView;

		}

	}
}
