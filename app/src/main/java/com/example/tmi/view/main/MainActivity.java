package com.example.tmi.view.main;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.tmi.R;
import com.example.tmi.model.entities.Report;
import com.example.tmi.model.entities.Tag;
import com.example.tmi.view.FirstMainFragment;
import com.example.tmi.view.main.LoadDialogFragment;
import com.example.tmi.view.main.MainViewModel;
import com.example.tmi.view.main.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FirstMainFragment.OnFragmentInteractionListener {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private MainViewModel viewModel;

    private ProgressDialog detectionProgressDialog;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);

        viewModel = new MainViewModel();
        viewModel.getLoad().observe(this, this::loadObserve);
        viewModel.getSequence().observe(this, reports -> {
            Fragment fragment = new SecondMainFragment(viewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentPosition, fragment)
                    .commit();
        });
        detectionProgressDialog = new ProgressDialog(this);

        Fragment fragment = new FirstMainFragment(viewModel);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentPosition, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home) {
            // Handle the camera action
        } else if(id == R.id.nav_gallery) {

        } else if(id == R.id.nav_slideshow) {

        } else if(id == R.id.nav_tools) {

        } else if(id == R.id.nav_share) {

        } else if(id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            viewModel.processVideo(this, videoUri, 1000);
        }
    }

    private void sequenceObserver(List<Report> reports) {
        /*list = reports;
        showReport(list.get(i++));*/
    }
    private void showReport(Report report) {
        /*Glide.with(this)
                .load(report.getPhotos().get(0).getUrl())
                .animate(R.anim.abc_fade_in)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(imageView);
        adapter = new MyAdapter(report.getPhotos().get(0).getTags());
        rv.setAdapter(adapter);*/
    }
    private void loadObserve(Boolean aBoolean) {
        if (aBoolean) {
            dialogFragment = new LoadDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "1");
        } else
            dialogFragment.dismiss();
    }
    private void photoObserve(Report photo) {
        /*adapter = new MyAdapter(photo.getPhotos().get(0).getTags());
        rv.setAdapter(adapter);*/
        /*bitmap = drawFaceRectanglesOnBitmap(bitmap, photo.getPhotos().get(0).getTags());
        imageView.setImageBitmap(bitmap);*/
    }

    private void showError(Throwable message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message.getMessage())
                .setPositiveButton("OK", (dialog, id) -> {
                    dialog.dismiss();
                })
                .create().show();
    }

    private static Bitmap drawFaceRectanglesOnBitmap(
            Bitmap originalBitmap, List<Tag> tags) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        if (tags != null) {
            for (Tag tag : tags) {
                canvas.drawRect(
                        tag.getCenter().getX() - 3.0f,
                        tag.getCenter().getY() - 3.0f,
                        tag.getCenter().getX() + 3.0f,
                        tag.getCenter().getY() + 3.0f,
                        paint);
            }
        }
        return bitmap;
    }


    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override public void onVideo() {
        dispatchTakeVideoIntent();
    }
    @Override public void onFolder() {

    }
}
