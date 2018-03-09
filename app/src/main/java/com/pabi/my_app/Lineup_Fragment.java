package com.pabi.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Lineup_Fragment extends AppCompatActivity{
   /** public static Lineup_Fragment newInstance(){
        Lineup_Fragment fragment = new Lineup_Fragment();
        return fragment;
    }**/

    private EditText mSearchField;
   //private ImageButton mSearchBtn;
    private RecyclerView mResults;

    private DatabaseReference mDatabaseReference;
    FirebaseUser mFirebaseUser;
    ArrayList<String > nameList;
    ArrayList<String> descrList;
    ArrayList<String> imgList;
    SearchAdapter searchAdapter;

    //ImageView ImageMenu;

       @Override
    public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.fragment_lineup_);


           // mDatabaseReference = FirebaseDatabase.getInstance().getReference("Clubs");
           mDatabaseReference = FirebaseDatabase.getInstance().getReference();
           mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        mSearchField = (EditText)findViewById(R.id.edtSearch);
        //mSearchBtn = (ImageButton)view.findViewById(R.id.btnSearch);

        mResults = (RecyclerView)findViewById(R.id.recycler);
        mResults.setHasFixedSize(true);
        mResults.setLayoutManager(new LinearLayoutManager(this));
        mResults.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

           nameList = new ArrayList<>();
          descrList = new ArrayList<>();
          imgList = new ArrayList<>();


           mSearchField.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {

               }

               @Override
               public void afterTextChanged(Editable s) {
                   if(!s.toString().isEmpty()){
                       setAdapter(s.toString());

                   }else {

                       nameList.clear();;
                       descrList.clear();
                       imgList.clear();
                       mResults.removeAllViews();
                   }

               }
           });

        }



    private void setAdapter( final String searchedString) {
        mDatabaseReference.child("Clubs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nameList.clear();;
                descrList.clear();
                imgList.clear();
                mResults.removeAllViews();

                int counter = 0;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String uid = snapshot.getKey();
                    String name = snapshot.child("name").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String image = snapshot.child("image").getValue(String.class);

                    if(name.toLowerCase().contains(searchedString.toLowerCase())){
                        nameList.add(name);
                        descrList.add(description);
                        imgList.add(image);
                        counter++;

                    }else  if(name.toLowerCase().contains(searchedString.toLowerCase())){
                        nameList.add(name);
                        descrList.add(description);
                        imgList.add(image);
                        counter++;

                    }

                    if(counter == 15){
                        break;
                    }

                    searchAdapter = new SearchAdapter(Lineup_Fragment.this, nameList,descrList,imgList);
                    mResults.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }






   /** private void firebaseSearch(String searchText){

            Query firebaseSearchQuery = mDatabaseReference.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

            FirebaseRecyclerAdapter<Places, PlacesViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Places, PlacesViewHolder>(
                    Places.class,
                    R.layout.list_layout,
                    PlacesViewHolder.class,
                    firebaseSearchQuery
            ) {



                @Override
                protected void populateViewHolder(PlacesViewHolder viewHolder, Places model, int position) {

                    viewHolder.setDetails(getContext(), model.getName(),model.getImage(), model.getDescription());



                }
            };

            mResults.setAdapter(firebaseRecyclerAdapter);

        }

        public static class PlacesViewHolder extends  RecyclerView.ViewHolder{

            View mView;

            public PlacesViewHolder(View itemView) {
                super(itemView);

                mView = itemView;
            }
            public void setDetails(Context context,String placeName, String placeDesc, String placeImg){

                TextView place_name = (TextView)itemView.findViewById(R.id.club_name);
                TextView place_descr = (TextView)itemView.findViewById(R.id.club_descr);
                ImageView place_img = (ImageView)itemView.findViewById(R.id.club_Img);

                place_name.setText(placeName);
                place_descr.setText(placeDesc);

                Glide.with(context).load(placeImg).into(place_img);

            }
        }**/



}
