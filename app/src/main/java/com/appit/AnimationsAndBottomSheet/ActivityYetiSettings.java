package com.appit.AnimationsAndBottomSheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityYetiSettings extends AppCompatActivity {

    ConstraintLayout panicLayout;
    String value = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeti_settings);

        panicLayout = (ConstraintLayout) findViewById(R.id.panic_layout);
        panicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityYetiSettings.this);
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setTitle("Are you sure?");
                alertDialogBuilder.setMessage("We will delete media that was most recently sent.");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityYetiSettings.this);
                                alertDialog.setCancelable(false);


                                final LayoutInflater inflater = ActivityYetiSettings.this.getLayoutInflater();
                                final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                                alertDialog.setView(dialogView);
                                alertDialog.setTitle("Confirmation");
                                alertDialog.setMessage("Type the word DELETE to confirm.");

                                final EditText input = dialogView.findViewById(R.id.edit1);

                               /*final EditText input = new EditText(ActivityYetiSettings.this);
                                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.MATCH_PARENT);
                                        input.setLayoutParams(lp);
                                        alertDialog.setView(input);*/

                                alertDialog.setPositiveButton("YES", null);

                                alertDialog.setNegativeButton("NO",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog b = alertDialog.create();
                                b.show();

                                Button button = ((AlertDialog) b).getButton(AlertDialog.BUTTON_POSITIVE);

                                Log.v("Tag", "button working");
                                button.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {
                                        value = input.getText().toString();

                                        if (value.length()>0 && value.equals("DELETE")) {

                                            Log.v("Tag", "delete successfully");
                                            Toast.makeText(getApplicationContext(), "File deleted ", Toast.LENGTH_SHORT).show();
                                            b.cancel();

                                        } else {
                                            //to remove error value
                                            input.setText(null);

                                            Log.v("Tag", "Invalid word");
                                            Toast.makeText(getApplicationContext(), "Type the word DELETE ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}
