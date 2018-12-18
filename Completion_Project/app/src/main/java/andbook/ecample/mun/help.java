package andbook.ecample.mun;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class help extends Fragment implements View.OnClickListener, MainActivity.OnBackPressedListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    TextView t1, t2, t3, t4, t5, t6;
    Fragment fragment;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate( R.layout.help, null,false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        fragment = new Fragment();

        ImageButton imageBut=(ImageButton) view.findViewById(R.id.backbt3);
        imageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doBack();
            }
        });
        t1 = (TextView) view.findViewById( R.id.text1);
        t2 = (TextView) view.findViewById( R.id.explain);
        t3 = (TextView) view.findViewById( R.id.stategood);
        t4 = (TextView) view.findViewById( R.id.statesoso);
        t5 = (TextView) view.findViewById( R.id.statebad);
        t6 = (TextView) view.findViewById( R.id.stateverybad);

        btn1=(Button) view.findViewById( R.id.btn1 );
        btn2=(Button) view.findViewById( R.id.btn2 );
        btn3=(Button) view.findViewById( R.id.btn3 );
        btn4=(Button) view.findViewById( R.id.btn4 );
        btn5=(Button) view.findViewById( R.id.btn5 );
        btn6=(Button) view.findViewById( R.id.btn6 );
        btn7=(Button) view.findViewById( R.id.btn7 );

        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "NanumSquareL.otf");   //asset > fonts 폴더 내 폰트파일 적용
        Typeface typeFace2 = Typeface.createFromAsset(getContext().getAssets(), "NanumSquareEB.otf");

        t2.setTypeface(typeFace2);
        t3.setTypeface(typeFace2);
        t4.setTypeface(typeFace2);
        t5.setTypeface(typeFace2);
        t6.setTypeface(typeFace2);

        btn1.setTypeface(typeFace2);
        btn2.setTypeface(typeFace2);
        btn3.setTypeface(typeFace2);
        btn4.setTypeface(typeFace2);
        btn5.setTypeface(typeFace2);
        btn6.setTypeface(typeFace2);
        btn7.setTypeface(typeFace2);



        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);//버튼 마다 온클릭 리스너 지정
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        // ((MainActivity) getActivity()).setOnBackPressedListener(this);
        fragment = new Fragment();
        return view;
    }

    @Override
    public void onClick(View view) {//온클릭했을때 나올거 지정
        switch (view.getId()){
            case R.id.btn1:
                t1.setText("그래프는 4단계 기준이며 한국환경공단 기준과 같아요.\n" +
                        "\n" +
                        "미세먼지는 입자가 매우 작아 눈에 보이지 않아요. 그러므로 밖이 아무리 맑아도 미세먼지는 존재 할 수 있기 때문에 이 어플을 개발하게되었습니다!\n" +
                        "자료제공" +
                        "환경부 한국환경공단(Air Korea)" +
                        "기상청\n" +
                        "통합지수는 미세먼지, 초미세먼지, 오존, 이산화질소, 일산화탄소, 아황산가스 지수들을 다 합한 지수입니다");
                break;
            case R.id.btn2:
                t1.setText("미세먼지\n" +
                        "\n" +
                        "지름 10㎛이하인 먼지를 미세먼지라 말하며 환경법령에서는 흔히 PM10으로 불러요.\n 사람의 폐포까지 깊숙하게 침투해 각종 호흡기 질환으로 이어지며 황산염, 질산염, 암모니아 등의 이온 성분과 금속화합물, 탄소화합물 등 유해물지로 이루어져 있어요.\n" +
                        "외국에서는 지름이 10㎛ 이하(PM 10)이면 미세먼지(부유먼지, suspended particles)라 합니다\n");
                break;
            case R.id.btn3:
                t1.setText("초미세먼지\n" +
                        "\n"
                        +"지름 2.5㎛ 이하인 먼지를 초미세먼지라 말하며 환경법령에서는 흔히 PM2.5라고 불려요.\n 미세먼지보다 유해한 초미세먼지는 입자가 미세하여 코점막을 통해 걸러지지 않고 흡입시 폐포까지 직접 침투하여 천식이나 폐질환의 유병률과 조기사망률을 증가시킵니다.\n");
                break;
            case R.id.btn4:
                t1.setText("오존\n" +
                        "\n" +
                        "피해를 주는 오존"
                        +"지표로부터 10km이내의 대류권에는 나머지 오존 10%가 존재하고 있어요. \n 그러나 오존농도가 일정기준이상 높아질 경우 호흡기나 눈이 자극을 받아 기침이 나고 눈이 따끔거리거나 심할 경우 폐기능 저하를 가져오는 등 인체에 피해를 주기도 하고, 농작물의 수확량 감소를 가져오기도 합니다. 이러한 오존으로부터 피해를 줄이기 위해서는 가급적 자동차 운행을 줄이고, 대중교통시설을 이용하는 것이 필요해요\n");
                break;
            case R.id.btn5:
                t1.setText("이산화질소\n" +
                        "\n" +
                        "이산화질소는 적갈색의 반응성이 큰 기체로서, 대기 중에서 일산화질소의 산화에 의해서 발생하며, 대기 중에서 휘발성유기화합물(VOCs)과 반응하여 오존을 생성하는 전구물질(precursor)의 역할을 해요.\n" +
                        "\n" +
                        "주요 배출원은 자동차와 파워 플랜트와 같은 고온 연소공정과 화학물질 제조공정 등이 있으며, 토양중의 세균에 의해 생성되는 자연적 현상 등이 있습니다.\n"
                );
                break;
            case R.id.btn6:
                t1.setText("일산화탄소\n" +
                        "\n" +
                        "일산화 탄소(Carbon monoxide)는 탄소와 산소로 구성된 화합물이예요. 석탄이나 석유를 다량 연소시키는 공업지대의 대기에 포함되어 있는 경우가 있으며, 가정에 공급되고 있는 도시가스에도 포함되어 있어요. 탄소 화합물이 불완전 연소되면 발생하며 가연성이고 독성이 있어서 취급에 주의가 필요합니다. 산소보다 헤모글로빈과의 친화력이 200배 정도 더 좋기 때문에 소량 흡입시에도 호흡 대사를 방해하여 생명 유지가 어려울 수 있으므로 주의!해야 합니다\n" );

                break;
            case R.id.btn7:
                t1.setText("아황산가스\n" +
                        "\n" +
                        "공기 중에 농도가 높아지먼 호흡기에 장애를 일으켜요. 천식, 기관지염을" +
                        "일으키는 것말고도 눈을 따끔거리게 하며 목구멍이나 소화기계통을 통해서 " +
                        "혈액에 들어가 혈액의 산성도를 높이고 뼈에서 칼슘을 빼앗아 벼의 발육을 " +
                        "방해하며 이의 상아질을 녹이기도 해요.. \n가스를 들어마시면 기침.가래가 생기" +
                        "며 눈물이 난답니다 아황산가스를 발생시키는 주요 물질은 석유나 석탄이며 " +
                        "전국민의 7l% 가 쓰고 있는 난방 연료인 연탄이 아황산가스 오염을 일으키는" +
                        "비중은 53.8%에 이르고 있습니다 " );
                break;

        }

    }

    @Override
    public void doBack() {
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener( null );
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left)
                .replace( R.id.frag, fragment).commit();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach( context );
        ((MainActivity)context).setOnBackPressedListener( this );

    }
}