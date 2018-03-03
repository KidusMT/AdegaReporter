package com.gmail.kidusmt.adegareporter.ui.post;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gmail.kidusmt.adegareporter.R;
import com.gmail.kidusmt.adegareporter.base.view.BaseActivity;
import com.gmail.kidusmt.adegareporter.data.Accident;
import com.gmail.kidusmt.adegareporter.ui.home.HomePresenter;
import com.gmail.kidusmt.adegareporter.util.App;
import com.gmail.kidusmt.adegareporter.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.objectbox.Box;

import static android.Manifest.permission.CAMERA;

public class PostActivity extends BaseActivity implements PostContract.View{

    Box<Accident> accidentBox = App.boxStore.boxFor(Accident.class);

    PostContract.Presenter presenter;

    Uri picUri;
    Calendar myCalendar;
    ImageView post_product_img;

    Button btn_save;
    EditText recordDate, recordLocation, recordDescription, recordPlateNumber;
    public DatePickerDialog.OnDateSetListener date;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();

    private final static int ALL_PERMISSIONS_RESULT = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        presenter = new PostPresenter(this);

        //hides the keyboard till the user selects to an edit text
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.post_collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setTitle("");

        post_product_img = findViewById(R.id.post_product_img);

//        if (ContextCompat.checkSelfPermission(PostActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //TODO have to insert the imageView  I want to display the image on and disable it
//            ActivityCompat.requestPermissions(PostActivity.this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
//        }else{
            //TODO have to insert the imageView and enable it
//        }

        myCalendar = Calendar.getInstance();

        recordDate = findViewById(R.id.record_date_picker);
        recordLocation = findViewById(R.id.record_location);
        recordDescription = findViewById(R.id.record_description);
        recordPlateNumber = findViewById(R.id.record_plate_number);

        btn_save = findViewById(R.id.btn_record);

        date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        recordDate.setOnClickListener(v -> new DatePickerDialog(PostActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        FloatingActionButton fab_pic = findViewById(R.id.fab_pick_image);
        fab_pic.setOnClickListener(view -> startActivityForResult(getImageChooserIntent(), Constants.SELECT_PHOTO));

        btn_save.setOnClickListener(view -> {

            Accident accident_temp = new Accident();

            //handles the empty strings not to be inserted
            if (!TextUtils.isEmpty(recordDate.getText()) &&
                    !TextUtils.isEmpty(recordLocation.getText()) &&
                    !TextUtils.isEmpty(recordPlateNumber.getText()) &&
                    !TextUtils.isEmpty(recordDescription.getText())) {

                accident_temp.setLocation(recordLocation.getText().toString().trim());
                accident_temp.setDate(recordDate.getText().toString().trim());
                accident_temp.setDescription(recordDescription.getText().toString().trim());
                accident_temp.setImgPath(getRealPathFromURI(picUri).toString());
                accident_temp.setPlate(recordPlateNumber.getText().toString().trim());

                //TODO show progress bar here

                //call the saveRecord method
                presenter.onSaveClicked(accident_temp);

                toast("save successful");
            }
        });

        permissions.add(CAMERA);
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        ALL_PERMISSIONS_RESULT);
        }
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        recordDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * For choosing image for gallery only
     * @return
     */
    public Intent getImageChooserIntent(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }

    /**
     * For choosing image from multiple source
     * @return
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == Constants.SELECT_PHOTO){
            if(resultCode == RESULT_OK){
                try{
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    picUri = imageUri;
                    //TODO show progress bar here

                    //set image on the imageView
                    post_product_img.setImageBitmap(selectedImage);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }


//        Bitmap bitmap;
//        if (resultCode == Activity.RESULT_OK) {
//
//            if (getPickImageResultUri(data) != null) {
//                picUri = getPickImageResultUri(data);
//
//                try {
//                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
//                    myBitmap = rotateImageIfRequired(myBitmap, picUri);
//                    myBitmap = getResizedBitmap(myBitmap, 500);
//
//                    post_product_img.setImageBitmap(myBitmap);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//
//                bitmap = (Bitmap) data.getExtras().get("data");
//
//                myBitmap = bitmap;
//
//                post_product_img.setImageBitmap(myBitmap);
//
//            }
//        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("pic_uri", picUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        picUri = savedInstanceState.getParcelable("pic_uri");
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (hasPermission(perms)) {

                    } else {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    (dialog, which) -> {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                            //Log.d("API123", "permisionrejected " + permissionsRejected.size());

                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
        }

    }

    @Override
    public void closeAddAccident() {
        finish();
    }
}
