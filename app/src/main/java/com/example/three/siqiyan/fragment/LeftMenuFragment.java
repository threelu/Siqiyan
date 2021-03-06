package com.example.three.siqiyan.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.three.siqiyan.LoginInActivity;
import com.example.three.siqiyan.LoginUpActivity;
import com.example.three.siqiyan.MainActivity;
import com.example.three.siqiyan.R;
import com.example.three.siqiyan.UserInfoActivity;
import com.example.three.siqiyan.pager.ZiXunPager;
import com.example.three.siqiyan.view.CircleImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 侧边栏
 *Created by Three on 2016/4/18.
 */
public class LeftMenuFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout leftHome, leftSuscribe, leftComment, leftPush;//中间的相对布局选项duixiang
    private TextView fastLogin,fastLoginUp;
    private CircleImageView circleImageView;
    private ImageView toUserInfo;
    private LinearLayout lyLoginstate,lyNotLoginstate;



    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    boolean state = false;
    View view;

    @Override
    public View initViews() {
        view = View.inflate(mActivity, R.layout.fragment_left_news, null);
        leftHome = (RelativeLayout) view.findViewById(R.id.left_home);
        leftSuscribe = (RelativeLayout) view.findViewById(R.id.left_suscribe);
        leftComment = (RelativeLayout) view.findViewById(R.id.left_comment);
        leftPush = (RelativeLayout) view.findViewById(R.id.left_push);
        fastLogin = (TextView) view.findViewById(R.id.fast_login);
        fastLoginUp = (TextView) view.findViewById(R.id.fast_loginup);
        circleImageView = (CircleImageView) view.findViewById(R.id.first_circleimg);
        toUserInfo = (ImageView) view .findViewById(R.id.touserinfo);
        lyLoginstate = (LinearLayout) view.findViewById(R.id.left_login_after);
        lyNotLoginstate= (LinearLayout) view.findViewById(R.id.left_login_before);

        //设置监听事件
        leftHome.setOnClickListener(this);
        leftSuscribe.setOnClickListener(this);
        leftComment.setOnClickListener(this);
        leftPush.setOnClickListener(this);
        fastLoginUp.setOnClickListener(this);
        fastLogin.setOnClickListener(this);
        toUserInfo.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        setLoginState();//登录显示状态
        return view;
    }

    @Override
    public void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_home:
                setMenuPager(0,"首页");
                break;
            case R.id.left_suscribe:
                setMenuPager(1, "订阅");
                break;
            case R.id.left_comment:
                setMenuPager(2, "神评论");
                break;
            case R.id.left_push:
                setMenuPager(3,"历史推送");
                break;
            case R.id.fast_login:
                Intent intent = new Intent(mActivity, LoginInActivity.class);
                startActivity(intent);
//                toggleSlidingMenu();//如果注册途中返回则显示有侧边栏
                break;
            case R.id.fast_loginup:
                Intent intent2 = new Intent(mActivity, LoginUpActivity.class);
                startActivity(intent2);
//                toggleSlidingMenu();
                break;
            case R.id.touserinfo:
                Intent toUserIn = new Intent(mActivity, UserInfoActivity.class);
                startActivity(toUserIn);
                break;
            case R.id.first_circleimg:
                Intent toUserIn2 = new Intent(mActivity, UserInfoActivity.class);
                startActivity(toUserIn2);
                break;
        }
    }

    /**
     * 处理munepager事件，显示指定的页面，关闭侧边栏
     * @param pos   选型位置（0.首页 1.订阅 2.神评论 3.推送历史）
     * @param title menupager页面的标题
     */
    public void setMenuPager(int pos, String title) {
        setCurrentMenuPager(pos, title);
        toggleSlidingMenu();
    }
    /**
     * 设置当前菜单的页签详情页
     */
    private void setCurrentMenuPager(int pos, String title) {
        MainActivity main = (MainActivity) mActivity;

        ContentFragment content = main.getContentFragment();
        ZiXunPager pager = content.getZiXunPager();
        pager.setCurrentMenuPager(pos, title);
    }

    /**
     * 关闭侧边栏
     */
    public void toggleSlidingMenu() {
        MainActivity main = (MainActivity) mActivity;
        SlidingMenu slidemenu = main.getSlidingMenu();
        slidemenu.toggle();//切换状态，显示隐藏，隐藏显示
    }

    /**
     * 根据登上成功与否设置侧边栏显示
      */
    public void setLoginState(){
        MainActivity main = (MainActivity) mActivity;
        if(main.isLoginState()){
            lyLoginstate.setVisibility(View.VISIBLE);
            lyNotLoginstate.setVisibility(View.GONE);
        }else {
            lyLoginstate.setVisibility(View.GONE);
            lyNotLoginstate.setVisibility(View.VISIBLE);
        }
    }

}
