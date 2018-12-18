package andbook.ecample.mun;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018-05-23.
 */

public class Text extends AppCompatTextView {
    public Text(Context context) {
        super(context, null);
        applyTypeface(context);
    }

    public Text(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        applyTypeface(context);
    }

    public Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }
    private void applyTypeface(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"NanumSquareR.otf");
        setTypeface(typeface);
    }



}
