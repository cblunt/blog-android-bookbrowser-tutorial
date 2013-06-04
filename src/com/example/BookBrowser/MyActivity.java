package com.example.BookBrowser;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;

public class MyActivity extends Activity {

  private MyDatabaseHelper mDatabaseHelper;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mDatabaseHelper = new MyDatabaseHelper(this);

    if(mDatabaseHelper.count(MyDatabaseHelper.BooksTable.TABLE_NAME) == 0) {
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
  }
}
