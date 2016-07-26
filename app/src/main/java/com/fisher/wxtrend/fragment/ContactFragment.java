package com.fisher.wxtrend.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.SortAdapter;
import com.fisher.wxtrend.base.BaseFragment;
import com.fisher.wxtrend.permission.EasyPermission;
import com.fisher.wxtrend.po.SortModel;
import com.fisher.wxtrend.ui.ClearEditTextView;
import com.fisher.wxtrend.ui.SideBarView;
import com.fisher.wxtrend.util.CharacterParser;
import com.fisher.wxtrend.util.ContactUtil;
import com.fisher.wxtrend.util.LogUtil;
import com.fisher.wxtrend.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/25/.
 */
public class ContactFragment extends BaseFragment implements EasyPermission.PermissionCallback {

    @BindView(R.id.filter_edit)
    ClearEditTextView mClearEditText;
    @BindView(R.id.sortlist)
    ListView sortListView;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidebar)
    SideBarView sideBar;

    private SortAdapter adapter;
    private Map<String, String> callRecords;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    private PinyinComparator pinyinComparator;

    private static final int RC_SMS_PERM = 122;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        checkPermission();
    }

    private void initData() {
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialog);
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBarView.OnTouchingLetterChangedListener() {

            @SuppressLint("NewApi")
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                // Toast.makeText(getApplication(),
                // ((SortModel)adapter.getItem(position)).getName(),
                // Toast.LENGTH_SHORT).show();
                String number = callRecords.get(((SortModel) adapter.getItem(position)).getName());
                Toast.makeText(getContext(), number, Toast.LENGTH_SHORT).show();
            }
        });

        new ConstactAsyncTask().execute(0);

    }

    private class ConstactAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... arg0) {
            int result = -1;
            callRecords = ContactUtil.getAllCallRecords(getContext());
            LogUtil.e(getActivity(), "size---->" + callRecords.size());
            result = 1;
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 1) {
                List<String> constact = new ArrayList<String>();
                for (Iterator<String> keys = callRecords.keySet().iterator(); keys.hasNext(); ) {
                    String key = keys.next();
                    constact.add(key);
                }
                String[] names = new String[]{};
                names = constact.toArray(names);
                SourceDateList = filledData(names);

                // 根据a-z进行排序源数据
                Collections.sort(SourceDateList, pinyinComparator);
                adapter = new SortAdapter(getContext(), SourceDateList);
                sortListView.setAdapter(adapter);

                mClearEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View arg0, boolean arg1) {
                        mClearEditText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

                    }
                });
                // 根据输入框输入值的改变来过滤搜索
                mClearEditText.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                        filterData(s.toString());
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EasyPermission.SETTINGS_REQ_CODE) {
            boolean hasReadSmsPermission = EasyPermission.hasPermissions(getContext(), Manifest.permission.READ_CONTACTS);
            String hasReadSmsPermissionText = getString(R.string.has_permission, hasReadSmsPermission);

            Toast.makeText(getContext(), hasReadSmsPermissionText, Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermission() {
        EasyPermission.with(this).addRequestCode(RC_SMS_PERM).permissions(Manifest.permission.READ_CONTACTS).rationale(getString(R.string.rationale_permission)).request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        initData();
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {

        // Handle negative button on click listener
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), R.string.settings_dialog_canceled, Toast.LENGTH_SHORT).show();
            }
        };

        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, getString(R.string.rationale_ask_again), R.string.setting, R.string.cancel, onClickListener, perms);
    }

}
