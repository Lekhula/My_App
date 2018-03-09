package com.pabi.my_app;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Bookings_Fragment extends AppCompatActivity{

    private int mBook = BOOK_UNKNOWN;

    public static final  int BOOK_UNKNOWN= 0;
    public static final int BOOK_PARTY = 1;
    public static final int BOOK_WEDDING = 2;
    public static final int BOOK_CLUB = 3;

    private boolean mUserHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mUserHasChanged = true;
            return false;
        }
    };

    EditText nameEdt;
    EditText ageEdt;
    Spinner book_For;
    Button Btnbook;

    public static boolean validate(int bookDJ){
        if(bookDJ ==BOOK_UNKNOWN || bookDJ == BOOK_PARTY || bookDJ == BOOK_WEDDING || bookDJ == BOOK_CLUB){
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bookings_);

        nameEdt = (EditText)findViewById(R.id.name);
        ageEdt = (EditText)findViewById(R.id.age);
        book_For = (Spinner) findViewById(R.id.bookFor);
        Btnbook = (Button)findViewById(R.id.bookBtn);

        book_For.setOnTouchListener(mTouchListener);
//        setupBook_ForSpinner();

        Btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Booking succesful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupBook_ForSpinner(){

        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_book_options, android.R.layout.simple_spinner_item);

        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        book_For.setAdapter(statusSpinnerAdapter);

/**        book_For.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(select)){
                    if(select.equals(getString(R.string.party))){
                        mBook = BOOK_PARTY;
                    }else if(select.equals(getString(R.string.wedding))){
                        mBook = BOOK_WEDDING;
                    }else if(select.equals(getString(R.string.club))){
                        mBook = BOOK_CLUB;
                    }else {
                        mBook = BOOK_UNKNOWN;
                    }
                }
            }
            public void onNothingSelected(AdapterView<?>parent){
                mBook = BOOK_UNKNOWN;
            }

        });**/
    }


}
