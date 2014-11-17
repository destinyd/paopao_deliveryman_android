/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.realityandapp.paopao_official_deliveryman.views.im;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.realityandapp.paopao_official_deliveryman.IMConstant;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.im.IMUser;
import com.realityandapp.paopao_official_deliveryman.views.adapter.im.ContactAdapter;
import com.realityandapp.paopao_official_deliveryman.widget.im.Sidebar;

import java.util.*;
import java.util.Map.Entry;

public class PickContactNoCheckboxActivity extends BaseActivity {

	private ListView listView;
	private Sidebar sidebar;
	protected ContactAdapter contactAdapter;
	private List<IMUser> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.im_activity_pick_contact_no_checkbox);
		listView = (ListView) findViewById(R.id.list);
		sidebar = (Sidebar) findViewById(R.id.sidebar);
		sidebar.setListView(listView);
		contactList = new ArrayList<IMUser>();
		// 获取设置contactlist
		getContactList();
		// 设置adapter
		contactAdapter = new ContactAdapter(this, R.layout.im_row_contact, contactList, sidebar);
		listView.setAdapter(contactAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onListItemClick(position);
			}
		});

	}

	protected void onListItemClick(int position) {
		if (position != 0) {
			setResult(RESULT_OK, new Intent().putExtra("username", contactAdapter.getItem(position)
					.getUsername()));
			finish();
		}
	}

	public void back(View view) {
		finish();
	}

	private void getContactList() {
		contactList.clear();
		Map<String, IMUser> users = PaopaoOfficialDeliverymanApplication.getInstance().getContactList();
		Iterator<Entry<String, IMUser>> iterator = users.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, IMUser> entry = iterator.next();
			if (!entry.getKey().equals(IMConstant.NEW_FRIENDS_USERNAME) && !entry.getKey().equals(IMConstant.GROUP_USERNAME))
				contactList.add(entry.getValue());
		}
		// 排序
		Collections.sort(contactList, new Comparator<IMUser>() {

            @Override
            public int compare(IMUser lhs, IMUser rhs) {
                return lhs.getUsername().compareTo(rhs.getUsername());
            }
        });
	}

}
