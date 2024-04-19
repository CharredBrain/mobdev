package ru.mirea.maksimovaok.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.maksimovaok.looper.databinding.ActivityMainBinding;


public	class	MainActivity	extends	AppCompatActivity	{
    private ActivityMainBinding binding;

    @Override
    protected	void	onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        ActivityMainBinding	binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler	mainThreadHandler	=	new	Handler(Looper.getMainLooper())	{
            @Override
            public	void	handleMessage(Message msg)	{
                Log.d(MainActivity.class.getSimpleName(),	"Task	execute.	This	is	result:	"	+	msg.getData().getString("result"));
            }
        };
        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();
        binding.editTextMirea.setText("Мой номер по списку №13");
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                Message	msg	=	Message.obtain();
                Bundle	bundle	=	new	Bundle();
                bundle.putString("KEY",	 binding.age.getText().toString() + " лет, работает " + binding.position.getText().toString());
                msg.setData(bundle);
                SystemClock.sleep(Integer.parseInt(binding.age.getText().toString()) * 1000);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}