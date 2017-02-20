package com.jspark.android.databasebasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jspark.android.databasebasic.domain.Bbs;

import java.sql.SQLException;

/**
 * Created by jsPark on 2017. 2. 14..
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    /*
    생성자에서 호출되는 super(context... 에서 databaes.db 파일이 생성되어 있지 않으면 호출된다
     */

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // 테이블 생성
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 버전 int가 변동사항이 생기면 호출된다
    // 호출 후 내부에서 onCreate 호출
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // 테이블 삭제
            TableUtils.dropTable(connectionSource, DBHelper.class, false);
            /*
            * 데이터 보존과 관련된 로직
            * */
            // 새로운 테이블 생성
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao<Bbs, Long> bbsDao = null;

    public Dao<Bbs, Long> getDao() throws SQLException {
        if(bbsDao==null) {
            bbsDao = getDao(Bbs.class);
        }
        return bbsDao;
    }

    public void dropDao() {
        if(bbsDao!=null) {
            bbsDao = null;
        }
    }
}
