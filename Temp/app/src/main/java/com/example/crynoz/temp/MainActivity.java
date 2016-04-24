package com.example.crynoz.temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends Activity implements OnClickListener, TextToSpeech.OnInitListener {

    private final String apikey = "9ac9cce9-fefb-499a-9727-cbd6e7b7ead3";

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.UK);
            //textToSpeech.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
        }
        else{
            Log.d("zinit","Some Error With TTS");
        }
    }

    TextView txtSDK;
    Button  clickImage;
    TextView txtUriPath, txtRealPath;
    ImageView imageView;
    EditText ed;
    TextToSpeech textToSpeech;

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.shutdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to views
//        txtSDK = (TextView) findViewById(R.id.txtSDK);
        //btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        /*txtUriPath = (TextView) findViewById(R.id.txtUriPath);
        txtRealPath = (TextView) findViewById(R.id.txtRealPath);*/
        imageView = (ImageView) findViewById(R.id.imgView);
        clickImage = (Button)findViewById(R.id.b1);
        ed = (EditText) findViewById(R.id.ed);
        ed.setVisibility(View.INVISIBLE);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
                }
                else{
                    Log.d("init","Some Error With TTS");
                }
            }
        });


        // add click listener to button
       // btnSelectImage.setOnClickListener(this);
        clickImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak("Click Picture", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        clickImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doCapture(v);
                return true;
            }
        });
    }


    private String mImageFullPathAndName;


    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.mImageFullPathAndName = file.getAbsolutePath();
        return imgUri;
    }

    @Override
    public void onClick(View view) {

        // 1. on Upload click call ACTION_GET_CONTENT intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // 2. pick image only
        intent.setType("image/*");
        // 3. start activity
        startActivityForResult(intent, 0);

        // define onActivityResult to do something with picked image
    }
    Uri imageUri;


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public Bitmap decodeFile(File file) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        int mImageRealWidth = options.outWidth;
        int mImageRealHeight = options.outHeight;
        Bitmap pic = null;
        try {
            pic = BitmapFactory.decodeFile(file.getPath(), options);
        } catch (Exception ex) {
            Log.e("MainActivity", ex.getMessage());
        }
        pic = getResizedBitmap(pic, 400, 700);
        return pic;
    }

    public void doCapture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"fname_" +
                String.valueOf(System.currentTimeMillis()) + ".jpg"));
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, setImageUri());
        startActivityForResult(intent, 1);
    }
    private String localImagePath = "";
    private String SaveImage(Bitmap image)
    {
        String fileName = localImagePath + "imagetoocr.jpg";
        try {

            File file = new File(fileName);
            FileOutputStream fileStream = new FileOutputStream(file);

            image.compress(Bitmap.CompressFormat.JPEG, 100, fileStream);
            try {
                fileStream.flush();
                fileStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileName;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        if (requestCode == 0) {
            if (resultCode == RESULT_OK && null != data) {String realPath;
                // SDK < API11
                if (Build.VERSION.SDK_INT < 11)
                    realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

                    // SDK >= 11 && SDK < 19
                else if (Build.VERSION.SDK_INT < 19)
                    realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());

                    // SDK > 19 (Android 4.4)
                else
                    realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());


                setTextViews(Build.VERSION.SDK_INT, data.getData().getPath(), realPath);
                TypedFile file = new TypedFile("multipart/form-data",new File(realPath));
                textToSpeech = new TextToSpeech(getApplicationContext(), this);
                APIClient.getOcrAPI().getOCR(file, apikey, new Callback<OCR>() {
                    @Override
                    public void success(OCR ocr, Response response) {
                        List<TextBlock> textBlocks = ocr.getTextBlock();
                        StringBuilder stb = new StringBuilder("");
                        for(TextBlock tb: textBlocks){
                            stb.append(tb.getText());
                        }
                        ed.setText(stb.toString());
                        imageView.setVisibility(View.INVISIBLE);
                        ed.setVisibility(View.VISIBLE);
                        dialog.hide();

                        textToSpeech.speak(stb.toString(), TextToSpeech.QUEUE_FLUSH, null);
                        textToSpeech.speak(stb.toString(),TextToSpeech.QUEUE_FLUSH, null);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

                }
            }



        else if (resultCode != Activity.RESULT_CANCELED) {
              if (requestCode == 1) {

                imageView.setImageBitmap(decodeFile(new File(mImageFullPathAndName)));
                  TypedFile file = new TypedFile("multipart/form-data",new File(mImageFullPathAndName));
                  textToSpeech = new TextToSpeech(getApplicationContext(), this);
                  APIClient.getOcrAPI().getOCR(file, apikey, new Callback<OCR>() {
                      @Override
                      public void success(OCR ocr, Response response) {
                          List<TextBlock> textBlocks = ocr.getTextBlock();
                          StringBuilder stb = new StringBuilder("");
                          for(TextBlock tb: textBlocks){
                              stb.append(tb.getText());
                          }
                          ed.setText(stb.toString());
                          imageView.setVisibility(View.INVISIBLE);
                          ed.setVisibility(View.VISIBLE);
                          dialog.hide();
                          textToSpeech.speak(stb.toString(), TextToSpeech.QUEUE_FLUSH, null);
                          textToSpeech.speak(stb.toString(),TextToSpeech.QUEUE_FLUSH, null);
                      }

                      @Override
                      public void failure(RetrofitError error) {

                      }
                  });
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    private void setTextViews(int sdk, String uriPath, String realPath) {

//        this.txtSDK.setText("Build.VERSION.SDK_INT: " + sdk);
//        this.txtUriPath.setText("URI Path: " + uriPath);
//        this.txtRealPath.setText("Real Path: " + realPath);

        Uri uriFromPath = Uri.fromFile(new File(realPath));

        // you have two ways to display selected image

        // ( 1 ) imageView.setImageURI(uriFromPath);

        // ( 2 ) imageView.setImageBitmap(bitmap);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);

        Log.d("HMKCODE", "Build.VERSION.SDK_INT:" + sdk);
        Log.d("HMKCODE", "URI Path:" + uriPath);
        Log.d("HMKCODE", "Real Path: " + realPath);
    }

}


