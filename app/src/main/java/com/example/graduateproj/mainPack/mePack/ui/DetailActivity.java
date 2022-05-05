package com.example.graduateproj.mainPack.mePack.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUI.RoundCornerButton;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.mePack.util.DetailStateUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class DetailActivity extends AppCompatActivity {

    private ImageView backIcon;
    private EditText editName, editSign, editPhone, editQQ, editInstitute;
    private RoundCornerButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        checkAllEditSavedBeforeOrNot();
        initEvents();
    }

    private void initViews() {
        backIcon = findViewById(R.id.edit_back);
        editName = findViewById(R.id.me_edit_detail_name_input);
        editSign = findViewById(R.id.me_edit_detail_sign_input);
        editPhone = findViewById(R.id.me_edit_detail_phone_input);
        editQQ = findViewById(R.id.me_edit_detail_qq_input);
        editInstitute = findViewById(R.id.me_edit_detail_institute_input);
        saveButton = findViewById(R.id.save_button);
    }

    private void checkAllEditSavedBeforeOrNot() {
        String localName = DetailStateUtil.getInstance(this).getLocalSelfNameOrDefault();
        if (!TextUtils.equals(localName, "")) {
            editName.setText(localName);
        }
        String localSign = DetailStateUtil.getInstance(this).getLocalSignOrDefault();
        if (!TextUtils.equals(localSign, "")) {
            editSign.setText(localSign);
        }
        String localPhone = DetailStateUtil.getInstance(this).getLocalPhoneNumberOrDefault();
        if (!TextUtils.equals(localPhone, "")) {
            editPhone.setText(localPhone);
        }
        String localQQ = DetailStateUtil.getInstance(this).getLocalQQOrDefault();
        if (!TextUtils.equals(localQQ, "")) {
            editQQ.setText(localQQ);
        }
        String localInstitute = DetailStateUtil.getInstance(this).getLocalInstituteOrDefault();
        if (!TextUtils.equals(localInstitute, "")) {
            editInstitute.setText(localInstitute);
        }
    }

    private void saveAllEdit() {
        if (!TextUtils.equals(editName.getText(), "")) {
            DetailStateUtil.getInstance(DetailActivity.this).saveSelfNameToLocal(editName.getText().toString());
        }
        if (!TextUtils.equals(editSign.getText(), "")) {
            DetailStateUtil.getInstance(DetailActivity.this).saveSignToLocal(editSign.getText().toString());
        }
        if (!TextUtils.equals(editPhone.getText(), "")) {
            DetailStateUtil.getInstance(DetailActivity.this).savePhoneNumberToLocal(editPhone.getText().toString());
        }
        if (!TextUtils.equals(editQQ.getText(), "")) {
            DetailStateUtil.getInstance(DetailActivity.this).saveQQToLocal(editQQ.getText().toString());
        }
        if (!TextUtils.equals(editInstitute.getText(), "")) {
            DetailStateUtil.getInstance(DetailActivity.this).saveInstituteToLocal(editInstitute.getText().toString());
        }
    }

    private void initEvents() {
        RxClickUtil.INSTANCE.clickEvent(backIcon, this)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        AppNavigator.INSTANCE.backToMainContentActivity(DetailActivity.this);
                    }
                });

        RxClickUtil.INSTANCE.clickEvent(saveButton, this)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        saveAllEdit();
                    }
                });
    }
}