package com.example.opeyemi.storytime.Activites;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.opeyemi.storytime.R;

import io.realm.Realm;

public class CharacterInfoActivity extends ActionBarActivity {
    protected  Realm realm;

    protected TextView physicalHeader;
    protected TextView personalityHeader;


    protected LinearLayout physicalContainer;
    protected LinearLayout personalityContainer;

    protected EditText characterDescriptionLabel;
    protected EditText characterAgeLabel;
    protected EditText characterStoryLabel;
    protected EditText characterHeightLabel;
    protected EditText charcterRaceLabel;

    protected EditText opennessLabel;
    protected EditText conscientiousnessLabel;
    protected EditText extraversionLabel;
    protected EditText agreeablenessLabel;
    protected EditText neuroticismLabel;

    protected Toolbar toolBar;


    protected boolean isPhysicalCollaspsed =false;
    protected boolean isPersonalityCollaspsed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

       toolBar=(Toolbar)findViewById(R.id.app_bar);
        toolBar.setTitle(AboutBookActivity.selectedCharacter.getName().toString());
        setSupportActionBar(toolBar);

        physicalHeader=(TextView)findViewById(R.id.PhysicalHeader);
        physicalHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhysicalCollaspsed) {
                    expand(physicalContainer);
                    isPhysicalCollaspsed = !isPhysicalCollaspsed;
                } else {
                    collapse(physicalContainer);
                    isPhysicalCollaspsed = !isPhysicalCollaspsed;
                }
            }
        });
        personalityHeader=(TextView)findViewById(R.id.personalityHeader);
        personalityHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPersonalityCollaspsed) {
                    expand(personalityContainer);
                   isPersonalityCollaspsed = !isPersonalityCollaspsed;
                } else {
                    collapse(personalityContainer);
                    isPersonalityCollaspsed = !isPersonalityCollaspsed;
                }
            }
        });


        //edit text used in character description.
        physicalContainer=(LinearLayout)findViewById(R.id.physicalContainer);
        personalityContainer=(LinearLayout)findViewById(R.id.personalityContainer);

        characterDescriptionLabel=(EditText)findViewById(R.id.characterDescriptionLabel);
        characterAgeLabel=(EditText)findViewById(R.id.characterAgeLabel);
        characterStoryLabel=(EditText)findViewById(R.id.characterStoryLabel);
        characterHeightLabel=(EditText)findViewById(R.id.characterHeightLabel);
        charcterRaceLabel=(EditText)findViewById(R.id.characterRaceLabel);
        opennessLabel=(EditText)findViewById(R.id.opennessLabel);
        conscientiousnessLabel=(EditText)findViewById(R.id.conscientiousnessLabel);
        extraversionLabel =(EditText)findViewById(R.id.extraversionLabel);
        agreeablenessLabel=(EditText)findViewById(R.id.agreeablenessLabel);
        neuroticismLabel=(EditText)findViewById(R.id.neuroticismLabel);

        //setting the text values of the edit text
        characterDescriptionLabel.setText(AboutBookActivity.selectedCharacter.getDescription());
        characterAgeLabel.setText(AboutBookActivity.selectedCharacter.getAge());
        characterStoryLabel.setText(AboutBookActivity.selectedCharacter.getStoryOfOrigin());
        characterHeightLabel.setText(AboutBookActivity.selectedCharacter.getHeight());
        charcterRaceLabel.setText(AboutBookActivity.selectedCharacter.getRace());
        opennessLabel.setText(AboutBookActivity.selectedCharacter.getOpenness());
        conscientiousnessLabel.setText(AboutBookActivity.selectedCharacter.getConscientiousness());
        extraversionLabel.setText(AboutBookActivity.selectedCharacter.getExtraversion());
        agreeablenessLabel.setText(AboutBookActivity.selectedCharacter.getAgreeableness());
        neuroticismLabel.setText(AboutBookActivity.selectedCharacter.getNeuroticism());

        realm=Realm.getInstance(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //saving the editted properties when the back button is pressed
        realm.beginTransaction();

        AboutBookActivity.selectedCharacter.setDescription(characterDescriptionLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setAge(characterAgeLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setStoryOfOrigin(characterStoryLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setHeight(characterHeightLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setRace(charcterRaceLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setOpenness(opennessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setConscientiousness(conscientiousnessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setExtraversion(extraversionLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setAgreeableness(agreeablenessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setNeuroticism(neuroticismLabel.getText().toString());

        realm.commitTransaction();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        //saving editted properties when the activity is paused.
        realm.beginTransaction();

        AboutBookActivity.selectedCharacter.setDescription(characterDescriptionLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setAge(characterAgeLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setStoryOfOrigin(characterStoryLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setHeight(characterHeightLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setRace(charcterRaceLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setOpenness(opennessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setConscientiousness(conscientiousnessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setExtraversion(extraversionLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setAgreeableness(agreeablenessLabel.getText().toString());
        AboutBookActivity.selectedCharacter.setNeuroticism(neuroticismLabel.getText().toString());

        realm.commitTransaction();
        super.onPause();
    }
    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
