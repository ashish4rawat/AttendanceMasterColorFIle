package com.technialrj.attendancemaster;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class EnterSubjects extends Fragment {


        private LinearLayout linearLayout ;
        FloatingActionButton fab;
        Button saveButton;
        public int editTextCount = 0;
        int errorFlag = 0;



        public EnterSubjects() {

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_enter_subjects, container, false);

                getActivity().setTitle("Subjects");


                setHasOptionsMenu(true);
                linearLayout = view.findViewById(R.id.subjectNameLayout);
                saveButton = view.findViewById(R.id.saveButton);
                fab = view.findViewById(R.id.addSubject);


                addEditText();



                fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                addEditText();

                        }
                });

                saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                errorFlag = 0;

                                for(int i  = 0 ;i< linearLayout.getChildCount() ;i++){
                                        if(linearLayout.getChildAt(i) instanceof EditText) {
                                                EditText view = (EditText)linearLayout.getChildAt(i);

                                                if(view.getText().toString().trim().equals("")){
                                                        view.setError("Subject Name Can't be Empty");

                                                        errorFlag = 1; // Some Errors in the subjects
                                                        break;
                                                }


                                        }



                                }


                                if(errorFlag == 0){
                                        Toast.makeText(getActivity(),"Successfully Saved Subjects",Toast.LENGTH_SHORT).show();
                                        // Put the subjects saving code here

                                        Intent intent = new Intent(getContext(),EnterTimeTable.class);
                                        startActivity(intent);

                                }

                        }
                });





                return view;
        }




        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

                MenuInflater findMenuItems = getActivity().getMenuInflater();
                findMenuItems.inflate(R.menu.enter_tt_menu, menu);

                super.onCreateOptionsMenu(menu, inflater);

        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                        case R.id.addSubject:
                                addEditText();
                                break;

                        case R.id.deleteSubject:

                                deleteLastEditText();

                                break;

                }
                return super.onOptionsItemSelected(item);
        }

        private void deleteLastEditText() {

                int x = linearLayout.getChildCount();

                if (x > 0) {
                        linearLayout.removeViewAt(x-1);
                        --editTextCount;
                }else{
                        Toast.makeText(getActivity(),"No Subject to Delete" , Toast.LENGTH_SHORT).show();
                }





        }


        private void addEditText() {

                int subjectNum = editTextCount+1;

                EditText subjectName = new EditText(getActivity());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(40,20,40,20);


                subjectName.setPadding(20,25,20,25);
                subjectName.setTextSize(20);
                subjectName.setHint("Enter Subject "+ subjectNum);
                subjectName.setLayoutParams(layoutParams);
                subjectName.setId(editTextCount);
                subjectName.setBackgroundResource(R.drawable.add_subject_back);
                Log.i("InfoText",editTextCount+" ");

                ++editTextCount;


                linearLayout.addView(subjectName);

        }

}


