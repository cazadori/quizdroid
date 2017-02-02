package edu.washington.dubeh.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizQuestion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizQuestion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUESTION = "question";
    private static final String ANSWERS = "answers";

    // TODO: Rename and change types of parameters
    private String question;
    private String[] answers;
    private Overview overview;
    private String answer;

    private OnFragmentInteractionListener mListener;

    public QuizQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter 1.
     * @param answers Parameter 2.
     * @return A new instance of fragment QuizQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizQuestion newInstance(String question, String[] answers) {
        QuizQuestion fragment = new QuizQuestion();
        Bundle args = new Bundle();
        args.putString(QUESTION, question);
        args.putStringArray(ANSWERS, answers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            answers = getArguments().getStringArray(ANSWERS);
            overview = (Overview)getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        TextView questionText = (TextView) view.findViewById(R.id.questionText);
        questionText.setText(question);
        RadioButton answer1 = (RadioButton) view.findViewById(R.id.answer1);
        answer1.setText(answers[0]);
        RadioButton answer2 = (RadioButton) view.findViewById(R.id.answer2);
        answer2.setText(answers[1]);
        RadioButton answer3 = (RadioButton) view.findViewById(R.id.answer3);
        answer3.setText(answers[2]);
        RadioButton answer4 = (RadioButton) view.findViewById(R.id.answer4);
        answer4.setText(answers[3]);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioAnswers);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if(id != -1) {
                    RadioButton checkedAnswer = (RadioButton) group.findViewById(id);
                    overview.findViewById(R.id.begin).setEnabled(true);
                    overview.findViewById(R.id.begin).setBackgroundColor(0xFF00FF00);
                    answer = answers[group.indexOfChild(checkedAnswer)];
                }
            }
        });

        return view;
    }

    public String getAnswer() {
        return answer;
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
