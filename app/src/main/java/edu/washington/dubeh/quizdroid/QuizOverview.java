package edu.washington.dubeh.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizOverview.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizOverview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String DESCRIPTION = "description";
    private static final String QUESTION_COUNT = "question count";

    // TODO: Rename and change types of parameters
    private String description;
    private int questionCount;
    private Overview overview;

    private OnFragmentInteractionListener mListener;

    public QuizOverview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param description Parameter 1.
     * @param questionCount Parameter 2.
     * @return A new instance of fragment QuizOverview.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizOverview newInstance(String description, int questionCount) {
        QuizOverview fragment = new QuizOverview();
        Bundle args = new Bundle();
        args.putString(DESCRIPTION, description);
        args.putInt(QUESTION_COUNT, questionCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            description = getArguments().getString(DESCRIPTION);
            questionCount = getArguments().getInt(QUESTION_COUNT);
            overview = (Overview)getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_overview, container, false);

        TextView tvDescription = (TextView) view.findViewById(R.id.description);
        TextView tvQuestionCount = (TextView) view.findViewById(R.id.questions);

        tvDescription.setText(description);
        tvQuestionCount.setText("There are " + questionCount + " questions");
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}