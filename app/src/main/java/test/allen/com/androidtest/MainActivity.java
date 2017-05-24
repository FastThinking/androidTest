package test.allen.com.androidtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allen.BaseActivity;
import com.allen.MyApplication;
import com.allen.bean.Note;
import com.allen.bean.UserInfo;
import com.allen.greendao.gen.NoteDao;
import com.allen.greendao.gen.UserInfoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.noteCount)
    TextView noteCount;
    @BindView(R.id.selectNote)
    Button selectNote;
    UserInfoDao mUserDao;
    NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mUserDao = MyApplication.getInstances().getDaoSession().getUserInfoDao();
        noteDao = MyApplication.getInstances().getDaoSession().getNoteDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add)
    public void addData() {
        try {
            UserInfo mUser = new UserInfo((long) 2, "123", "allen");
            mUserDao.insert(mUser);//添加一个
            toast("allen 添加成功！");
            show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.query)
    public void showData() {
        show();
    }

    @OnClick(R.id.update)
    public void updateData() {
        UserInfo mUser = new UserInfo((long) 2, "123", "allen0803");
        mUserDao.update(mUser);
        show();
    }

    @OnClick(R.id.delete)
    public void deleteData() {
        mUserDao.deleteByKey(2l);
        show();
    }

    private void show() {
        List<UserInfo> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ",";
        }
        tv.setText("查询全部数据==>" + userName);
    }

    @OnClick(R.id.addNote)
    public void addNote() {
        List<UserInfo> users = mUserDao.loadAll();
        if (users.size() > 0) {
            UserInfo userInfo = users.get(0);
            Note note = new Note();
            note.setUserUuid(userInfo.getUuid());
            note.setNoteTitle("6月16号日记");
            noteDao.save(note);
            toast("笔记添加成功！");
        }
        showNoteCount();
    }

    @OnClick(R.id.selectNote)
    public void showNoteCount() {
        //清除缓存
//        daoSession.clear();
        mUserDao.detachAll();
        UserInfo userInfo = mUserDao.load(2l);
        String noteCountText = "笔记数量：0";
        if (userInfo != null) {
            noteCountText = "笔记数量：" + userInfo.getNotes().size();
        }
        noteCount.setText(noteCountText);
    }

}
