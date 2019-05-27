package fr.free.nrw.commons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import fr.free.nrw.commons.contributions.ContributionController;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.profile.ProfilePagerAdapter;
import fr.free.nrw.commons.theme.NavigationBaseActivity;

public class TestActivity extends NavigationBaseActivity {
    private FragmentManager supportFragmentManager;

    @Inject
    ContributionController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("Profile");

    }

    public static void startYourself(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.handleActivityResult(this, requestCode, resultCode, data);
    }
}