package com.example.fitnessapp.model_class;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fitnessapp.R;

import java.util.ArrayList;

public class MultipleChoiceFragment extends DialogFragment {
    public interface onMultiChoiceListener{
        void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemList);
        void onNegativeButtonClicked();
    }

    onMultiChoiceListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach ( context );
        try {
            mListener = (onMultiChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException ( getActivity ().toString ()+ " onMultiChoiceListener must be implemented " );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final ArrayList<String > selectedItemList = new ArrayList<> (  );
        AlertDialog.Builder builder = new AlertDialog.Builder ( getActivity () );
        final String [] list = getActivity ().getResources ().getStringArray ( R.array.choiceitems );
        builder.setTitle ( "Select your Choices" ).setMultiChoiceItems ( list, null, new DialogInterface.OnMultiChoiceClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                {
                    selectedItemList.add ( list[which] );
                }
                else {
                    selectedItemList.remove ( list[which] );
                }


            }
        } ).setPositiveButton ( "OK", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onPositiveButtonClicked (list,selectedItemList);

            }
        } )
                .setNegativeButton ( "Cancel", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNegativeButtonClicked ();
                    }
                } );
        return builder.create ();
    }
}
