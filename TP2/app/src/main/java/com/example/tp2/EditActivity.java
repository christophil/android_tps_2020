package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditFragment editFragment;
    private EditText editText;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            editFragment.getEditText().removeTextChangedListener(editFragment.getTextWatcher());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            editFragment.setEditTextText(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            editFragment.getEditText().addTextChangedListener(editFragment.getTextWatcher());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = findViewById(R.id.activity_edit_text);

        editFragment = EditFragment.newInstance(editText, textWatcher);

        editText.addTextChangedListener(textWatcher);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent(this, ActivityTwo.class);
                intent.putExtra("calculAndResult", "");
                this.startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2 = new Intent(this, MainActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.menu3:
                Intent intent3 = new Intent(this, EditActivity.class);
                this.startActivity(intent3);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
    }
}
