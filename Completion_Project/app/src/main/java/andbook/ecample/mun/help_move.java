package andbook.ecample.mun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class help_move extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container
    , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.help, container, false);       //도움말 레이아웃 불러옴
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {        //slide animation (도움말 클릭시 생기는 애니메이션)
        super.onActivityCreated(savedInstanceState);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        help fragment = new help();
        manager.beginTransaction()
                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left)
                .replace( R.id.frag,fragment )
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

}
