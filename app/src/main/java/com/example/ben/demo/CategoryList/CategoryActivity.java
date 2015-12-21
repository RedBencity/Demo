package com.example.ben.demo.CategoryList;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.ben.demo.R;

/**
 * @Description 图书分类
 * @author hongbencheng
 * @version 1.0.0 2015-12-8
 * @reviewer
 */

public class CategoryActivity extends Activity implements CategoryListFragment.Callbacks {
	private SpecificCategoryFragment specificCategoryFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_category);
		super.onCreate(savedInstanceState);
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		specificCategoryFragment = new SpecificCategoryFragment();
		getFragmentManager().beginTransaction().add(R.id.category_page, new CategoryListFragment()).commit();
		getFragmentManager().beginTransaction().add(R.id.category_content, specificCategoryFragment).commit();

	}


	protected void getData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onItemSelected(int position, String category) {
		// TODO Auto-generated method stub
		if (specificCategoryFragment == null) {
			specificCategoryFragment = new SpecificCategoryFragment();
		}
		Log.i("category", category);
		specificCategoryFragment.setCategory(category);

	}

}
