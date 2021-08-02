package com.example.myapplication8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public login_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static login_fragment newInstance(String param1, String param2) {
        login_fragment fragment = new login_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,container,false);
        Button button2 = (Button)view.findViewById(R.id.button2);
        Button btn_login = (Button) view.findViewById(R.id.button);
        String post_string[] = new String[4];
        for (int a = 0; a < 4; a++) {
            post_string[a] = null;
        }
         EditText login[] = new EditText[2];
        login[0] = view.findViewById(R.id.id);
        login[1] = view.findViewById(R.id.pwd);

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                post_string[0] = login[0].getText().toString();
                post_string[1] = login[1].getText().toString();
                System.out.println("로그인버튼");
                new Thread() {
                    public void run() {
                        ServerOperation.post(post_string, "http://192.168.0.84:92/login.php", btn_login.getContext());
                    }
                }.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        SharedPreferences pref = btn_login.getContext().getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =pref.edit();
                        editor.clear();
                        editor.commit();
                    }
                }.start();
            }
        });
        return view;
    }
}