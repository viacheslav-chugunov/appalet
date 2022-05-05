package viacheslav.chugunov.appalet.contentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import viacheslav.chugunov.appalet.contentprovider.CollectionSharedDatabase.Companion.TABLE_NAME
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredThemesRepository
import viacheslav.chugunov.storage.room.AppRoomDatabase
import viacheslav.chugunov.storage.room.ThemeDatabaseDataSource


class CollectionContentProvider : ContentProvider() {
    private lateinit var database: SQLiteDatabase

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH, TYPE_ALL)
        addURI(AUTHORITY, "$PATH/#", TYPE_SINGLE)
    }

    override fun onCreate(): Boolean {
        val dao = AppRoomDatabase.getInstance(context!!).themeDao
        val dataSource = ThemeDatabaseDataSource(dao)
        val repository =  DefaultPreferredThemesRepository(dataSource)
        val themes = repository.getThemesNow()
        database = CollectionSharedDatabase(context!!, themes).writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var localSortOrder: String = sortOrder ?: ""
        var localSelection: String = selection ?: ""
        when (uriMatcher.match(uri)) {
            TYPE_ALL -> {
                if (localSortOrder.isEmpty()) {
                    localSortOrder = "$ID ASC"
                }
            }
            TYPE_SINGLE -> {
                localSelection += "$ID ${uri.lastPathSegment}"
            }
        }
        val cursor = database.query(
            TABLE_NAME,
            projection,
            localSelection,
            selectionArgs,
            null,
            null,
            localSortOrder
        )
        cursor.setNotificationUri(
            context!!.contentResolver,
            CONTENT_URI
        )
        return cursor
    }

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        TYPE_ALL -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
        TYPE_SINGLE -> "vnd.android.cursor.item/vnd.$AUTHORITY.$TABLE_NAME"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        require(uriMatcher.match(uri) == TYPE_ALL) { "Wrong URI: $uri" }
        val rowId = database.insert(TABLE_NAME, null, values)
        val resultUri = ContentUris.withAppendedId(CONTENT_URI, rowId)
        context!!.contentResolver.notifyChange(resultUri, null)
        return resultUri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val result = database.delete(TABLE_NAME, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return result
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val result = database.update(TABLE_NAME, values, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return result
    }

    companion object {
        private const val AUTHORITY = "viacheslav.chugunov.CollectionContentProvider"
        private const val PATH = "collection"
        private const val TYPE_ALL = 1
        private const val TYPE_SINGLE = 2
        private const val ID = "_ID"
        private val CONTENT_URI = Uri.parse("content://$AUTHORITY/$PATH")
    }
}