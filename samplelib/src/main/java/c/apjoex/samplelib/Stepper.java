package c.apjoex.samplelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by apjoe on 6/28/2017.
 */

public class Stepper extends LinearLayout {

    public int currentNumber;
    public int maxNumber;

    Button decreaseBtn, increaseBtn;
    TextView numberText;

    public Stepper(Context context) {
        super(context);
        init(context, null);
    }

    public Stepper(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Stepper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Stepper);
            currentNumber = typedArray.getInteger(R.styleable.Stepper_currentNumber, 0);
            maxNumber = typedArray.getInteger(R.styleable.Stepper_maxNumber, 0);
            typedArray.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom, this);
        
        decreaseBtn = (Button)findViewById(R.id.decrease_btn);
        increaseBtn = (Button)findViewById(R.id.increase_btn);
        numberText = (TextView)findViewById(R.id.text);


        decreaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber > 0) {
                    currentNumber--;
                    updateControls();
                }
            }
        });


        increaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentNumber < maxNumber){
                    currentNumber++;
                    updateControls();
                }
            }
        });

        updateControls();
    }

    private void updateControls() {
        numberText.setText(String.valueOf(currentNumber));
        increaseBtn.setEnabled(currentNumber < maxNumber);
        decreaseBtn.setEnabled(currentNumber > 0);
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

}
