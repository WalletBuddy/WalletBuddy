package com.dalin.mywallet.walletbuddy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;


import com.dalin.mywallet.walletbuddy.data.BudgetData;
import com.dalin.mywallet.walletbuddy.adapter.BudgetDataAdapter;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseACL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudgetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudgetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class BudgetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private BudgetDataAdapter budgetAdapter;
    boolean isUpdate;
    BudgetData saveData;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetFragment newInstance(String param1, String param2) {
        BudgetFragment fragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_budget, container, false);
        final EditText budgetNumber = (EditText)view.findViewById(R.id.initialBudget);

        Button buttonBudget = (Button)view.findViewById(R.id.saveBudgetButton);
        buttonBudget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final double budgetTemp = Double.parseDouble(budgetNumber.getText().toString());
                String key = ParseUser.getCurrentUser().getObjectId();

                ParseQuery<BudgetData> query = ParseQuery.getQuery(BudgetData.class);
                query.whereEqualTo("user", ParseUser.getCurrentUser());
                query.getFirstInBackground(new GetCallback<BudgetData>() {
                    @Override
                    public void done(BudgetData budgetData, ParseException e) {
                        //user logins with budget setup
                        if (budgetData != null) {
                            budgetData.setBudget(budgetTemp);
                            budgetData.setRemaining(budgetTemp);
                            budgetData.saveInBackground();
                        //user logins with no budget setup
                        } else if (budgetData == null) {

                            BudgetData data = new BudgetData();
                            data.setACL(new ParseACL(ParseUser.getCurrentUser()));
                            data.setUser(ParseUser.getCurrentUser());
                            data.setBudget(budgetTemp);
                            data.setRemaining(budgetTemp);
                            data.saveInBackground();
                        }
                    }
                });

            }

        });
        return view;
        //return inflater.inflate(R.layout.fragment_budget, container, false);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
