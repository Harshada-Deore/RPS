package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class RulesActivity extends AppCompatActivity {
    private String welcome_text,rule1,rule2,rule3;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        TextSwitcher rules_switch = findViewById(R.id.rules_switch);
        initialiseRules();
        String[] rules = {welcome_text,rule1,rule2,rule3};
        rules_switch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView rules_text = new TextView(RulesActivity.this);
                rules_text.setGravity(Gravity.CENTER_HORIZONTAL);
                rules_text.setTextSize(32f);
                rules_text.setPadding(100,100,100,100);
                rules_text.setTextColor(Color.BLACK);
                return rules_text;
            }
        });
        rules_switch.setText(rules[index]);
        Animation rule_out = AnimationUtils.loadAnimation(
                this,android.R.anim.slide_out_right
        );
        Animation rule_in = AnimationUtils.loadAnimation(
                this,android.R.anim.slide_in_left
        );
        Button nextRule = findViewById(R.id.next_rule);
        Button prevRule = findViewById(R.id.previous_rule);
        nextRule.setOnClickListener(view -> {
            if(index + 1 < rules.length){
                index = index + 1;
            }else{
                index = 0;
            }
            rules_switch.setText(rules[index]);
            rules_switch.setAnimation(rule_in);
        });
        prevRule.setOnClickListener(view ->{
            if(index - 1 >= 0){
                index = index - 1;
            }
            else{
                index = rules.length - 1;
            }
            rules_switch.setText(rules[index]);
            rules_switch.setAnimation(rule_out);
        });
    }

    private void initialiseRules() {
        welcome_text = "Hello there challenger!! Before we begin the game lets go through some rules!!";
        rule1 = "Rock beats Scissors!!";
        rule2 = "Paper beats Rock!!";
        rule3 = "Scissors beats Paper!!";
    }
}