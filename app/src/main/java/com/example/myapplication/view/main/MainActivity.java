package com.example.myapplication.view.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.model.entities.Report;
import com.example.myapplication.model.entities.Tag;
import com.example.myapplication.view.main.MainViewModel;
import com.github.mhendred.face4j.model.Face;
import com.github.mhendred.face4j.model.Photo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView rv;
    private MyAdapter adapter;

    private Bitmap bitmap;
    private ImageView imageView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(
                    intent, "Select Picture"), PICK_IMAGE);
        });
        rv = findViewById(R.id.rvMainActivity);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        // specify an adapter (see also next example)


        viewModel = new MainViewModel();
        viewModel.getPhoto().observe(this, this::photoObserve);
        viewModel.getError().observe(this, this::errorObserve);
        detectionProgressDialog = new ProgressDialog(this);

    }
    private void errorObserve(Throwable throwable) {
        throwable.toString();
    }
    private void photoObserve(Report photo) {
        adapter = new MyAdapter(photo.getPhotos().get(0).getTags());
        rv.setAdapter(adapter);
        /*bitmap = drawFaceRectanglesOnBitmap(bitmap, photo.getPhotos().get(0).getTags());
        imageView.setImageBitmap(bitmap);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(bitmap);

                // Comment out for tutorial
                File f = new File(getCacheDir(), "cache");
                f.createNewFile();

                //Convert bitmap to byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();

                //write the bytes in file
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
                viewModel.detect(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }})
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
}
