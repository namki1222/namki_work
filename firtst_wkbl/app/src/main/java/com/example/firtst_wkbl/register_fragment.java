package com.example.myapplication8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public register_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static register_fragment newInstance(String param1, String param2) {
        register_fragment fragment = new register_fragment();
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
        View view = inflater.inflate(R.layout.register, container, false);

        Button join_button = (Button) view.findViewById(R.id.join_button);
        String post_string_register[] = new String[4];
        for (int a = 0; a < 4; a++) {
            post_string_register[a] = null;
        }
        EditText register[] = new EditText[4];
        register[0] = view.findViewById(R.id.reg_id);
        register[1] = view.findViewById(R.id.reg_pwd);
        register[2] = view.findViewById(R.id.reg_birth);
        register[3] = view.findViewById(R.id.reg_name);
        System.out.println("회원가입버튼");

        join_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("회원가입버튼");
                post_string_register[0] = register[0].getText().toString();
                post_string_register[1] = register[1].getText().toString();
                post_string_register[2] = register[2].getText().toString();
                post_string_register[3] = register[3].getText().toString();
                System.out.println("회원가입버튼");
                new Thread() {
                    public void run() {
                        ServerOperation.post(post_string_register, "http://192.168.0.84:92/register.php", join_button.getContext());
                    }
                }.start();
            }
        });
        return view;
    }
}