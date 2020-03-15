package com.example.tp2;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditFragment extends Fragment {

    private EditText editText;
    private EditText editTextBinded;
    private TextWatcher editTextBindedTextWatcher;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            editTextBinded.removeTextChangedListener(editTextBindedTextWatcher);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            editTextBinded.setText(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            editTextBinded.addTextChangedListener(editTextBindedTextWatcher);
        }
    };

    public EditFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment, container, false);
        editText = view.findViewById(R.id.fragment_edit_text);

        editText.addTextChangedListener(textWatcher);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setEditTextText(String text){
        this.editText.setText(text);
    }

    public void setEditTextBinded(EditText editText){
        this.editTextBinded = editText;
    }

    public void setEditTextBindedTextWatcher(TextWatcher textWatcher){
        this.editTextBindedTextWatcher = textWatcher;
    }

    public TextWatcher getTextWatcher(){
        return this.textWatcher;
    }

    public EditText getEditText() {
        return editText;
    }

    public static EditFragment newInstance(EditText editTextBinded, TextWatcher textWatcher){
        EditFragment fragment = new EditFragment();
        fragment.setEditTextBinded(editTextBinded);
        fragment.setEditTextBindedTextWatcher(textWatcher);
        return fragment;
    }

}
