package ru.mirea.maksimovaok.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

public class MyTimeDialogFragment extends DialogFragment {

    Calendar calendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                tslistener,
                hr, min, DateFormat.is24HourFormat(getActivity()));
        return dialog;
    }

    TimePickerDialog.OnTimeSetListener tslistener = (view, hourOfDay, minute) -> {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
    };
}
