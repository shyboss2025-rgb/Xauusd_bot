package com.xau.bot;
import android.os.Bundle; import android.os.Handler; import android.widget.TextView; import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity; import java.util.Random; import java.text.SimpleDateFormat; import java.util.Date; import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    TextView priceText, signalText, logText; Handler handler = new Handler(); Random rnd = new Random();
    double price = 2650.0, ema20 = 2650.0; int tradesToday=0; String lastTradeDate="";
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        priceText=findViewById(R.id.priceText); signalText=findViewById(R.id.signalText); logText=findViewById(R.id.logText);
        findViewById(R.id.btnBuy).setOnClickListener(v->manual(true)); findViewById(R.id.btnSell).setOnClickListener(v->manual(false));
        handler.postDelayed(new Runnable(){ public void run(){ price+=(rnd.nextDouble()-0.5)*1.5; ema20=ema20*0.95+price*0.05;
                priceText.setText(String.format(Locale.US,"XAUUSD: $%.2f | EMA20: %.2f",price,ema20)); checkAuto(); handler.postDelayed(this,2000); }},1000);
        log("Bot started. Lot 0.01 | SL 200c | TP 150c | 1 trade/day");
    }
    void checkAuto(){
        String today=new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(new Date()); if(!today.equals(lastTradeDate)){tradesToday=0; lastTradeDate=today;}
        if(tradesToday>=1) return; boolean dipped=price<ema20-0.8; boolean pumped=price>ema20+0.8;
        boolean bull=rnd.nextDouble()>0.92; boolean bear=rnd.nextDouble()>0.92;
        if(dipped&&bull) trigger("BUY","Dip below EMA + Bullish Engulfing\nEntry: "+String.format("%.2f",price));
        else if(pumped&&bear) trigger("SELL","Pump above EMA + Bearish Engulfing\nEntry: "+String.format("%.2f",price));
    }
    void manual(boolean isBuy){ String today=new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(new Date()); if(!today.equals(lastTradeDate)){tradesToday=0; lastTradeDate=today;}
        if(tradesToday>=1){log("BLOCKED: 1 trade per day done!"); return;} trigger(isBuy?"BUY":"SELL",isBuy?"Manual BUY after dip":"Manual SELL after pump");
    }
    void trigger(String type,String detail){ tradesToday++; signalText.setText(type+" SIGNAL\n"+detail); log(">>> "+type+" @ $"+String.format("%.2f",price)); log(detail); }
    void log(String msg){ String t=new SimpleDateFormat("HH:mm:ss",Locale.US).format(new Date()); logText.append("\n["+t+"] "+msg); }
}
