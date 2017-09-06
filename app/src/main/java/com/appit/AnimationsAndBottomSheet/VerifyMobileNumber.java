package com.appit.AnimationsAndBottomSheet;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyMobileNumber extends AppCompatActivity implements View.OnClickListener {

    EditText countryCode,phoneNumber;
    Context context;
    String myPhoneNum;
    TextView next;
    String locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mobile_number);


        context=this;
        countryCode=(EditText)findViewById(R.id.country_code_id);
        phoneNumber=(EditText)findViewById(R.id.phone_num_id);
        next=(TextView)findViewById(R.id.tv_next_id);

        next.setOnClickListener(this);

        countryCode.setText("+"+getCountryDialCode());
        phoneNumber.setText(" "+getMyPhoneNO());

    }
    public String getCountryDialCode(){

      /*  String countryId = null;
        String countryDialCode = null;

        TelephonyManager telephonyMngr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);

        countryId = telephonyMngr.getSimCountryIso().toUpperCase();
        countryId = telephonyMngr.getNetworkCountryIso().toUpperCase();
*/
        String countryDialCode = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        } else {
            locale = context.getResources().getConfiguration().locale.getCountry();
        }
        String[] arrCountryCode=this.getResources().getStringArray(R.array.DialingCountryCode);
        for(int i=0; i<arrCountryCode.length; i++){
            String[] arrDial = arrCountryCode[i].split(",");
            if(arrDial[1].trim().equals(locale.trim())){
                countryDialCode = arrDial[0];
                break;
            }
        }
        return countryDialCode;
    }


    private String getMyPhoneNO() {

        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_PHONE_STATE") == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            myPhoneNum = tMgr.getLine1Number();
            Log.e("Tag", "PHONE NUMBER = " + myPhoneNum);
        } else {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        return myPhoneNum;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(VerifyMobileNumber.this,EnterCodeActivity.class));
    }
}
