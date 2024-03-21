package ru.mirea.maksimovaok.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class MyDateDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog = new DatePickerDialog(getActivity());
        return dialog;
    }
}
