package com.example.BookBrowser;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {

  private MyDatabaseHelper mDatabaseHelper;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mDatabaseHelper = new MyDatabaseHelper(this);


  }
}
