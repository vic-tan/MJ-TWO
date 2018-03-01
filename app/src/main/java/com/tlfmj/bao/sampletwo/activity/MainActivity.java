package com.tlfmj.bao.sampletwo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tlfmj.bao.animation.GuillotineAnimation;
import com.tlfmj.bao.sampletwo.R;
import com.tlfmj.bao.sampletwo.navigator.FragmentNavigator;
import com.tlfmj.bao.sampletwo.navigator.HomeNavigatorFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final long RIPPLE_DURATION = 250;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content_hamburger)
    View contentHamburger;
    GuillotineAnimation build;
    View guillotineMenu;
    private FragmentNavigator mNavigator;
    TextView one, two, three, four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        ButterKnife.bind(this);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        guillotineMenu.findViewById(R.id.one).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.two).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.three).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.four).setOnClickListener(this);
        one = (TextView) guillotineMenu.findViewById(R.id.one_title);
        two = (TextView) guillotineMenu.findViewById(R.id.two_title);
        three = (TextView) guillotineMenu.findViewById(R.id.three_title);
        four = (TextView) guillotineMenu.findViewById(R.id.four_title);
        root.addView(guillotineMenu);
        chageBtn(one);
        build = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        mNavigator = new FragmentNavigator(this.getSupportFragmentManager(), new HomeNavigatorFragmentAdapter(), R.id.container);
        mNavigator.setDefaultPosition(0);
        setCurrentTab(0);
    }


    @Override
    public void onClick(View v) {
        build.close();
        switch (v.getId()) {
            case R.id.one:
                setCurrentTab(0);
                title.setText(one.getText());
                chageBtn(one);
                break;
            case R.id.two:
                setCurrentTab(1);
                title.setText(two.getText());
                chageBtn(two);
                break;
            case R.id.three:
                setCurrentTab(2);
                title.setText(three.getText());
                chageBtn(three);
                break;
            case R.id.four:
                setCurrentTab(3);
                title.setText(four.getText());
                chageBtn(four);
                break;
            default:
                break;
        }
    }

    public void chageBtn(TextView name) {
        one.setTextColor(getColor(R.color.white));
        two.setTextColor(getColor(R.color.white));
        three.setTextColor(getColor(R.color.white));
        four.setTextColor(getColor(R.color.white));
        name.setTextColor(getColor(R.color.selected_item_color));
    }

    public void setCurrentTab(int position) {
        mNavigator.showFragment(position);
    }
}
