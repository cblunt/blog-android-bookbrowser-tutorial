package com.example.BookBrowser;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyActivity extends Activity {

  private MyDatabaseHelper mDatabaseHelper;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mDatabaseHelper = new MyDatabaseHelper(this);


      Cursor c = mDatabaseHelper.query(BOOKS_WITH_AUTHOR);
      String[] from = {MyDatabaseHelper.BooksTable.FULL_TITLE, MyDatabaseHelper.AuthorsTable.FULL_FIRST_NAME};
      int[] to = {android.R.id.text1, android.R.id.text2};

      SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, from, to);

      ListView listView = (ListView) findViewById(R.id.listView);
      listView.setAdapter(adapter);

  }
}
