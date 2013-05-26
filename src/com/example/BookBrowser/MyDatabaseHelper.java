package com.example.BookBrowser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

public class MyDatabaseHelper extends SQLiteOpenHelper {

  public enum Query {
      BooksWithAuthor
  }

  private static final String DATABASE_NAME = "bookbrowser.db";
  private static final int DATABASE_VERSION = 1;

  public MyDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + BooksTable.TABLE_NAME + " ("
        + BooksTable.COL_ID + " INTEGER AUTOINCREMENT PRIMARY KEY"
        + ", " + BooksTable.COL_TITLE + " TEXT NOT NULL"
        + ", " + BooksTable.COL_ISBN + " TEXT NOT NULL"
        + ", " + BooksTable.COL_AUTHOR_ID + " INTEGER NOT NULL"
        + ", " + BooksTable.COL_CATEGORY_ID + " INTEGER NOT NULL "
        + ");");

    db.execSQL("CREATE TABLE " + AuthorsTable.TABLE_NAME + " ("
        + AuthorsTable.COL_ID + " INTEGER AUTOINCREMENT PRIMARY KEY"
        + ", " + AuthorsTable.COL_FIRST_NAME + " TEXT NOT NULL"
        + ", " + AuthorsTable.COL_LAST_NAME + " TEXT NOT NULL"
        + ");");

    db.execSQL("CREATE TABLE " + CategoriesTable.TABLE_NAME + " ("
        + CategoriesTable.COL_ID + " INTEGER AUTOINCREMENT PRIMARY KEY"
        + ", " + CategoriesTable.COL_NAME + " TEXT NOT NULL"
        + ");");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + AuthorsTable.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + BooksTable.TABLE_NAME);
  }

  public void query(Query query) {
      switch (query) {
          case BooksWithAuthor:
            SQLiteQueryBuilder builder ;
      }
  }

  public static class BooksTable {
    public static final String TABLE_NAME = "books";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_TITLE = "title";
    public static final String COL_ISBN = "isbn";

    public static final String COL_AUTHOR_ID = "author_id";
    public static final String COL_CATEGORY_ID = "category_id";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_TITLE = TABLE_NAME + "." + "title";
    public static final String FULL_ISBN = TABLE_NAME + "." + "isbn";

    public static final String FULL_AUTHOR_ID = TABLE_NAME + "." + "author_id";
    public static final String FULL_CATEGORY_ID = TABLE_NAME + "." + "category_id";
  }

  public static class AuthorsTable {
    public static final String TABLE_NAME = "authors";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "first_name";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_FIRST_NAME = TABLE_NAME + "." + "first_name";
    public static final String FULL_LAST_NAME = TABLE_NAME + "." + "last_name";
  }

  public static class CategoriesTable {
    public static final String TABLE_NAME = "categories";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_NAME = "name";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_NAME = TABLE_NAME + "." + "name";
  }
}
