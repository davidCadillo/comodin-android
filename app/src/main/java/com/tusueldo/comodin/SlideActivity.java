package com.tusueldo.comodin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

public class SlideActivity extends WelcomeActivity {


    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimaryDark)
                .page(new TitlePage(R.drawable.logo,
                        "Title")
                )
                .page(new BasicPage(R.drawable.ic_comodin,
                        "Header",
                        "More text.")
                        .background(R.color.colorValidado)
                )
                .page(new BasicPage(R.drawable.ic_business_black_24dp,
                        "Lorem ipsum",
                        "dolor sit amet.")
                )
                .swipeToDismiss(true)
                .build();
    }
}
