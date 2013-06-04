package com.example.BookBrowser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

public class MyDatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "bookbrowser.db";
  private static final int DATABASE_VERSION = 1;

  public MyDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + BooksTable.TABLE_NAME + " ("
        + BooksTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
        + ", " + BooksTable.COL_TITLE + " TEXT NOT NULL"
        + ", " + BooksTable.COL_ISBN + " TEXT NOT NULL"
        + ", " + BooksTable.COL_AUTHOR_ID + " INTEGER NOT NULL"
        + ", " + BooksTable.COL_CATEGORY_ID + " INTEGER NOT NULL "
        + ");");

    db.execSQL("CREATE TABLE " + AuthorsTable.TABLE_NAME + " ("
        + AuthorsTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
        + ", " + AuthorsTable.COL_FIRST_NAME + " TEXT NOT NULL"
        + ", " + AuthorsTable.COL_LAST_NAME + " TEXT NOT NULL"
        + ");");

    db.execSQL("CREATE TABLE " + CategoriesTable.TABLE_NAME + " ("
        + CategoriesTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
        + ", " + CategoriesTable.COL_NAME + " TEXT NOT NULL"
        + ");");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + AuthorsTable.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + BooksTable.TABLE_NAME);
  }

  public long insert(String tableName, ContentValues values) {
    return getWritableDatabase().insert(tableName, null, values);
  }

  public long count(String tableName) {
    return DatabaseUtils.queryNumEntries(getReadableDatabase(), tableName, null, null);
  }

  public long insertCategory(String name) {
    ContentValues values = new ContentValues();
    values.put(CategoriesTable.COL_NAME, name);

    return insert(CategoriesTable.TABLE_NAME, values);
  }

  public long insertAuthor(String firstName, String lastName) {
    ContentValues values = new ContentValues();
    values.put(AuthorsTable.COL_FIRST_NAME, firstName);
    values.put(AuthorsTable.COL_LAST_NAME, lastName);

    return insert(AuthorsTable.TABLE_NAME, values);
  }

  public long insertBook(String title, String isbn, long authorId, long categoryId) {
    ContentValues values = new ContentValues();
    values.put(BooksTable.COL_TITLE, title);
    values.put(BooksTable.COL_ISBN, isbn);
    values.put(BooksTable.COL_AUTHOR_ID, authorId);
    values.put(BooksTable.COL_CATEGORY_ID, categoryId);

    return insert(BooksTable.TABLE_NAME, values);
  }

  public Cursor queryBooks(String selection, String[] selectionArgs, String sortOrder) {
    String[] projection = {BooksTable.FULL_ID, BooksTable.FULL_TITLE, AuthorsTable.FULL_FIRST_NAME, AuthorsTable.FULL_LAST_NAME, CategoriesTable.FULL_NAME };

    SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
    sqLiteQueryBuilder.setTables(BooksTable.TABLE_NAME
        + " LEFT OUTER JOIN " + CategoriesTable.TABLE_NAME + " ON (" + BooksTable.FULL_CATEGORY_ID + " = " + CategoriesTable.FULL_ID + ")"
        + " LEFT OUTER JOIN " + AuthorsTable.TABLE_NAME + " ON (" + BooksTable.FULL_AUTHOR_ID+ " = " + AuthorsTable.FULL_ID + ")"
    );

    return sqLiteQueryBuilder.query(getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
  }

  public static class BooksTable {
    public static final String TABLE_NAME = "books";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_TITLE = "title";
    public static final String COL_ISBN = "isbn";

    public static final String COL_AUTHOR_ID = "author_id";
    public static final String COL_CATEGORY_ID = "category_id";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_TITLE = TABLE_NAME + "." + COL_TITLE;
    public static final String FULL_ISBN = TABLE_NAME + "." + COL_ISBN;

    public static final String FULL_AUTHOR_ID = TABLE_NAME + "." + COL_AUTHOR_ID;
    public static final String FULL_CATEGORY_ID = TABLE_NAME + "." + COL_CATEGORY_ID;
  }

  public static class AuthorsTable {
    public static final String TABLE_NAME = "authors";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_FIRST_NAME = TABLE_NAME + "." + COL_FIRST_NAME;
    public static final String FULL_LAST_NAME = TABLE_NAME + "." + COL_LAST_NAME;
  }

  public static class CategoriesTable {
    public static final String TABLE_NAME = "categories";

    public static final String COL_ID = BaseColumns._ID;
    public static final String COL_NAME = "name";

    public static final String FULL_ID = TABLE_NAME + "." + BaseColumns._ID;
    public static final String FULL_NAME = TABLE_NAME + "." + COL_NAME;
  }
}
