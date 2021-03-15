package fr.tvbarthel.lib.blurdialogfragment.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SampleActivity extends Activity implements View.OnClickListener {

    /**
     * Seek bar used to change the blur radius.
     */
    SeekBar mBlurRadiusSeekbar;

    /**
     * TextView used to display the current blur radius.
     */
    TextView mBlurRadiusTextView;

    /**
     * Prefix used to explain blur radius.
     */
    String mBlurPrefix;

    /**
     * Seek bar used to change the down scale factor.
     */
    SeekBar mDownScaleFactorSeekbar;

    /**
     * TextView used to display the current down scale factor.
     */
    TextView mDownScaleFactorTextView;

    /**
     * Checkbox used to enable or disable debug mode.
     */
    CheckBox mDebugMode;

    /**
     * Prefix used to explain down scale factor.
     */
    String mDownScalePrefix;

    /**
     * Checkbox used to enable / disable dimming effect.
     */
    private CheckBox mDimmingEnable;

    /**
     * View used to change blur background color.
     */
    private View cbWhite, cbGrey, cbYellow, cbRed, cbOrange, cbBlue, cbGreen, cbPurple;

    /**
     * Int used for blur background color
     */
    private int mColor = Color.TRANSPARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        findViewById(R.id.button).setOnClickListener(this);

        cbWhite = findViewById(R.id.cbWhite);
        cbGrey = findViewById(R.id.cbGrey);
        cbYellow = findViewById(R.id.cbYellow);
        cbRed = findViewById(R.id.cbRed);
        cbOrange = findViewById(R.id.cbOrange);
        cbBlue = findViewById(R.id.cbBlue);
        cbGreen = findViewById(R.id.cbGreen);
        cbPurple = findViewById(R.id.cbPurple);

        cbWhite.setOnClickListener(this);
        cbGrey.setOnClickListener(this);
        cbYellow.setOnClickListener(this);
        cbRed.setOnClickListener(this);
        cbOrange.setOnClickListener(this);
        cbBlue.setOnClickListener(this);
        cbGreen.setOnClickListener(this);
        cbPurple.setOnClickListener(this);

        mBlurRadiusTextView = ((TextView) findViewById(R.id.blurRadius));
        mBlurRadiusSeekbar = ((SeekBar) findViewById(R.id.blurRadiusSeekbar));
        mDownScaleFactorTextView = ((TextView) findViewById(R.id.downScalefactor));
        mDownScaleFactorSeekbar = ((SeekBar) findViewById(R.id.downScaleFactorSeekbar));
        mDebugMode = ((CheckBox) findViewById(R.id.debugMode));
        mDimmingEnable = ((CheckBox) findViewById(R.id.dimmingEnable));

        setUpView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.actions_fullscreen) {
            startActivity(new Intent(this, SampleFullScreenActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.bg_color_state);
        drawable.setStroke(2, ContextCompat.getColor(this, R.color.black));
        switch (v.getId()) {
            case R.id.button:
                SampleDialogFragment fragment
                        = SampleDialogFragment.newInstance(
                        mBlurRadiusSeekbar.getProgress() + 1,
                        mColor,
                        (mDownScaleFactorSeekbar.getProgress() / 10f) + 2,
                        mDimmingEnable.isChecked(),
                        mDebugMode.isChecked()
                );
                fragment.show(getFragmentManager(), "blur_sample");
                break;
            case R.id.cbWhite:
                setBlurBackgroundColor(android.R.color.transparent);
                drawable.setColor(mColor);
                cbWhite.setBackground(drawable);
                break;
            case R.id.cbGrey:
                setBlurBackgroundColor(R.color.transparent_grey);
                drawable.setColor(mColor);
                cbGrey.setBackground(drawable);
                break;
            case R.id.cbYellow:
                setBlurBackgroundColor(R.color.transparent_yellow);
                drawable.setColor(mColor);
                cbYellow.setBackground(drawable);
                break;
            case R.id.cbRed:
                setBlurBackgroundColor(R.color.transparent_red);
                drawable.setColor(mColor);
                cbRed.setBackground(drawable);
                break;
            case R.id.cbOrange:
                setBlurBackgroundColor(R.color.transparent_orange);
                drawable.setColor(mColor);
                cbOrange.setBackground(drawable);
                break;
            case R.id.cbBlue:
                setBlurBackgroundColor(R.color.transparent_cyan);
                drawable.setColor(mColor);
                cbBlue.setBackground(drawable);
                break;
            case R.id.cbGreen:
                setBlurBackgroundColor(R.color.transparent_light_green);
                drawable.setColor(mColor);
                cbGreen.setBackground(drawable);
                break;
            case R.id.cbPurple:
                setBlurBackgroundColor(R.color.transparent_purple);
                drawable.setColor(mColor);
                cbPurple.setBackground(drawable);
                break;
            default:
                break;
        }
    }

    private void setBlurBackgroundColor(int color) {
        mColor = ContextCompat.getColor(this, color);

        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.bg_color_state);
        drawable.setStroke(2, ContextCompat.getColor(this, R.color.black));
        drawable.setColor(ContextCompat.getColor(this, android.R.color.transparent));

        cbWhite.setBackground(drawable);
        cbGrey.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_grey));
        cbYellow.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_yellow));
        cbRed.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_red));
        cbOrange.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_orange));
        cbBlue.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_cyan));
        cbGreen.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_light_green));
        cbPurple.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent_purple));
    }

    /**
     * Set up widgets.
     */
    private void setUpView() {

        mBlurRadiusSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurRadiusTextView.setText(mBlurPrefix + (progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mDownScaleFactorSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDownScaleFactorTextView.setText(mDownScalePrefix + (progress / 10f + 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBlurPrefix = getString(R.string.activity_sample_blur_radius);
        mDownScalePrefix = getString(R.string.activity_sample_down_scale_factor);

        //set default blur radius to 8.
        mBlurRadiusSeekbar.setProgress(7);
        mDownScaleFactorSeekbar.setProgress(20);
    }
}
