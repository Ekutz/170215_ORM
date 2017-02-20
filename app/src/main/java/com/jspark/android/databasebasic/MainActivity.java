package com.jspark.android.databasebasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.jspark.android.databasebasic.domain.Bbs;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert() throws SQLException {
        // DB 연결
        // OnpenHelperManager는 DBHelper dbHelper = new DBHelper(this, null, null, 0); 을 줄여줌
        DBHelper dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);

        // 테이블 조작할 객체 생성
        Dao<Bbs, Long> bbsDao = dbHelper.getDao();

        // 입력 값 생성
        String title = "제목";
        String content = "내용입니다";
        Date currDateTime = new Date(System.currentTimeMillis());

        // 값을 토대로 Bbs 개체 생성
        Bbs bbs = new Bbs(title, content, currDateTime);

        // Bbs 개체를 dao를 통해 insert
        bbsDao.create(bbs);

        // 한큐에 insert
        bbsDao.create(new Bbs("제목2", "내용2입니다", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("제목3", "내용3입니다", new Date(System.currentTimeMillis())));


        List<Bbs> bbsList = bbsDao.queryForAll();
        for(Bbs item : bbsList) {
            Log.i("Bbs item : ", "id = "+item.getId()+" "+"title = "+item.getTitle());
        }

        Bbs bbs2 = bbsDao.queryForId(40L);
        Log.i("Bbs item : ", "id = "+bbs2.getId()+" "+"title = "+bbs2.getTitle());

        List<Bbs> bbs3 = bbsDao.queryForEq("title", "제목3");
        for(Bbs item : bbs3) {
            Log.i("Bbs item : ", "id = "+item.getId()+" "+"title = "+item.getTitle());
        }

        //bbsDao.delete(bbsList);

//        Bbs bbstemp = bbsDao.queryForId(37L);
//        bbstemp.setTitle("37번 수정");
//        bbsDao.update(bbstemp);

        String query = "SELECT * FROM bbs where title like '%3%'";
        GenericRawResults<Bbs> rawResults = bbsDao.queryRaw(query, bbsDao.getRawRowMapper());

        List<Bbs> bbs4 = rawResults.getResults();
        for(Bbs item : bbs4) {
            Log.i("RAW : Bbs item : ", "id = "+item.getId()+" "+"title = "+item.getTitle());
        }
    }
}
