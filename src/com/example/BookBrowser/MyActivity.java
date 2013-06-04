package com.example.BookBrowser;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class MyActivity extends Activity {

  private MyDatabaseHelper mDatabaseHelper;
  private BooksAdapter mBooksAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mDatabaseHelper = new MyDatabaseHelper(this);

    if (mDatabaseHelper.count(MyDatabaseHelper.BooksTable.TABLE_NAME) == 0) {
      seedDatabase();
    }

    Cursor c = mDatabaseHelper.queryBooks(null, null, MyDatabaseHelper.BooksTable.FULL_TITLE);
    mBooksAdapter = new BooksAdapter(this, R.layout.simple_list_item_3, c, 0);

    ListView listView = (ListView) findViewById(R.id.listView);
    listView.setAdapter(mBooksAdapter);
  }

  private void seedDatabase() {
    long[] categoryIds = new long[3];

    categoryIds[0] = mDatabaseHelper.insertCategory("Agile");
    categoryIds[1] = mDatabaseHelper.insertCategory("Mobile");
    categoryIds[2] = mDatabaseHelper.insertCategory("Ruby and Rails");

    long[] authorIds = new long[9];

    authorIds[0] = mDatabaseHelper.insertAuthor("Elisabeth", "Hendrikson");
    authorIds[1] = mDatabaseHelper.insertAuthor("Henrik", "Kniberg");
    authorIds[2] = mDatabaseHelper.insertAuthor("Josh", "Carter");

    authorIds[3] = mDatabaseHelper.insertAuthor("Jonathan", "Penn");
    authorIds[4] = mDatabaseHelper.insertAuthor("Mike", "Riley");
    authorIds[5] = mDatabaseHelper.insertAuthor("Kevin", "Brothaler");

    authorIds[6] = mDatabaseHelper.insertAuthor("Jose", "Valim");
    authorIds[7] = mDatabaseHelper.insertAuthor("Sam", "Ruby");
    authorIds[8] = mDatabaseHelper.insertAuthor("Clay", "Allsopp");

    mDatabaseHelper.insertBook("Explore It!: Reduce Risk and Increase Confidence with Exploratory Testing", "978-1-93778-502-4", authorIds[0], categoryIds[0]);
    mDatabaseHelper.insertBook("Lean from the Trenches: Managing Large-Scale Projects with Kanban", "978-1-93435-685-2", authorIds[1], categoryIds[0]);
    mDatabaseHelper.insertBook("New Programmer's Survival Manual: Navigate Your Workplace, Cube Farm, or Startup", "978-1-93435-681-4", authorIds[2], categoryIds[0]);

    mDatabaseHelper.insertBook("Test iOS Apps with UI Automation: Bug Hunting Made Easy", "978-1-93778-552-9", authorIds[3], categoryIds[1]);
    mDatabaseHelper.insertBook("Ultimate Android Power Tips: Make Your Mobile Work for You", "978-1-93778-554-3", authorIds[4], categoryIds[1]);
    mDatabaseHelper.insertBook("OpenGL ES 2 for Android: A Quick-Start Guide", "978-1-93778-534-5", authorIds[5], categoryIds[1]);

    mDatabaseHelper.insertBook("Crafting Rails 4 Applications: Expert Practices for Everyday Rails Development", "978-1-93778-555-0", authorIds[6], categoryIds[2]);
    mDatabaseHelper.insertBook("Agile Web Development with Rails 4", "978-1-93778-556-7", authorIds[7], categoryIds[2]);
    mDatabaseHelper.insertBook("RubyMotion", "978-1-93778-528-4", authorIds[8], categoryIds[2]);
  }

  private class BooksAdapter extends ResourceCursorAdapter {
    public BooksAdapter(Context context, int layout, Cursor c, int flags) {
      super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {
      TextView text1 = (TextView) view.findViewById(R.id.text1);
      TextView text2 = (TextView) view.findViewById(R.id.text2);
      TextView text3 = (TextView) view.findViewById(R.id.text3);

      String bookTitle = c.getString(c.getColumnIndex(MyDatabaseHelper.BooksTable.COL_TITLE));
      String authorName = c.getString(c.getColumnIndex(MyDatabaseHelper.AuthorsTable.COL_FIRST_NAME)) + " " + c.getString(c.getColumnIndex(MyDatabaseHelper.AuthorsTable.COL_LAST_NAME));
      String categoryName = c.getString(c.getColumnIndex(MyDatabaseHelper.CategoriesTable.COL_NAME));

      text1.setText(bookTitle);
      text2.setText(authorName);
      text3.setText(categoryName);
    }
  }
}
