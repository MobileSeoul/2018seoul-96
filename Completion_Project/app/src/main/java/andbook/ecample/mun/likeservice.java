package andbook.ecample.mun;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class likeservice extends Fragment implements MainActivity.OnBackPressedListener,View.OnClickListener{


    public likeservice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment likeservice.
     */
    // TODO: Rename and change types and number of parameters
    public static likeservice newInstance(String param1, String param2) {
        likeservice fragment = new likeservice();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    Fragment fragment;
    private ListView listView;                      // onCilck 쓰레드에서도 화면 구성을
    ArrayList<String> item = new ArrayList<>();      // 변경해야하기에 전역변수로 선언
    ArrayAdapter<String> adapter;                    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_likeservice,container,false);
        LinearLayout imageButton = (LinearLayout) view.findViewById( R.id.imagebtn);
        imageButton.setOnClickListener(this);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        fragment = new Fragment();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ImageButton imageBut=(ImageButton) view.findViewById(R.id.backbt);
        imageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doBack();
            }
        });

        readData(item);
        listView = (ListView)view.findViewById( R.id.listview);

        adapter =new ArrayAdapter<String>(getActivity(),
                R.layout.listview_item,R.id.textView12,item)
        {
            @Override
            public View getView(int position,View convertview,ViewGroup parent){

                View view1 =super.getView(position,convertview,parent);
                TextView tv=(TextView)view1.findViewById(R.id.textView12);
                ImageView iv =(ImageView) view1.findViewById(R.id.sliding);


                return view1;
            }

        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ((MainActivity) getActivity()).startRequest(item.get(position));

                MainActivity activity = (MainActivity)getActivity();
                activity.setOnBackPressedListener( null );
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace( R.id.frag,fragment ).commit();

            }
        });
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    adapter.remove(adapter.getItem(position));
                                }
                                adapter.notifyDataSetChanged();
                                saveData(item);
                            }
                        });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());

        return view;
    }


    void saveData (ArrayList<String> Data)
    {
        try {
            FileOutputStream outFs = getContext().openFileOutput("Data.txt",
                    getContext().MODE_PRIVATE);
            String str ="";
            for(int i=0;i<Data.size();i++)
            {
                str += Data.get(i)+"|";
            }
            outFs.write(str.getBytes());
            outFs.close();
            Toast.makeText(getContext(),
                    "Data저장됨", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
        }
    }
    void readData (ArrayList<String> Data){
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = getContext().openFileInput("Data.txt");
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            String value ="";
            for(int i=0;i<diaryStr.length();i++)
            {
                char Check=diaryStr.charAt(i);
                if(Check=='|') {
                    Data.add(value);
                    value ="";
                }
                else
                    value += diaryStr.charAt(i);
            }
        } catch (IOException e) {
            return;
        }
        return;
    }


    @Override
    public void doBack() {
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener( null );
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left)
                .replace( R.id.frag,fragment ).commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.imagebtn:
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("즐겨찾기 지역 추가");
                alert.setMessage("지역 이름을 입력해주세요");
                final EditText name = new EditText(getActivity());
                name.setHint("분당구 야탑동");
                final String result = name.getText().toString();
                alert.setView(name);
                //item.add(result);
                //saveData(item);
                //listView.setAdapter(adapter);
                alert.setPositiveButton("검색", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int whichButton) {
                        final GpsInfo gps = new GpsInfo(getContext());
                        final String result = transAddr.getDmXY(getContext(), String.valueOf(name.getText()), gps);
                        AlertDialog.Builder Sub_alert = new AlertDialog.Builder(getActivity());
                        Sub_alert.setTitle("검색 결과");
                        if(result.equals("error")){
                            Sub_alert.setMessage("네트워크 또는 GPS 상태가 원활하지 않습니다.");
                            Sub_alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            });
                        }
                        else if (result != "")
                         {
                        Sub_alert.setMessage("검색하신 지역이\n" + result + "\n가 맞나요?");
                        Sub_alert.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                item.add(result);
                                saveData(item);
                                listView.setAdapter(adapter);

                            }
                        });
                        Sub_alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                    }
                    else {
                            Sub_alert.setMessage("정확한 지역 이름을 입력해주세요");
                            Sub_alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            });
                        }


                        Sub_alert.show();
                    }
                });
                alert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                final AlertDialog dialog = alert.create();
                dialog.setOnShowListener( new DialogInterface.OnShowListener() { @Override public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK); } });

                dialog.show();
                break;


        }

    }
}
