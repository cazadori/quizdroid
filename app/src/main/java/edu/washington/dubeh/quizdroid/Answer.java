package edu.washington.dubeh.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Answer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Answer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Answer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CORRECT = "correct";
    private static final String TOTAL = "total";
    private static final String ANSWER_GIVEN = "answer given";
    private static final String CORRECT_ANSWER = "correct answer";

    // TODO: Rename and change types of parameters
    private int correct;
    private int total;
    private String answerGiven;
    private String correctAnswer;

    private OnFragmentInteractionListener mListener;

    public Answer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param correct Parameter 1.
     * @param total Parameter 2.
     * @return A new instance of fragment Answer.
     */
    // TODO: Rename and change types and number of parameters
    public static Answer newInstance(int correct, int total, String answerGiven, String correctAnswer) {
        Answer fragment = new Answer();
        Bundle args = new Bundle();
        args.putInt(CORRECT, correct);
        args.putInt(TOTAL, total);
        args.putString(ANSWER_GIVEN, answerGiven);
        args.putString(CORRECT_ANSWER, correctAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correct = getArguments().getInt(CORRECT);
            total = getArguments().getInt(TOTAL);
            answerGiven = getArguments().getString(ANSWER_GIVEN);
            correctAnswer = getArguments().getString(CORRECT_ANSWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        TextView correctAnswers = (TextView) view.findViewById(R.id.correctAnswers);
        TextView tvAnswerGiven = (TextView) view.findViewById(R.id.answerGiven);
        TextView tvCorrectAnswer = (TextView) view.findViewById(R.id.correctAnswer);
        tvAnswerGiven.setText("Answer Given: " + answerGiven);
        tvCorrectAnswer.setText("Correct Answer: " + correctAnswer);
        correctAnswers.setText("You have " + correct + " out of " + total + " correct");

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
